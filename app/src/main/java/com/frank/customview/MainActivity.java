package com.frank.customview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.frank.customview.wight.stepview.StepView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }
}
