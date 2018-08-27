package com.frank.customview.wight.colortracktextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.frank.customview.R;

/**
 * Created by Frank on 2018/8/27.
 *
 * @fuction 字体变色
 */
public class ColorTrackTextView extends android.support.v7.widget.AppCompatTextView {

    private int mOriginColor = Color.BLACK;//不变化的颜色
    private int mChangeColor = Color.RED;//变化的颜色
    private Paint mOriginPaint, mChangePaint;

    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        mOriginColor = array.getColor(R.styleable.ColorTrackTextView_originColor, mOriginColor);
        mChangeColor = array.getColor(R.styleable.ColorTrackTextView_changeColor, mChangeColor);

        mOriginPaint = getPaintByColor(mOriginColor);
        mChangePaint = getPaintByColor(mChangeColor);

        array.recycle();//属性回收
    }

    /**
     * 根据颜色获取画笔
     *
     * @param color 颜色值
     * @return 画笔paint
     */
    private Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        paint.setColor(color);//设置颜色
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setDither(true);//防抖动
        paint.setTextSize(getTextSize());//设置字体大小，getTextSize()就是TextView的字体大小
        return paint;
    }
}
