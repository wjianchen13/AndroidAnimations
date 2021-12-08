package com.cold.androidanimations.open;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class Rotate3dActivity extends AppCompatActivity {
    private ImageView[] imageViews = new ImageView[2];
    private int index = 0;
//        private ObjectAnimator frontAnim;
//    private ObjectAnimator backAnim;
    private Animation frontAnim;
    private Animation backAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_3d);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        imageViews[0] = findViewById(R.id.front_view);
        imageViews[0].setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.ic_jn,options));
        imageViews[1] = findViewById(R.id.back_view);
        imageViews[1].setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.ic_m,options));

//        frontAnim = new ObjectAnimator();
//        frontAnim.setFloatValues(0,90f);
//        frontAnim.setDuration(1000);
//        frontAnim.setInterpolator(new LinearInterpolator());
//        frontAnim.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                imageViews[index].setVisibility(View.INVISIBLE);
//                index = (index+1)%2;
//                imageViews[index].setVisibility(View.VISIBLE);
//                backAnim.start();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        ValueAnimator.AnimatorUpdateListener updateListener = new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (Float) animation.getAnimatedValue();
//                imageViews[index].setRotationY(value);
//            }
//        };
//        frontAnim.addUpdateListener(updateListener);
//
//        backAnim = new ObjectAnimator();
//        backAnim.setFloatValues(270f,360f);
//        backAnim.setDuration(1000);
//        backAnim.setInterpolator(new LinearInterpolator());
//        backAnim.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                isAnim = false;
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//            }
//        });
//        backAnim.addUpdateListener(updateListener);

    }
    private boolean isAnim = false;
    public void startAnimation(View view){
        if(isAnim){
            return;
        }
        isAnim = true;
        index = 0;
        imageViews[index].setVisibility(View.VISIBLE);
        imageViews[index].clearAnimation();
        imageViews[index + 1].setVisibility(View.INVISIBLE);
        if(frontAnim == null){
            final float centerX = imageViews[0].getWidth() / 2.0f;
            final float centerY = imageViews[0].getHeight() / 2.0f;
            frontAnim= new Rotate3dAnimation(this,0, -90, centerX, centerY, 0f, true,0);
            frontAnim.setDuration(1000);                         //设置动画时长
            frontAnim.setFillAfter(true);                        //保持旋转后效果
            frontAnim.setInterpolator(new LinearInterpolator());   //设置插值器

            backAnim = new Rotate3dAnimation(this,90, 0, centerX, centerY, 0f, true,0);
            backAnim.setDuration(1000);
            backAnim.setFillAfter(true);
            backAnim.setInterpolator(new LinearInterpolator());

            frontAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    imageViews[index].setVisibility(View.INVISIBLE);
                    imageViews[index].clearAnimation();
                    index = (index+1)%2;
                    imageViews[index].setVisibility(View.VISIBLE);
                    imageViews[index].startAnimation(backAnim);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            backAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isAnim = false;
                    imageViews[index].clearAnimation();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
        imageViews[index].startAnimation(frontAnim);


//        frontAnim.start();
    }
}