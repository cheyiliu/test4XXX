package com.didi.biz.editor.container;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.test4uibuilder.R;

public class OrderCreatorMainActivity extends Activity {

    private static final String CREATOR_TYPE = "creator_type";

    public static void startMe(Activity acContext, CreatorType type){
        if (null != acContext) {            
            Intent intent = new Intent();
            intent.putExtra(CREATOR_TYPE, type);
            acContext.startActivity(intent );
        }
    }
    enum CreatorType {
        BookingCar, RentCar, PickAtAirport, SendToAirport
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_creator_main);
        CreatorType type = CreatorType.PickAtAirport;
        configUI(type);
    }

    /**
     * @param type 
     * 
     */
    private void configUI(CreatorType type) {
        switch (type) {
        case  PickAtAirport:
            
            break;

        default:
            break;
        }
    }

}
