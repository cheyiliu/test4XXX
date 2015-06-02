package com.didi.biz.editor.container;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-6-2
 */

public abstract class OrderCreatorItemBase extends RelativeLayout implements ICreatorItem {

    /**
     * @param context
     */
    public OrderCreatorItemBase(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public OrderCreatorItemBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public OrderCreatorItemBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

}
