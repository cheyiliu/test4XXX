package com.didi.biz.editor.container;

import com.example.test4uibuilder.R;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * 辅助类，抽离主类代码量
 * 
 * @author houshengyong
 * @since 2015-6-2
 */

public class OrderCreatorUIHelper {
    private OrderCreatorUIHelper() {
    }

    /**
     * @param mItemCarType
     * @param orderCreatorMainActivity
     */
    public static void initCarType(OrderCreatorItemBase item, final ICreatorItemInputCallback inputCallback) {
        item.setLeftSubject("车型");
        item.setLeftSubjectDes(" xx描述");
        item.setRightIcon(R.drawable.ic_launcher);
        item.setRightInputContent("setRightInputContent");
        item.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                inputCallback.requestCarType();
            }
        });
    }

    /**
     * @param mItemCarType
     * @param orderCreatorMainActivity
     */
    public static void initFlight(OrderCreatorItemBase item, final ICreatorItemInputCallback inputCallback) {
        item.setLeftSubject("航班");
        item.setLeftSubjectDes(" xx描述");
        item.setRightIcon(R.drawable.ic_launcher);
        item.setRightInputContent("content line1", "content line2");
        item.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                inputCallback.requestFlight();
            }
        });
    }

    /**
     * @param mItemCarType
     * @param orderCreatorMainActivity
     */
    public static void initPassanger(OrderCreatorItemBase item, final ICreatorItemInputCallback inputCallback) {
        item.setLeftSubject("乘客");
        item.setLeftSubjectDes(" xx描述");
        item.setRightIcon(R.drawable.ic_launcher);
        item.setRightInputContent("姓名abc", "电话1234567890");
        item.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                inputCallback.requestPassenger();
            }
        });
    }

    /**
     * @param mItemCarType
     * @param orderCreatorMainActivity
     */
    public static void initRentTime(OrderCreatorItemBase item, final ICreatorItemInputCallback inputCallback) {
        item.setLeftSubject("租车时长");
        item.setLeftSubjectDes(" xx描述");
        item.setRightIcon(R.drawable.ic_launcher);
        item.setRightInputContent("setRightInputContent");
        item.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                inputCallback.requestRentTime();
            }
        });
    }

    /**
     * @param mItemCarType
     * @param orderCreatorMainActivity
     */
    public static void initStartPos(OrderCreatorItemBase item, final ICreatorItemInputCallback inputCallback) {

        item.setLeftSubject("起点");
        item.setLeftSubjectDes(" xx描述");
        item.setRightIcon(R.drawable.ic_launcher);
        item.setRightInputContent("setRightInputContent");
        item.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                inputCallback.requestFromPos();
            }
        });

    }

    /**
     * @param mItemCarType
     * @param orderCreatorMainActivity
     */
    public static void initStartTime(OrderCreatorItemBase item, final ICreatorItemInputCallback inputCallback) {
        item.setLeftSubject("出发时间");
        item.setLeftSubjectDes(" xx描述");
        item.setRightIcon(R.drawable.ic_launcher);
        item.setRightInputContent("setRightInputContent");
        item.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                inputCallback.requestStartTime();
            }
        });
    }

    /**
     * @param mItemCarType
     * @param orderCreatorMainActivity
     */
    public static void initToPos(OrderCreatorItemBase item, final ICreatorItemInputCallback inputCallback) {
        item.setLeftSubject("终点");
        item.setLeftSubjectDes(" xx描述");
        item.setRightIcon(R.drawable.ic_launcher);
        item.setRightInputContent("setRightInputContent");
        item.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                inputCallback.requestToPos();
            }
        });
    }

}
