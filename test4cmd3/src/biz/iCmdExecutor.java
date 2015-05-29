package biz;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import android.os.Bundle;
import android.util.Log;
import basic.UiThreadHandler;

public class iCmdExecutor {
	private static final String TAG = "CmdExecutor";

	// private static final int CPU_COUNT =
	// Runtime.getRuntime().availableProcessors();
	// private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
	// private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
	// private static final int KEEP_ALIVE = 1;
	//
	// private static final ThreadFactory sThreadFactory = new ThreadFactory() {
	// private final AtomicInteger mCount = new AtomicInteger(1);
	//
	// public Thread newThread(Runnable r) {
	// return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
	// }
	// };
	//
	// private static final BlockingQueue<Runnable> sPoolWorkQueue =
	// new LinkedBlockingQueue<Runnable>(128);
	//
	// /**
	// * An {@link Executor} that can be used to execute tasks in parallel.
	// */
	// public static final Executor THREAD_POOL_EXECUTOR
	// = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
	// TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);

	private static ExecutorService executorService = Executors
			.newFixedThreadPool(3);
	// .newCachedThreadPool();;
	private static final Set<iCmd> mCmds = new HashSet<iCmd>();

	private static void addToPool(iCmd cmd) {
		Log.i(TAG, "addToPool, " + cmd);
		synchronized (mCmds) {
			mCmds.add(cmd);
		}
	}

	private static void removeFromPool(iCmd cmd) {
		Log.i(TAG, "removeFromPool, " + cmd);
		synchronized (mCmds) {
			mCmds.remove(cmd);
		}
	}

	public static void cancelAll(final Object tag) {
		if (tag == null) {
			throw new IllegalArgumentException(
					"Cannot cancelAll with a null tag");
		}
		Log.i(TAG, "cancelAll, " + tag);

		synchronized (mCmds) {
			for (iCmd cmd : mCmds) {
				if (cmd.getTag() == tag) {
					cmd.cancel();
				}
			}
		}
	}

	public static void exeAsync(final iCmd cmd, final iCmdListener listener,
			Object tag) {
		if (null == cmd) {
			throw new IllegalArgumentException(
					"Cannot exeAsync with a null cmd");
		}

		cmd.setTag(tag);
		addToPool(cmd);

		// pre notify
		if (null != listener) {
			UiThreadHandler.post(new Runnable() {

				@Override
				public void run() {
					if (!cmd.isCanceled()) {
						Log.i(TAG, "exeAsync, listener.onPreExecute()");
						listener.onPreExecute();
					} else {
						Log.i(TAG,
								"exeAsync, listener.onPreExecute(), cmd is canceled");
					}
				}
			});
		}
		// do
		if (!cmd.isCanceled()) {
			executorService.submit(new CmdWrapper(cmd, listener));
		} else {
			removeFromPool(cmd);
		}
		// post in CmdWrapper
	}

	public static void exeSync(iCmd cmd, iCmdListener listener, Object tag) {
		if (null == cmd) {
			throw new IllegalArgumentException(
					"Cannot exeAsync with a null cmd");
		}

		cmd.setTag(tag);
		addToPool(cmd);

		// pre notify
		if (null != listener && !cmd.isCanceled()) {
			Log.i(TAG, "exeSync, listener.onPreExecute()");
			listener.onPreExecute();
		}

		// do exe
		Bundle resultBundle = null;
		if (!cmd.isCanceled()) {
			Log.i(TAG, "exeSync, cmd.exe()");

			try {
				resultBundle = cmd.exe();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// post notify
		if (null != listener && !cmd.isCanceled()) {
			Log.i(TAG, "exeSync, listener.onPostExecute()");
			listener.onPostExecute(resultBundle);
		}

		removeFromPool(cmd);
	}

	private static class CmdWrapper extends FutureTask<Bundle> {
		private iCmdListener listener;
		private iCmd cmd;

		public CmdWrapper(iCmd cmd, iCmdListener listener) {
			super(new CmdCallable(cmd));
			this.listener = listener;
			this.cmd = cmd;
		}

		@Override
		protected void done() {
			Log.i(TAG, "CmdWrapper, done, tid="
					+ Thread.currentThread().getId());

			removeFromPool(cmd);

			boolean errorOccured = false;
			try {
				final Bundle resultBundle = get();

				UiThreadHandler.post(new Runnable() {

					@Override
					public void run() {
						if (!cmd.isCanceled()) {
							Log.i(TAG,
									"CmdWrapper, done, listener.onPostExecute(resultBundle), tid="
											+ Thread.currentThread().getId());
							listener.onPostExecute(resultBundle);
						}
					}
				});
			} catch (CancellationException e) {
				errorOccured = true;
				e.printStackTrace();
			} catch (InterruptedException e) {
				errorOccured = true;
				e.printStackTrace();
			} catch (ExecutionException e) {
				errorOccured = true;
				e.printStackTrace();
			}

			if (errorOccured) {
				UiThreadHandler.post(new Runnable() {

					@Override
					public void run() {
						if (!cmd.isCanceled()) {
							Log.i(TAG,
									"CmdWrapper, done, listener.onPostExecute(null)");
							listener.onPostExecute(null);
						}
					}
				});
			}
		}

		private static class CmdCallable implements Callable<Bundle> {
			private iCmd cmd;

			public CmdCallable(iCmd cmd) {
				this.cmd = cmd;
			}

			@Override
			public Bundle call() throws Exception {
				if (!cmd.isCanceled()) {
					try {
						Log.i(TAG, "CmdCallable, call, cmd.exe(), tid="
								+ Thread.currentThread().getId());
						return cmd.exe();
					} catch (Exception e) {
					}
				}
				return null;
			}
		}

	}

}
