package com.frank.customview.wight.lettersidebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.frank.customview.R;

/**
 * Created by Frank on 2018/8/29.
 *
 * @fuction 字母索引列表
 */
public class LetterSideBar extends View {

    private Paint mPaint;
    private int mSideBarColor = Color.BLUE;
    private int mSideBarHightColor = Color.RED;
    private int mSideBarSize = 15;
    private String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private String mCurrentTouchLetter;

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LetterSideBar);
        mSideBarColor = array.getColor(R.styleable.LetterSideBar_sideBarColor, mSideBarColor);
        mSideBarHightColor = array.getColor(R.styleable.LetterSideBar_sideBarHeightColor, mSideBarHightColor);
        mSideBarSize = array.getDimensionPixelSize(R.styleable.LetterSideBar_sideBarSize, mSideBarSize);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setTextSize(mSideBarSize);
        mPaint.setColor(mSideBarColor);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量索引列表宽度 = PaddingLeft + PaddingRight + 字母的宽度
        int textWidth = (int) mPaint.measureText("A");
        int width = getPaddingLeft() + getPaddingRight() + textWidth;
        int height = MeasureSpec.getSize(heightMeasureSpec);//高度直接获取
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        //画26个字母
        //字母的高度
        int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
        for (int i = 0; i < mLetters.length; i++) {
            //每个字母的中心位置   第一个字母的高度一半   第二个字母的高度一半加上前面字母的高度
            int letterCenterY = i * itemHeight + itemHeight / 2 + getPaddingTop();
            //基线baseLine基于中心位置
            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            int dy = (fontMetrics.bottom - fontMetrics.top / 2) - fontMetrics.bottom;
            int baseLine = letterCenterY + dy;
            //x 绘制在最中间 = 宽度/2 - 文字/2
            int textWidth = (int) mPaint.measureText(mLetters[i]);
            int x = getWidth() / 2 - textWidth / 2;
            //当前字母高亮  用两个画笔（最好） / 改变颜色
            if (mLetters[i].equals(mCurrentTouchLetter)) {
                mPaint.setColor(mSideBarHightColor);
                canvas.drawText(mLetters[i], x, baseLine, mPaint);
            } else {
                mPaint.setColor(mSideBarColor);
                canvas.drawText(mLetters[i], x, baseLine, mPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //计算当前触摸字母  获取当前位置
                float currentMoveY = event.getY();
                //位置 = currentMoveY / 字母高度
                int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
                int currentPosition = (int) (currentMoveY / itemHeight);
                if (currentPosition < 0)
                    currentPosition = 0;
                if (currentPosition > mLetters.length - 1)
                    currentPosition = mLetters.length - 1;
                mCurrentTouchLetter = mLetters[currentPosition];

                if (mLetterTouchListener != null) {
                    mLetterTouchListener.touch(mCurrentTouchLetter, false);
                }
                //重新绘制
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (mLetterTouchListener != null) {
                    mLetterTouchListener.touch(mCurrentTouchLetter, true);
                }
                break;
        }
        return true;
    }

    public void setSideBarSize(int sideBarSize) {
        mSideBarSize = sideBarSize;
    }

    public void setSideBarColor(int sideBarColor) {
        mSideBarColor = sideBarColor;
    }

    public void setSideBarHightColor(int sideBarHightColor) {
        mSideBarHightColor = sideBarHightColor;
    }

    private LetterTouchListener mLetterTouchListener;

    public void setLetterTouchListener(LetterTouchListener listener) {
        this.mLetterTouchListener = listener;
    }

    public interface LetterTouchListener {
        /**
         * @param letter 触摸到的字
         * @param isUp   是否抬起
         */
        public void touch(CharSequence letter, boolean isUp);
    }
}
