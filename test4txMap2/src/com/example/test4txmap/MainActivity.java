package com.example.test4txmap;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.helper.EsMapHelper;
import com.didi.es.map.marker.EsMyMarker;
import com.didi.es.map.marker.EsSimpleMarker;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

public class MainActivity extends Activity {
    private EsMapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mapView = (EsMapView) findViewById(R.id.mapview);
        mapView.getMap().animateCamera(CameraUpdateFactory.newLatLng(new LatLng(39.905029, 116.389546)));

        test3Marker();
    }

    /**
     * 
     */
    private void test3Marker() {
        new EsSimpleMarker(mapView, "1", "1", R.drawable.red_location, 39.905029, 116.389546);
        new EsSimpleMarker(mapView, "12", "12", R.drawable.red_location, 39.915029, 116.389546);
        new EsSimpleMarker(mapView, "123", "123", R.drawable.red_location, 39.895029, 116.389546);
        new EsMyMarker(mapView, "1235", "1235", R.drawable.red_location, 39.875029, 116.389546);
        new EsMyMarker(mapView, "12356", "12356", R.drawable.ic_launcher, 39.865029, 116.389546);
        new EsMyMarker(mapView, "123567", "12356", R.drawable.ic_launcher, 39.865029, 116.289546);
        new EsMyMarker(mapView, "1235678", "12356", R.drawable.ic_launcher, 39.865029, 116.489546);

        List<LatLng> latLngs = new ArrayList<LatLng>();
        latLngs.add(new LatLng(39.905029, 116.389546));
        latLngs.add(new LatLng(39.915029, 116.389546));
        latLngs.add(new LatLng(39.895029, 116.389546));
        latLngs.add(new LatLng(39.875029, 116.389546));
        latLngs.add(new LatLng(39.865029, 116.389546));
        latLngs.add(new LatLng(39.865029, 116.289546));
        latLngs.add(new LatLng(39.865029, 116.489546));
//        EsMapHelper.zoomToSpan(mapView, latLngs, 100, 100, 100, 100);
        
        EsMapHelper.zoomBy(mapView, 10);
//        EsMapHelper.zoomTo(mapView, 15);
    }

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
