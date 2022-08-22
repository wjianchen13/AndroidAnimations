package com.cold.androidanimations.match;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class HeadView extends AppCompatImageView {

    public HeadView(Context context) {
        super(context);
    }

    public HeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void startAnim() {
        ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(this, "scaleX", 0f, 1f);
        ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(this, "scaleY", 0f, 1f);
        scaleX1.setInterpolator(new BounceInterpolator());
        scaleY1.setInterpolator(new BounceInterpolator());
        scaleX1.setDuration(2000);
        scaleY1.setDuration(2000);
        ObjectAnimator transY = ObjectAnimator.ofFloat(this, "translationY", 0, -80, 0);
        transY.setInterpolator(new LinearInterpolator());
        transY.setDuration(2500);

        ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.2f, 0f);
        ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.2f, 0f);
        scaleX2.setInterpolator(new LinearInterpolator());
        scaleY2.setInterpolator(new LinearInterpolator());
        scaleX2.setDuration(1000);
        scaleY2.setDuration(1000);


        AnimatorSet set = new AnimatorSet();
        set.play(scaleX1).with(scaleY1);
        set.play(transY).after(scaleY1);
        set.play(scaleX2).with(scaleY2).after(transY);
//        set.setDuration(4500);
//        set.setInterpolator(new BounceInterpolator());
        set.addListener(new MyAnimatorListener() {

            public void onAnimationStart(Animator animation, boolean isReverse) {
                log("onAnimationStart(Animator animator, boolean isReverse)");
                setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationStart(Animator animator) {
                log("onAnimationStart(Animator animator)");
                setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }
        });
        set.start();
    }

    class MyAnimatorListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation, boolean isReverse) {

        }

        @Override
        public void onAnimationEnd(Animator animation, boolean isReverse) {

        }

        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {

        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    }

    public void log(String str) {
        System.out.println("===========================> " + str);
    }

}
