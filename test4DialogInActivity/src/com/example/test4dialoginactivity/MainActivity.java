package com.example.test4dialoginactivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  
        builder.setMessage("Are you sure you want to exit?")  
               .setCancelable(false)  
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {  
                   public void onClick(DialogInterface dialog, int id) {  
                	   MainActivity.this.finish();  
                   }  
               })  
               .setNegativeButton("No", new DialogInterface.OnClickListener() {  
                   public void onClick(DialogInterface dialog, int id) {  
                        dialog.cancel();  
                   }  
               });  
        AlertDialog alert = builder.create();
        alert.show();
        
        findViewById(R.id.tv).postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				finish();
			}
		}, 10000);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
