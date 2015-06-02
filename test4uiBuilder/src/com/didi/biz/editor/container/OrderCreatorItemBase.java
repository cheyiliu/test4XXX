package com.didi.biz.editor.container;

import com.example.test4uibuilder.R;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-6-2
 */

public class OrderCreatorItemBase extends RelativeLayout implements ICreatorItem {
    private TextView mTextViewLeftSubject;
    private TextView mTextViewLeftSubjectDes;
    private TextView mTextViewRightInputHint;
    private TextView mTextViewRightInputContent;
    private TextView mTextViewRightInputContentLine1;
    private TextView mTextViewRightInputContentLine2;

    private boolean mItemEnabled;

    View m2LineContenter = findViewById(R.id.right_input_content_2line_container);
    ImageView mImageViewRightIcon = (ImageView) findViewById(R.id.right_icon);

    /**
     * @param context
     */
    public OrderCreatorItemBase(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public OrderCreatorItemBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public OrderCreatorItemBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.order_creator_item_base, this, true);

        mTextViewLeftSubject = (TextView) findViewById(R.id.left_subject);
        mTextViewLeftSubjectDes = (TextView) findViewById(R.id.left_subject_des);
        mTextViewRightInputHint = (TextView) findViewById(R.id.right_input_hint);
        mTextViewRightInputContent = (TextView) findViewById(R.id.right_input_content);
        mTextViewRightInputContentLine1 = (TextView) findViewById(R.id.right_input_content_line1);
        mTextViewRightInputContentLine2 = (TextView) findViewById(R.id.right_input_content_line2);

        m2LineContenter = findViewById(R.id.right_input_content_2line_container);
        mImageViewRightIcon = (ImageView) findViewById(R.id.right_icon);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#isItemEnabled()
     */
    @Override
    public boolean isItemEnabled() {
        return mItemEnabled;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setItemEnabled(boolean)
     */
    @Override
    public void setItemEnabled(boolean enable) {
        mItemEnabled = enable;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setLeftSubject(java.lang.String)
     */
    @Override
    public void setLeftSubject(String subject) {
        mTextViewLeftSubject.setText(subject);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setLeftSubjectDes(java.lang.String)
     */
    @Override
    public void setLeftSubjectDes(String des) {
        mTextViewLeftSubjectDes.setText(des);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setRightIcon(int)
     */
    @Override
    public void setRightIcon(int iconId) {
        mImageViewRightIcon.setImageResource(iconId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setRightInputHint(java.lang.String)
     */
    @Override
    public void setRightInputHint(String hint) {
        mTextViewRightInputHint.setText(hint);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setRightInputContent(java.lang.String)
     */
    @Override
    public void setRightInputContent(String content) {
        mTextViewRightInputContent.setText(content);
        mTextViewRightInputContent.setVisibility(View.VISIBLE);
        m2LineContenter.setVisibility(View.GONE);
        if (TextUtils.isEmpty(content)) {
            mTextViewRightInputHint.setVisibility(View.VISIBLE);
        } else {
            mTextViewRightInputHint.setVisibility(View.INVISIBLE);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.biz.editor.container.ICreatorItem#setRightInputContent(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void setRightInputContent(String content1, String content2) {
        mTextViewRightInputContent.setVisibility(View.GONE);
        m2LineContenter.setVisibility(View.VISIBLE);
        mTextViewRightInputContentLine1.setText(content1);
        mTextViewRightInputContentLine2.setText(content2);

        if (TextUtils.isEmpty(content1) && TextUtils.isEmpty(content2)) {
            mTextViewRightInputHint.setVisibility(View.VISIBLE);
        } else {
            mTextViewRightInputHint.setVisibility(View.INVISIBLE);
        }
    }

}
