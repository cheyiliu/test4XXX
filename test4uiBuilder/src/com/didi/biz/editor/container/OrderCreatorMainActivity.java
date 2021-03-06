package com.didi.biz.editor.container;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.widget.Toast;

import com.example.test4uibuilder.R;

//TODO 发单页卡关，控制接机送机和其他业务类型， key buildTemplate
public class OrderCreatorMainActivity extends Activity implements ICreatorItemInputCallback {

    private static final String CREATOR_TYPE = "creator_type";
    private static final String TAG = "OrderCreatorMainActivity";

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

    /** 元素顺序很重要，涉及到发单页输入有效性校验 */
    private List<OrderCreatorItemBase> mOrderedTemplate = new ArrayList<OrderCreatorItemBase>();

    private Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                Toast.makeText(getApplicationContext(), "PickAtAirport", Toast.LENGTH_LONG).show();
                buildTemplate(CreatorType.PickAtAirport);
                mHandler.sendEmptyMessageDelayed(2, 10000);

            }
            if (msg.what == 2) {
                Toast.makeText(getApplicationContext(), "SendToAirport", Toast.LENGTH_LONG).show();
                buildTemplate(CreatorType.SendToAirport);
                mHandler.sendEmptyMessageDelayed(3, 10000);
            }
            if (msg.what == 3) {
                Toast.makeText(getApplicationContext(), "RentCar", Toast.LENGTH_LONG).show();
                buildTemplate(CreatorType.RentCar);
                mHandler.sendEmptyMessageDelayed(4, 10000);
            }

            if (msg.what == 4) {
                Toast.makeText(getApplicationContext(), "BookingCar", Toast.LENGTH_LONG).show();
                buildTemplate(CreatorType.BookingCar);
                mHandler.sendEmptyMessageDelayed(1, 10000);// 消息继续循环
            }
            return false;
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_creator_main);

        initItems();

        // CreatorType type = CreatorType.BookingCar;
        // if (null != getIntent()) {
        // type = (CreatorType) getIntent().getSerializableExtra(CREATOR_TYPE);
        // if (null == type) {
        // type = CreatorType.BookingCar;
        // }
        // }
        // buildTemplate(type);

        mHandler.sendEmptyMessageDelayed(1, 0);

    }

    /**
     * 
     */
    private void initItems() {
        mItemCarType = (OrderCreatorItemBase) findViewById(R.id.car_type);
        OrderCreatorUIHelper.initCarType(mItemCarType, this);

        mItemFlight = (OrderCreatorItemBase) findViewById(R.id.flight_no);
        OrderCreatorUIHelper.initFlight(mItemFlight, this);

        mItemPassanger = (OrderCreatorItemBase) findViewById(R.id.for_who);
        OrderCreatorUIHelper.initPassanger(mItemPassanger, this);

        mItemRentTime = (OrderCreatorItemBase) findViewById(R.id.rent_time);
        OrderCreatorUIHelper.initRentTime(mItemRentTime, this);

        mItemStartPos = (OrderCreatorItemBase) findViewById(R.id.from_pos);
        OrderCreatorUIHelper.initStartPos(mItemStartPos, this);

        mItemStartTime = (OrderCreatorItemBase) findViewById(R.id.start_time);
        OrderCreatorUIHelper.initStartTime(mItemStartTime, this);

        mItemToPos = (OrderCreatorItemBase) findViewById(R.id.to_pos);
        OrderCreatorUIHelper.initToPos(mItemToPos, this);

        // add all by ui orders
        mOrderedTemplate.add(mItemPassanger);
        mOrderedTemplate.add(mItemFlight);
        mOrderedTemplate.add(mItemStartTime);
        mOrderedTemplate.add(mItemStartPos);
        mOrderedTemplate.add(mItemToPos);
        mOrderedTemplate.add(mItemRentTime);
        mOrderedTemplate.add(mItemCarType);
    }

    /**
     * @param type
     * 
     */
    private void buildTemplate(CreatorType type) {
        // enable all
        for (int i = 0; i < mOrderedTemplate.size(); i++) {
            mOrderedTemplate.get(i).setItemEnabled(true);
        }

        // disable the unnecessary items
        switch (type) {
        case PickAtAirport:
            mItemRentTime.setItemEnabled(false);
            break;
        case SendToAirport:// fall through
        case BookingCar:
            mItemFlight.setItemEnabled(false);
            mItemRentTime.setItemEnabled(false);
            break;
        case RentCar:
            mItemFlight.setItemEnabled(false);
            mItemToPos.setItemEnabled(false);
            break;

        default:
            break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestPassenger()
     */
    @Override
    public void requestPassenger() {
        Log.i(TAG, "requestPassenger");
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType(Phone.CONTENT_TYPE);
        startActivityForResult(i, RequestCode.REQUEST_CODE_PICK_PASSANGER);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onPassengerSelected()
     */
    @Override
    public void onPassengerSelected() {
        Log.i(TAG, "onPassengerSelected");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestFlight()
     */
    @Override
    public void requestFlight() {
        Log.i(TAG, "requestFlight");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onFlightSelected()
     */
    @Override
    public void onFlightSelected() {
        Log.i(TAG, "onFlightSelected");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestStartTime()
     */
    @Override
    public void requestStartTime() {
        Log.i(TAG, "requestStartTime");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onStartTimeSelected()
     */
    @Override
    public void onStartTimeSelected() {
        Log.i(TAG, "onStartTimeSelected");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestFromPos()
     */
    @Override
    public void requestFromPos() {
        Log.i(TAG, "requestFromPos");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onFromPosSelected()
     */
    @Override
    public void onFromPosSelected() {
        Log.i(TAG, "onFromPosSelected");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestToPos()
     */
    @Override
    public void requestToPos() {
        Log.i(TAG, "requestToPos");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onToPosSelected()
     */
    @Override
    public void onToPosSelected() {
        Log.i(TAG, "onToPosSelected");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestRentTime()
     */
    @Override
    public void requestRentTime() {
        Log.i(TAG, "requestRentTime");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onRentTimeSelected()
     */
    @Override
    public void onRentTimeSelected() {
        Log.i(TAG, "onRentTimeSelected");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestCarType()
     */
    @Override
    public void requestCarType() {
        Log.i(TAG, "requestCarType");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#onCarTypeSelected()
     */
    @Override
    public void onCarTypeSelected() {
        Log.i(TAG, "onCarTypeSelected");
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode) {
            if (RequestCode.REQUEST_CODE_PICK_PASSANGER == requestCode) {
                onPassengerSelected();
            }
        }
    }
}
