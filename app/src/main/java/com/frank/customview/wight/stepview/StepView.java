package com.frank.customview.wight.stepview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
    private Paint mEntiretyPaint, mPortionPanit, mTextPanit;
    private int mStepMax = 100;
    private int mStepCurrent = 50;


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
        mEntiretyPaint.setStrokeCap(Paint.Cap.ROUND);//边缘圆弧
        mEntiretyPaint.setStyle(Paint.Style.STROKE);//画笔空心

        mPortionPanit = new Paint();
        mPortionPanit.setAntiAlias(true);//设置抗锯齿
        mPortionPanit.setColor(mPortionColor);
        mPortionPanit.setStrokeWidth(mBorderWidth);
        mPortionPanit.setStrokeCap(Paint.Cap.ROUND);//边缘圆弧
        mPortionPanit.setStyle(Paint.Style.STROKE);//画笔空心

        mTextPanit = new Paint();
        mPortionPanit.setAntiAlias(true);//设置抗锯齿
        mTextPanit.setColor(mStepTextColor);
        mTextPanit.setTextSize(mStepTextSize);

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
        int center = getWidth() / 2;//中心点
        int radius = getWidth() / 2 - mBorderWidth / 2;//半径
//        RectF rectF = new RectF(mBorderWidth / 2, mBorderWidth / 2,
//                getWidth() - mBorderWidth / 2, getHeight() - mBorderWidth / 2);
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        canvas.drawArc(rectF, 135, 270, false, mEntiretyPaint);
        //画内圆弧 不能写死 百分比
        if (mStepMax == 0) return;
        float sweepAngle = (float) mStepCurrent / mStepMax;
        canvas.drawArc(rectF, 135, sweepAngle * 270, false, mPortionPanit);
        //画文字
        String stepText = mStepCurrent + "";
        Rect rect = new Rect();
        mTextPanit.getTextBounds(stepText, 0, stepText.length(), rect);
        int dx = getWidth() / 2 - rect.width() / 2;
        //基线 baseLine
        Paint.FontMetricsInt fontMetricsInt = mTextPanit.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(stepText, dx, baseLine, mTextPanit);
    }

    /**
     * 设置范围值
     * @param stepMax
     */
    public void setStepMax(int stepMax){
        this.mStepMax = stepMax;
    }

    public void setStepCurrent(int stepCurrent){
        this.mStepCurrent = stepCurrent;
        invalidate();//不断绘制
    }


}
