package ui;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import biz.CmdExecutor;
import biz.MyCmd;
import biz.iCmdListener;

import com.example.test4cmdpattern.R;

public class MainActivity extends Activity {
	private String TAG = "test";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.e(TAG, "thread id=" + Thread.currentThread().getId());
		findViewById(R.id.btn_toexeccmd).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						CmdExecutor.exeInBackground(new MyCmd(),
								new iCmdListener() {

									@Override
									public void onSucess() {
										// TODO Auto-generated method stub
										Log.e(TAG, "onSucess, thread id="
												+ Thread.currentThread()
														.getId() + ", this="
												+ this);
									}

									@Override
									public void onPreExe() {
										// TODO Auto-generated method stub
										Log.e(TAG, "onPreExecute, thread id="
												+ Thread.currentThread()
														.getId() + ", this="
												+ this);
									}

									@Override
									public void onFail() {
										// TODO Auto-generated method stub
										Log.e(TAG, "onFaile, thread id="
												+ Thread.currentThread()
														.getId() + ", this="
												+ this);
									}

								});
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		{
			FutureTask<String> futureTask = new FutureTask<String>(
					new Callable<String>() {

						@Override
						public String call() throws Exception {
							// TODO Auto-generated method stub
							return null;
						}
					}) {

				@Override
				protected void done() {
					// TODO Auto-generated method stub
					super.done();
					try {
						get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CancellationException e) {
						// TODO: handle exception
					}
				}
			};

		}
		return true;
	}

}
