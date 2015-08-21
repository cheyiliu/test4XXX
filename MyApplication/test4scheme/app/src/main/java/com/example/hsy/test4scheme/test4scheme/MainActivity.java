package com.example.hsy.test4scheme.test4scheme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("local://setting");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(uri);

            startActivity(intent);
        }

        {

            Uri uri = Uri.parse("local://setting");
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.addCategory(Intent.CATEGORY_DEFAULT);
//            intent.addCategory(Intent.CATEGORY_BROWSABLE);
//            intent.setData(uri);
            String scheme = uri.getScheme();
            String host = uri.getHost();
            if ("local".equals(scheme) && "setting".equals(host)){
                Log.i("test", "\"local\".equals(scheme) && \"setting\".equals(host)");
                Intent intent = new Intent(this, MainAc2.class);
                startActivity(intent);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
