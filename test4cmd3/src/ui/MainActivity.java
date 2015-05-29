package ui;

import img.BitmapCache;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import biz.CmdAdd;
import biz.CmdSubtract;
import biz.iCmdExecutor;
import biz.iCmdListener;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test4cmd2.R;

public class MainActivity extends Activity {

	protected static final String TAG = "test";
	private ImageView iv1;
	private ImageView iv2;
	private TextView tvAddResult;
	private TextView tvSubResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate, " + this);
		setContentView(R.layout.activity_main);
		tvAddResult = (TextView) findViewById(R.id.tv_result_add);
		tvSubResult = (TextView) findViewById(R.id.tv_result_sub);
		iv1 = (ImageView) findViewById(R.id.img_volley);
		iv2 = (ImageView) findViewById(R.id.img_volley2);
		findViewById(R.id.btn_finish).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		Log.e(TAG, "-------------------------------------------");
		for (int i = 0; i < 1000; i++)iCmdExecutor.exeAsync(new CmdAdd(new BigInteger("123"), new BigInteger(
				"12345678901234567890123456789012345678901234567890")),
				new iCmdListener() {

					@Override
					public void onPreExecute() {
						Log.e(TAG, "onPreExecute, tid="
								+ Thread.currentThread().getId());
					}

					@Override
					public void onPostExecute(Bundle resultBundle) {
						Log.e(TAG, "onPostExecute, tid="
								+ Thread.currentThread().getId()
								+ ", resultBundle=" + resultBundle);
						if (null != resultBundle) {
							String res = resultBundle.getString("result");
							tvAddResult.setText(res);
						}
					}
				}, this);
		Log.e(TAG, "-------------------------------------------");
		
		for (int i = 0; i < 1000; i++)iCmdExecutor
				.exeAsync(
						new CmdSubtract(
								new BigDecimal("123"),
								new BigDecimal(
										"12345678901234567890123456789012345678901234567890.1")),
						new iCmdListener() {

							@Override
							public void onPreExecute() {
								Log.e(TAG, "onPreExecute, tid="
										+ Thread.currentThread().getId());
							}

							@Override
							public void onPostExecute(Bundle resultBundle) {
								Log.e(TAG, "onPostExecute, tid="
										+ Thread.currentThread().getId()
										+ ", resultBundle=" + resultBundle);
								if (null != resultBundle) {
									String res = resultBundle
											.getString("result");
									tvSubResult.setText(res);
								}
							}
						}, this);
		Log.e(TAG, "-------------------------------------------");

		{
			// test volley
			RequestQueue queue = Volley.newRequestQueue(this);
			String url = "http://www.baidu.com";
			Listener<String> listener = new Listener<String>() {

				@Override
				public void onResponse(String arg0) {
					Log.i(TAG, "onResponse, " + arg0);
				}
			};
			ErrorListener errorListener = new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError arg0) {
					Log.i(TAG, "onErrorResponse, " + arg0);
				}
			};
			StringRequest request = new StringRequest(url, listener,
					errorListener){

						@Override
						protected Map<String, String> getParams()
								throws AuthFailureError {
							// TODO Auto-generated method stub
							return super.getParams();
						}};
			queue.add(request);

			//
			JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
					"http://m.weather.com.cn/data/101010100.html", null,
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {
							Log.d(TAG, response.toString());
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Log.e(TAG, error.getMessage(), error);
						}
					});
			queue.add(jsonObjectRequest);

			//
			ImageRequest imageRequest = new ImageRequest(
					"http://img.my.csdn.net/uploads/201407/17/1405567749_8669.jpg",
					new Response.Listener<Bitmap>() {

						@Override
						public void onResponse(Bitmap response) {
							iv1.setImageBitmap(response);
						}
					}, 100, 100, ScaleType.CENTER_INSIDE, Config.RGB_565,
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							iv1.setImageResource(R.drawable.ic_launcher);
						}
					});
			queue.add(imageRequest);

			//
			ImageLoader imageLoader = new ImageLoader(queue, new BitmapCache());
			ImageListener l2 = ImageLoader.getImageListener(iv2,
					R.drawable.ic_launcher, R.drawable.ic_launcher);
			imageLoader
					.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
							l2);
		}
	}

	@Override
	protected void onDestroy() {
		Log.e(TAG, "onDestroy, " + this);
		iCmdExecutor.cancelAll(this);
		super.onDestroy();
	}
}
