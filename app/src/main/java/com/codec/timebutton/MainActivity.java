package com.codec.timebutton;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codec.libraty.TimeButtonView;

public class MainActivity extends AppCompatActivity {

    private TimeButtonView mTimeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTimeBtn = (TimeButtonView) findViewById(R.id.time_btn);
        mTimeBtn.setOnTimeButtonClickListener(new TimeButtonView.OnTimeButtonClickListener() {
            @Override
            public void onStart() {
                mTimeBtn.setBackgroundColor(Color.GRAY);
            }

            @Override
            public void onFinish() {
                mTimeBtn.setBackgroundColor(Color.parseColor("#6699ff"));
            }
        });
    }
}
