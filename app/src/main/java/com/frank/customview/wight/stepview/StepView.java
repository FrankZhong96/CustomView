package com.frank.customview.wight.stepview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.frank.customview.R;

/**
 * Created by Frank on 2018/8/25.
 *
 * @fuction 仿QQ步数
 */
public class StepView extends View {

    private int mEntiretyColor = Color.BLUE;//大圆弧颜色
    private int mPortionColor = Color.RED;//小圆弧颜色
    private int mBorderWidth = 20;//圆弧宽度    px
    private int mStepTextSize;//步数字体大小
    private int mStepTextColor = Color.RED;//步数字体颜色
    private Paint mEntiretyPaint;

    public StepView(Context context) {
        this(context, null);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //1.分析效果
        //2.确定自定义属性:在attrs.xml文件中编写自定义属性
        //3.在布局中使用
        //4.在自定义View中获取自定义属性

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StepView);
        mEntiretyColor = array.getColor(R.styleable.StepView_entiretyColor, mEntiretyColor);
        mPortionColor = array.getColor(R.styleable.StepView_portionColor, mPortionColor);
        mBorderWidth = array.getDimensionPixelSize(R.styleable.StepView_borderWidth, mBorderWidth);
        mStepTextSize = array.getDimensionPixelSize(R.styleable.StepView_stepTextSize, mStepTextSize);
        mStepTextColor = array.getColor(R.styleable.StepView_stepTextColor, mStepTextColor);
        array.recycle();

        mEntiretyPaint = new Paint();
        mEntiretyPaint.setAntiAlias(true);//设置抗锯齿
        mEntiretyPaint.setColor(mEntiretyColor);
        mEntiretyPaint.setStrokeWidth(mBorderWidth);
        mEntiretyPaint.setStyle(Paint.Style.FILL);//画笔实心

        //7.其他。。。

    }

    //5.onMeasure();
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽度和高度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //如果使用者设置wrap_content 设置默认值40dp
        //获取模式AT_MOAT

        //如果圆弧指定了高宽设置高宽都为那个最小值，确保控件为正方形
        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
    }

    //6.画外圆弧，内圆弧，文字，onDraw();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画外圆弧
        RectF rectF = new RectF(getWidth(),0,getHeight(),0);
        canvas.drawArc(rectF,135,270,false,mEntiretyPaint);
    }
}
