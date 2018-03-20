package com.codec.libraty;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;



public class TimeButtonView extends TextView {

    private int time = 60;

    private String prefix = "";
    private String postfix = "";
    private String end = "重新获取";

    public TimeButtonView(Context context) {
        this(context,null);
    }

    public TimeButtonView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimeButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setTimeText();
    }

    private void init() {
        setPadding(10,8,10,8);
        setGravity(Gravity.CENTER);
    }

    public void setTime(int time) {
        this.time = time;
    }



    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    private void setTimeText(){
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onStart();
                    startAni();
                    setClickable(false);
                }
            }
        });
    }

    private void startAni() {
        ValueAnimator animator = ValueAnimator.ofInt(time,0);
        animator.setDuration(time*1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int t = (int) animation.getAnimatedValue();
                String text = prefix+t+"s"+postfix;
                setText(text);
            }
        });
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (listener!=null){
                    listener.onFinish();
                    setText(end);
                    setClickable(true);
                }
            }
        });
    }

    private OnTimeButtonClickListener listener;

    public  void setOnTimeButtonClickListener(OnTimeButtonClickListener listener){
        this.listener = listener;
    }

    public interface OnTimeButtonClickListener{
        void onStart();
        void onFinish();
    }
}
