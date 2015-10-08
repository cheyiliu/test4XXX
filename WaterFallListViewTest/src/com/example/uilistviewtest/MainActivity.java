package com.example.uilistviewtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.example.internal.AdapterView;
import com.example.internal.ListView;

public class MainActivity extends Activity {
	
	private List<Fruit> fruitList = new ArrayList<Fruit>();
	GridView v;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initFruits();
		FruitAdapter adapter = new FruitAdapter(MainActivity.this,
				R.layout.fruit_item, fruitList);
		ListView listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Fruit fruit = fruitList.get(position);
//				Toast.makeText(MainActivity.this, fruit.getName(),
//						Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void initFruits() {
		for (int i = 0; i < 50; i++) {
			StringBuilder text = new StringBuilder("Position[" + i + "]:\n");
			int loopCount = new Random().nextInt(15) + 1;
			for (int j = 0; j < loopCount; j++) {
				text.append("11111111111111111");
			}
			fruitList.add(new Fruit(text.toString()));
		}
	}

}