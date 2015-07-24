package com.example.hsy.test4surfaceviewcircleanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hsy on 2015/7/23.
 */
public class WaveView extends View {

    // 圆环总数
    private int mRingsCount = 1;

    // 记录每一个环的当前半径
    private int[] mRingsRadius = new int[mRingsCount];

    // 环的宽度
    private int mRingWidth = 5;

    // 每次递增的步长
    private int mStep = 2;

    // 最大半径
    private int mMaxRadius = 100;

    // 最小半径
    private int mMinRadius = 10;

    // 中心位置
    private int mCenter = 0;

    // 画笔
    private Paint mPaint;


    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
    }

    private void init() {
        // 设置画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mRingWidth);

        // 设置最大最小半径
        mMaxRadius = getWidth() / 2;
        mMinRadius = 100;
        mCenter = getWidth() / 2;

        // 设置环数及初始半径
        mRingsCount = 2;
        mRingsRadius = new int[mRingsCount];
        mRingsRadius[0] = mMinRadius;
        mRingsRadius[1] = (mMinRadius + mMaxRadius) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mRingsRadius.length; i++) {
            drawRing(canvas, i);
        }

        updateRadius();

        invalidate();
    }

    private void updateRadius() {
        for (int i = 0; i < mRingsRadius.length; i++) {
            mRingsRadius[i] += mStep;
            if (mRingsRadius[i] > mMaxRadius) {
                mRingsRadius[i] = mMinRadius;
            }
        }
    }

    private void drawRing(Canvas canvas, int i) {
        int currentRadius = mRingsRadius[i];
        int color = getColor(currentRadius);
        mPaint.setColor(color);
        canvas.drawCircle(mCenter, mCenter, currentRadius, mPaint);
    }

    private int getColor(int radius) {
        // 在半径的变化过程中，透明度的变化是：100->0->100
        {
            int total = mMaxRadius - mMinRadius;
            int halfTotal = total/2;
            float deltaRadius = radius - mMinRadius;
            float ratio = 0;
            if (deltaRadius <halfTotal){
                ratio =  deltaRadius/halfTotal;
            }else {
                ratio = 2.0f - deltaRadius/halfTotal;
            }
            int alpha = (int) (255*ratio);
            return Color.argb(alpha, 255, 137, 3);
        }
//        int total = mMaxRadius - mMinRadius;
//        float ratio = (radius - mMinRadius) * 1.0f / total;
//        if (ratio < 0) {
//            ratio = 0;
//        }
//        int alpha = (int) ((1.0f - ratio) * 255);
//        return Color.argb(alpha, 255, 137, 3);
    }

}
