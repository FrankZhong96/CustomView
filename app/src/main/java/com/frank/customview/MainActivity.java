package com.frank.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.frank.customview.activity.ColorTrackTextViewActivity;
import com.frank.customview.activity.LetterSideBarActivity;
import com.frank.customview.activity.StepActivity;
import com.frank.customview.wight.colortracktextview.ColorTrackTextView;
import com.frank.customview.wight.lettersidebar.LetterSideBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_stepview = findViewById(R.id.btn_stepview);
        Button btn_colortrack = findViewById(R.id.btn_colortrack);
        Button btn_letter = findViewById(R.id.btn_letter);
        btn_stepview.setOnClickListener(this);
        btn_colortrack.setOnClickListener(this);
        btn_letter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_stepview:
                startActivity(new Intent(MainActivity.this, StepActivity.class));
                break;
            case R.id.btn_colortrack:
                startActivity(new Intent(MainActivity.this, ColorTrackTextViewActivity.class));
                break;
            case R.id.btn_letter:
                startActivity(new Intent(MainActivity.this, LetterSideBarActivity.class));
                break;
        }
    }
}
