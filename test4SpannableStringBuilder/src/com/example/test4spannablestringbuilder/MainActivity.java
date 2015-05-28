package com.example.test4spannablestringbuilder;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv = (TextView) findViewById(R.id.text_view);

		SpannableString ss = new SpannableString(
				"红色打电话斜体删除线绿色下划线图片:.蓝色背景1234567890abcdefghijklnopqrstuvwxyz");

		// 用颜色标记文本
		ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 2,
		// setSpan时需要指定的 flag,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括).
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 用超链接标记文本
		ss.setSpan(new URLSpan("tel:10086"), 2, 5,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 用样式标记文本（斜体）
		ss.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 5, 7,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 用删除线标记文本
		ss.setSpan(new StrikethroughSpan(), 7, 10,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 用下划线标记文本
		ss.setSpan(new UnderlineSpan(), 10, 16,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 用颜色标记
		ss.setSpan(new ForegroundColorSpan(Color.GREEN), 10, 12,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 获取Drawable资源
		Drawable d = getResources().getDrawable(R.drawable.ic_launcher);
		d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
		// 创建ImageSpan
		ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
		// 用ImageSpan替换文本
		ss.setSpan(span, 18, 19, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

		// 用蓝色背景标记文本
		ss.setSpan(new BackgroundColorSpan(Color.BLUE), 19, 23,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 用缩放x来标记
		ss.setSpan(new ScaleXSpan(3), 23, 29,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 用上标来标记
		ss.setSpan(new SuperscriptSpan(), 29, 35,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		// 用下标来标记
		ss.setSpan(new SubscriptSpan(), 35, 38,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		// 用相对大小来标记
		ss.setSpan(new RelativeSizeSpan(3), 38, 40,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		// many other
		// TODO

		// 文本设置
		tv.setText(ss);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
