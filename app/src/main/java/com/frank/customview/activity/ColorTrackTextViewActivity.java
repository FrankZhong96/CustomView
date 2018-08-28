package com.frank.customview.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.frank.customview.R;
import com.frank.customview.fragment.ItemFragment;
import com.frank.customview.wight.colortracktextview.ColorTrackTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 2018/8/27.
 *
 * @fuction 字体变色，实现一个字体两种颜色
 */
public class ColorTrackTextViewActivity extends AppCompatActivity {

    private String[] mItems = {"全部", "待付款", "待发货", "待收货", "已完成", "退换货"};
    private LinearLayout mLl_layout;
    private List<ColorTrackTextView> mColorTrackTextViews;
    private ViewPager mViewpager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colortrack);
        mLl_layout = findViewById(R.id.ll_layout);
        mViewpager = findViewById(R.id.viewpager);
        mColorTrackTextViews = new ArrayList<>();
        initIndicator();
        initViewPager();
    }

    //初始化ViewPager
    private void initViewPager() {
        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return ItemFragment.newInstance(mItems[position]);
            }

            @Override
            public int getCount() {
                return mItems.length;
            }
        });
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.e("TAG", "pos" + position + "   positionOffset" + positionOffset);

                //position 当前页面位置   positionOffset 代表滚动的0-1 百分比
                ColorTrackTextView left = mColorTrackTextViews.get(position);
                left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                left.setCurrentProgress(1 - positionOffset);

                if (position == mItems.length - 1) return;//防止溢出

                ColorTrackTextView right = mColorTrackTextViews.get(position + 1);
                right.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                right.setCurrentProgress(positionOffset);

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    //初始化变色指示器
    private void initIndicator() {
        for (int i = 0; i < mItems.length; i++) {
            //动态添加颜色跟踪的TextView
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            ColorTrackTextView colorTrackTextView = new ColorTrackTextView(this);
            colorTrackTextView.setText(mItems[i]);
            colorTrackTextView.setTextSize(20);
            colorTrackTextView.setChangeColor(Color.RED);//设置变化颜色
            colorTrackTextView.setLayoutParams(params);
            //把新的加入LinearLayout容器
            mLl_layout.addView(colorTrackTextView);
            //加入集合
            mColorTrackTextViews.add(colorTrackTextView);
        }
    }


}
