package com.didi.biz.editor.container;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.test4uibuilder.R;

public class OrderCreatorMainActivity extends Activity implements ICreatorItemInputCallback {

    private static final String CREATOR_TYPE = "creator_type";

    /**
     * 启动发单页
     * 
     * @param acContext
     * @param type
     */
    public static void startMe(Activity acContext, CreatorType type) {
        if (null != acContext) {
            Intent intent = new Intent();
            intent.putExtra(CREATOR_TYPE, type);
            acContext.startActivity(intent);
        }
    }

    private OrderCreatorItemBase mItemPassanger;
    private OrderCreatorItemBase mItemFlight;
    private OrderCreatorItemBase mItemStartTime;
    private OrderCreatorItemBase mItemStartPos;
    private OrderCreatorItemBase mItemToPos;
    private OrderCreatorItemBase mItemRentTime;
    private OrderCreatorItemBase mItemCarType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_creator_main);
        
        initItems();
        
        CreatorType type = CreatorType.PickAtAirport;
        if (null != getIntent()) {
            type = (CreatorType) getIntent().getSerializableExtra(CREATOR_TYPE);
        }
        buildTemplate(type);
    
    }

    /**
     * 
     */
    private void initItems() {        
        mItemCarType = (OrderCreatorItemBase) findViewById(R.id.car_type);
        mItemFlight = (OrderCreatorItemBase) findViewById(R.id.flight_no);
        mItemPassanger = (OrderCreatorItemBase) findViewById(R.id.for_who);
        mItemRentTime = (OrderCreatorItemBase) findViewById(R.id.rent_time);
        mItemStartPos = (OrderCreatorItemBase) findViewById(R.id.from_pos);
        mItemStartTime = (OrderCreatorItemBase) findViewById(R.id.start_time);
        mItemToPos = (OrderCreatorItemBase) findViewById(R.id.to_pos);
    }

    /**
     * @param type
     * 
     */
    private void buildTemplate(CreatorType type) {
        switch (type) {
        case PickAtAirport:

            break;

        default:
            break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onPassengerSelected()
     */
    @Override
    public void onPassengerSelected() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onFlightSelected()
     */
    @Override
    public void onFlightSelected() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onStartTimeSelected()
     */
    @Override
    public void onStartTimeSelected() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onFromPosSelected()
     */
    @Override
    public void onFromPosSelected() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onToPosSelected()
     */
    @Override
    public void onToPosSelected() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onRentTimeSelected()
     */
    @Override
    public void onRentTimeSelected() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onCarTypeSelected()
     */
    @Override
    public void onCarTypeSelected() {
    }

}
