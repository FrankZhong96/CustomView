package com.frank.customview.wight.colortracktextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
    private float mCurrentProgress = 0f;//当前进度，计算中间值用

    private Direction mDirection = Direction.LEFT_TO_RIGHT;//实现不同方向
    public enum Direction{
        LEFT_TO_RIGHT,RIGHT_TO_LEFT
    }


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

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.clipRect();//裁剪区域

        //根据进度把中间值算出来
        int middle = (int) (mCurrentProgress*getWidth());

        if(mDirection == Direction.LEFT_TO_RIGHT){//左往右
            //绘制变色
            DrawText(canvas,mChangePaint,0,middle);
            //绘制不变色
            DrawText(canvas,mOriginPaint,middle,getWidth());
        }else {
            //绘制变色
            DrawText(canvas,mChangePaint,getWidth()-middle,getWidth());
            //绘制不变色
            DrawText(canvas,mOriginPaint,0,getWidth()-middle);
        }
    }

    /**
     *绘制Text
     * @param canvas
     * @param paint
     * @param start
     * @param end
     */
    private void DrawText(Canvas canvas,Paint paint,int start,int end){
        canvas.save();
        Rect rect = new Rect(start,0,end,getHeight());
        //利用clipRect();可以裁剪     左边用一个画笔画    右边用另一个画笔画   不断改变中间值
        canvas.clipRect(rect);
        String text = getText().toString();
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int x = getWidth() / 2 - bounds.width() / 2;//获取字体宽度
        //基线baseLine
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top / 2) - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(text,x,baseLine,paint);
        canvas.restore();
    }

    //设置进度
    public void setCurrentProgress(float currentProgress){
        this.mCurrentProgress = currentProgress;
        invalidate();
    }

    //设置方向
    public void setDirection(Direction direction){
        this.mDirection = direction;
    }

    //设置变化颜色
    public void setChangeColor(int color){
        this.mChangeColor = color;
    }

}
