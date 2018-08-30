package com.frank.customview.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.frank.customview.R;
import com.frank.customview.wight.lettersidebar.LetterSideBar;

/**
 * Created by Frank on 2018/8/30.
 *
 * @fuction
 */
public class LetterSideBarActivity extends AppCompatActivity implements LetterSideBar.LetterTouchListener {

    private TextView mLettertv;
    private LetterSideBar mLettersidebar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);
        mLettertv = findViewById(R.id.lettertv);
        mLettersidebar = findViewById(R.id.lettersidebar);
        mLettersidebar.setLetterTouchListener(this);
    }

    @Override
    public void touch(CharSequence letter, boolean isUp) {
        if (isUp) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    mLettertv.setVisibility(View.GONE);
                }
            }, 500);
        } else {
            mLettertv.setVisibility(View.VISIBLE);
            mLettertv.setText(letter);
        }
    }
}
