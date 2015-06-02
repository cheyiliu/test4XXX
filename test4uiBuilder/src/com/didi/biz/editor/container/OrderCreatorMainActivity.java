package com.didi.biz.editor.container;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.test4uibuilder.R;

//TODO 发单页卡关，控制接机送机和其他业务类型， key buildTemplate
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

    /** 元素顺序很重要，涉及到发单页输入有效性校验 */
    private List<OrderCreatorItemBase> mOrderedTemplate = new ArrayList<OrderCreatorItemBase>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_creator_main);

        initItems();

        CreatorType type = CreatorType.BookingCar;
        if (null != getIntent()) {
            type = (CreatorType) getIntent().getSerializableExtra(CREATOR_TYPE);
            if (null == type) {
                type = CreatorType.BookingCar;
            }
        }
        buildTemplate(type);
    }

    /**
     * 
     */
    private void initItems() {
        mItemCarType = (OrderCreatorItemBase) findViewById(R.id.car_type);
        OrderCreatorUIHelper.initCarType(mItemCarType, this);

        mItemFlight = (OrderCreatorItemBase) findViewById(R.id.flight_no);
        OrderCreatorUIHelper.initFlight(mItemCarType, this);

        mItemPassanger = (OrderCreatorItemBase) findViewById(R.id.for_who);
        OrderCreatorUIHelper.initPassanger(mItemCarType, this);

        mItemRentTime = (OrderCreatorItemBase) findViewById(R.id.rent_time);
        OrderCreatorUIHelper.initRentTime(mItemCarType, this);

        mItemStartPos = (OrderCreatorItemBase) findViewById(R.id.from_pos);
        OrderCreatorUIHelper.initStartPos(mItemCarType, this);

        mItemStartTime = (OrderCreatorItemBase) findViewById(R.id.start_time);
        OrderCreatorUIHelper.initStartTime(mItemCarType, this);

        mItemToPos = (OrderCreatorItemBase) findViewById(R.id.to_pos);
        OrderCreatorUIHelper.initToPos(mItemCarType, this);

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
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestFlight()
     */
    @Override
    public void requestFlight() {
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
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestStartTime()
     */
    @Override
    public void requestStartTime() {
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
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestFromPos()
     */
    @Override
    public void requestFromPos() {
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
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestToPos()
     */
    @Override
    public void requestToPos() {
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
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestRentTime()
     */
    @Override
    public void requestRentTime() {
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
     * @see com.didi.biz.editor.container.ICreatorItemInputCallback#requestCarType()
     */
    @Override
    public void requestCarType() {
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
