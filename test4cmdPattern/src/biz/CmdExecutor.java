package biz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import basic.UiThreadHandler;

public class CmdExecutor {

	public static ExecutorService executorService = Executors
			.newCachedThreadPool();

	public static void exeInBackground(iCmd iCmd, iCmdListener iCmdListener) {
		// TODO Auto-generated method stub
		executorService.execute(new CmdWrapper(iCmd, iCmdListener));

	}
	
	public static void exeInFroground(iCmd iCmd, iCmdListener iCmdListener) {
		// TODO Auto-generated method stub
//		executorService.execute(new CmdWrapper(iCmd, iCmdListener));
		iCmdListener.onPreExe();
		if (iCmd.exe()) {
			iCmdListener.onSucess();
		} else {
			iCmdListener.onFail();
		}

	}

	private static class CmdWrapper implements Runnable {
		iCmd cmd;
		iCmdListener iCmdListener;

		public CmdWrapper(iCmd cmd, biz.iCmdListener iCmdListener) {
			// super();
			this.cmd = cmd;
			this.iCmdListener = iCmdListener;
		}

		public void run() {
			UiThreadHandler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					iCmdListener.onPreExe();
				}
			});

			if (cmd.exe()) {
				UiThreadHandler.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						iCmdListener.onSucess();
					}
				});

			} else {
				UiThreadHandler.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						iCmdListener.onFail();
					}
				});

			}

		}
	}

}
