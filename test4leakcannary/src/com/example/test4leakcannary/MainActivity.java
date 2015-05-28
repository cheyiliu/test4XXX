package com.example.test4leakcannary;

import org.eclipse.mat.parser.index.IndexWriter.Identifier;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Box box = new Box();//来自官方的例子
		Cat schrodingerCat = new Cat();
		box.hiddenCat = schrodingerCat;
		Docker.container = box;
		MyApp.getRefWatcher(this).watch(schrodingerCat);
	}

}

class Cat {
}

class Box {
	Cat hiddenCat;
}

class Docker {
	static Box container;
}
