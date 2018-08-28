package com.frank.customview.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.frank.customview.R;
import com.frank.customview.wight.colortracktextview.ColorTrackTextView;

/**
 * Created by Frank on 2018/8/28.
 *
 * @fuction
 */
public class ItemFragment extends Fragment implements View.OnClickListener{

    private ColorTrackTextView mColortrack;

    public static ItemFragment newInstance(String item){
        ItemFragment itemFragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",item);
        itemFragment.setArguments(bundle);
        return itemFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item,null);
        mColortrack = view.findViewById(R.id.colortrack);
        Button btn_left_right = view.findViewById(R.id.btn_left_right);
        Button btn_right_left = view.findViewById(R.id.btn_right_left);
        TextView text = view.findViewById(R.id.tv);
        btn_left_right.setOnClickListener(this);
        btn_right_left.setOnClickListener(this);
        Bundle bundle = getArguments();
        text.setText(bundle.getString("title"));
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left_right://左往右
                setColorTrackDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                break;
            case R.id.btn_right_left://右往左
                setColorTrackDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                break;
        }
    }

    public void setColorTrackDirection(ColorTrackTextView.Direction direction){
        mColortrack.setDirection(direction);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentProgress = (float) animation.getAnimatedValue();
                mColortrack.setCurrentProgress(currentProgress);
            }
        });
        valueAnimator.start();
    }
}
