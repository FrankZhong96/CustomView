package com.frank.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.frank.customview.activity.ColorTrackTextViewActivity;
import com.frank.customview.activity.StepActivity;
import com.frank.customview.wight.colortracktextview.ColorTrackTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_stepview = findViewById(R.id.btn_stepview);
        Button btn_colortrack = findViewById(R.id.btn_colortrack);
        btn_stepview.setOnClickListener(this);
        btn_colortrack.setOnClickListener(this);
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
        }
    }
}
