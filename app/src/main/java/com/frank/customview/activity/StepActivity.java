package com.frank.customview.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;
import com.frank.customview.R;
import com.frank.customview.wight.stepview.StepView;

/**
 * Created by Frank on 2018/8/27.
 *
 * @fuction 仿QQ运动计步
 */
public class StepActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        final StepView stepview = findViewById(R.id.stepview);
        stepview.setStepMax(5000);
        //属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());//设置修饰动画效果 https://blog.csdn.net/jason0539/article/details/16370405
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float current = (float) animation.getAnimatedValue();
                stepview.setStepCurrent((int) current);
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }
}
