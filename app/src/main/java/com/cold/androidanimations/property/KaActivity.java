package com.cold.androidanimations.property;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;
import com.cold.androidanimations.kk.Utils;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class KaActivity extends AppCompatActivity {

    private View tv1;
    ValueAnimator dinoAnimator;
    ObjectAnimator dinoAnimator1;
    RelativeLayout rlytFrame;
    private View tvTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ka);
        tv1 = findViewById(R.id.tv_test1);
        rlytFrame = findViewById(R.id.rlyt_frame);
        tvTip = findViewById(R.id.tv_tip);
    }

    public void onStart1(View v) {
        dinoAnimator1 = ObjectAnimator.ofFloat(tv1, "translationX", Utils.dip2px(this, 360), -Utils.dip2px(this, 360));
//        dinoAnimator1 = ObjectAnimator.ofFloat(tv1, "translationX", Utils.dip2px(this, 360), -Utils.dip2px(this, 360));
//        dinoAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tv1, animatorX);
        dinoAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
//                tv1.setX(value);
            }
        });
//        ValueAnimator.setFrameDelay(1500L);
        dinoAnimator1.setFrameDelay(1500L);
        dinoAnimator1.setDuration(5000);
        dinoAnimator1.setTarget(tv1);
        dinoAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        dinoAnimator1.setRepeatMode(ValueAnimator.RESTART);
        dinoAnimator1.setInterpolator(new LinearInterpolator());
        dinoAnimator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                tv1.setTag(null);
                tv1.setVisibility(View.VISIBLE);
                tv1.setX(0);

            }

            @Override
            public void onAnimationStart(Animator animation) {
                tv1.setTag(dinoAnimator);
                tv1.setVisibility(View.VISIBLE);
            }
        });
        dinoAnimator1.start();
    }
    
    public void onStart2(View v) {
        PropertyValuesHolder  animatorX = PropertyValuesHolder.ofFloat("translationX", Utils.dip2px(this, 360), -Utils.dip2px(this, 360));
//        dinoAnimator1 = ObjectAnimator.ofFloat(tv1, "translationX", Utils.dip2px(this, 360), -Utils.dip2px(this, 360));
        dinoAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tv1, animatorX);
        dinoAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
//                tv1.setX(value);
            }
        });
        ValueAnimator.setFrameDelay(1500L);
        dinoAnimator1.setDuration(5000);
        dinoAnimator1.setTarget(tv1);
        dinoAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        dinoAnimator1.setRepeatMode(ValueAnimator.RESTART);
        dinoAnimator1.setInterpolator(new LinearInterpolator());
        dinoAnimator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                tv1.setTag(null);
                tv1.setVisibility(View.VISIBLE);
                tv1.setX(0);

            }

            @Override
            public void onAnimationStart(Animator animation) {
                tv1.setTag(dinoAnimator);
                tv1.setVisibility(View.VISIBLE);
            }
        });
        dinoAnimator1.start();
    }

    public void onStart(View v) {

        dinoAnimator = ValueAnimator.ofFloat(Utils.dip2px(this, 360), -Utils.dip2px(this, 360));
        dinoAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                tv1.setX(value);
            }
        });
        ValueAnimator.setFrameDelay(50L);
        dinoAnimator.setDuration(5000);
        dinoAnimator.setTarget(tv1);
        dinoAnimator.setRepeatCount(ValueAnimator.INFINITE);
        dinoAnimator.setRepeatMode(ValueAnimator.RESTART);
        dinoAnimator.setInterpolator(new LinearInterpolator());
        dinoAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                tv1.setTag(null);
                tv1.setVisibility(View.VISIBLE);
                tv1.setX(0);

            }

            @Override
            public void onAnimationStart(Animator animation) {
                tv1.setTag(dinoAnimator);
                tv1.setVisibility(View.VISIBLE);
            }
        });
        dinoAnimator.start();
    }
    
    /**
     * 显示toast
     */
    public void onShow(View v) {
//        Toast.makeText(KaActivity.this, "testtesttest", Toast.LENGTH_SHORT).show();

//        Snackbar bar = Snackbar.make(rlytFrame, "snack弹出来了", Snackbar.LENGTH_SHORT)
//                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
//                .setAction("action", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(KaActivity.this, "toast", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setCallback(new Snackbar.Callback(){
//                    @Override
//                    public void onShown(Snackbar sb) {
//                        super.onShown(sb);
//                    }
//
//                    @Override
//                    public void onDismissed(Snackbar transientBottomBar, int event) {
//                        super.onDismissed(transientBottomBar, event);
//                    }
//                })
//                .setActionTextColor(Color.RED);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(bar.getView().getLayoutParams().width,bar.getView().getLayoutParams().height);
//        params.gravity = Gravity.CENTER;
//        bar.getView().setLayoutParams(params);
//        bar.show();

        tvTip.setVisibility(View.GONE);
        tvTip.setVisibility(View.VISIBLE);

    }
    
    
}
