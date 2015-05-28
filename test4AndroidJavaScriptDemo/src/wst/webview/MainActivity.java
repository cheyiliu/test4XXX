package wst.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private WebView contentWebView = null;
	private TextView msgView = null;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		contentWebView = (WebView) findViewById(R.id.webview);
		msgView = (TextView) findViewById(R.id.msg);
		// 启用javascript
		contentWebView.getSettings().setJavaScriptEnabled(true);
		// 从assets目录下面的加载html
		contentWebView.loadUrl("https://www.baidu.com/");

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(btnClickListener);
		contentWebView.addJavascriptInterface(this, "wst");
	}

	OnClickListener btnClickListener = new Button.OnClickListener() {
		public void onClick(View v) {
			// 无参数调用
			contentWebView.loadUrl("javascript:javacalljs()");
			// 传递参数调用
			contentWebView.loadUrl("javascript:javacalljswithargs(" + "'hello world'" + ")");
		}
	};

	public void startFunction() {
		Toast.makeText(this, "js调用了java函数", Toast.LENGTH_SHORT).show();
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				msgView.setText(msgView.getText() + "\njs调用了java函数");

			}
		});
	}

	public void startFunction(final String str) {
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				msgView.setText(msgView.getText() + "\njs调用了java函数传递参数：" + str);

			}
		});
	}
}