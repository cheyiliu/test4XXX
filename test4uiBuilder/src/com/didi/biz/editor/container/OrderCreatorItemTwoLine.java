package com.didi.biz.editor.container;

import com.example.test4uibuilder.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-6-2
 */

public class OrderCreatorItemTwoLine extends OrderCreatorItemBase {

    /**
     * @param context
     */
    public OrderCreatorItemTwoLine(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public OrderCreatorItemTwoLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public OrderCreatorItemTwoLine(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * 
     */
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.order_creator_item_two_line, this, true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setLeftSubject(java.lang.String)
     */
    @Override
    public void setLeftSubject(String subject) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setLeftSubjectDes(java.lang.String)
     */
    @Override
    public void setLeftSubjectDes(String des) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setRightIcon(int)
     */
    @Override
    public void setRightIcon(int iconId) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setRightInputHint(java.lang.String)
     */
    @Override
    public void setRightInputHint(String hint) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setRightInputContent(java.lang.String)
     */
    @Override
    public void setRightInputContent(String content) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setRightInputContentLine1(java.lang.String)
     */
    @Override
    public void setRightInputContentLine1(String content) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setRightInputContentLine2(java.lang.String)
     */
    @Override
    public void setRightInputContentLine2(String content) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#isItemEnabled()
     */
    @Override
    public boolean isItemEnabled() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setItemEnabled(boolean)
     */
    @Override
    public void setItemEnabled(boolean enable) {
    }

}
