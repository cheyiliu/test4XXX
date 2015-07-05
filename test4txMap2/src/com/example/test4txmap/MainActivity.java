package com.example.test4txmap;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.marker.EsFromToInfoMarker;
import com.didi.es.map.marker.EsSimpleMarker;
import com.didi.es.map.marker.EsSingleLineInfoMarker;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

public class MainActivity extends Activity {
    private EsMapView mapView;
    EsSingleLineInfoMarker esSingleLineInfoMarker;
    EsFromToInfoMarker esFromToInfoMarker;
    Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            esSingleLineInfoMarker.setTextInfo("更新后的内容");
            SpannableString spannableString = new SpannableString("0123456789");
            spannableString.setSpan(new ForegroundColorSpan(Color.RED), 2, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
            esSingleLineInfoMarker.setTextInfo(spannableString);
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mapView = (EsMapView) findViewById(R.id.mapview);
        mapView.getMap().animateCamera(CameraUpdateFactory.newLatLng(new LatLng(39.905029, 116.389546)));

        // test3Marker();
        new EsSingleLineInfoMarker(mapView, R.drawable.red_location, 39.905029, 116.389546).setTextInfo("111");
        new EsSingleLineInfoMarker(mapView, R.drawable.red_location, 39.905029, 116.379546).setTextInfo("22222");
        new EsSimpleMarker(mapView, R.drawable.ic_launcher, 39.905029, 116.369546);

        esSingleLineInfoMarker = new EsSingleLineInfoMarker(mapView, R.drawable.red_location, 39.905029, 116.359546);
        mHandler.sendEmptyMessageDelayed(1, 5000);

        esFromToInfoMarker = new EsFromToInfoMarker(mapView, R.drawable.red_location, 39.905029, 116.349546);
        esFromToInfoMarker.setFromInfo("从得实出发", "");
        esFromToInfoMarker.setToInfo("到西二旗");
        
        String title = "title";
        String description = "des";
        String DateAdded = "date";
        // tView.setText(Html.fromHtml("<![CDATA[<font color='#145A14'>123text</font>]]>"));
        esFromToInfoMarker.setToInfo(Html.fromHtml("<b>"
                + title + "</b>" +  "<small><font color='#145A14'>" + description + "</font></small>" +  "<small>"
                + DateAdded + "</small>").toString());
    }

    /**
     * 
     */
    // private void test3Marker() {
    // new EsSimpleMarker(mapView, "1", "1", R.drawable.red_location, 39.905029, 116.389546);
    // new EsSimpleMarker(mapView, "12", "12", R.drawable.red_location, 39.915029, 116.389546);
    // new EsSimpleMarker(mapView, "123", "123", R.drawable.red_location, 39.895029, 116.389546);
    // new EsMyMarker(mapView, "1235", "1235", R.drawable.red_location, 39.875029, 116.389546);
    // new EsMyMarker(mapView, "12356", "12356", R.drawable.ic_launcher, 39.865029, 116.389546);
    // new EsMyMarker(mapView, "123567", "12356", R.drawable.ic_launcher, 39.865029, 116.289546);
    // new EsMyMarker(mapView, "1235678", "12356", R.drawable.ic_launcher, 39.865029, 116.489546);
    //
    // esLoadingMarker = new EsLoadingMarker(mapView, "", "", R.drawable.red_location, 39.940409,
    // 116.388817);
    // esLoadingMarker.showInfoWindow();
    // // EsMapHelper.setMapCenter(mapView, 39.940409, 116.388817);
    // // mapView.postDelayed(new Runnable() {
    // //
    // // @Override
    // // public void run() {
    // // Log.i("test", "post to update the from xxx to yyy");
    // // esLoadingMarker.setfrom("从xxx ");
    // // esLoadingMarker.setTo("  到yyy");
    // // }
    // // }, 5000);
    // // mapView.getMap().setLogoAnchor(0);//左下
    // // mapView.getMap().setLogoAnchor(1);//右下
    // mapView.getMap().setLogoAnchor(2);//右上
    // // mapView.getMap().setLogoAnchor(3);//左上
    // List<LatLng> latLngs = new ArrayList<LatLng>();
    // latLngs.add(new LatLng(39.905029, 116.389546));
    // latLngs.add(new LatLng(39.915029, 116.389546));
    // latLngs.add(new LatLng(39.895029, 116.389546));
    // latLngs.add(new LatLng(39.875029, 116.389546));
    // latLngs.add(new LatLng(39.865029, 116.389546));
    // latLngs.add(new LatLng(39.865029, 116.289546));
    // latLngs.add(new LatLng(39.865029, 116.489546));
    // // EsMapHelper.zoomToSpan(mapView, latLngs, 100, 100, 100, 100);
    //
    // // EsMapHelper.zoomBy(mapView, 10);
    // // EsMapHelper.zoomTo(mapView, 15);
    // }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();

    }

    @Override
    protected void onRestart() {
        mapView.onRestart();
        super.onRestart();
    }

    @Override
    protected void onStart() {
        mapView.onStart();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        super.onStop();
    }
}
