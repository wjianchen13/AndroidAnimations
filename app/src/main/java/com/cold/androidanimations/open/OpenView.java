package com.cold.androidanimations.open;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cold.androidanimations.R;

public class OpenView extends RelativeLayout {

    private ImageView imgvFront;
    private ImageView imgvBack;

    private Animation frontAnim;
    private Animation backAnim;
    
    public OpenView(Context context) {
        super(context);
        initView(context);
    }

    public OpenView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public OpenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.view_open, this);
        imgvFront = findViewById(R.id.imgv_front);
        imgvBack = findViewById(R.id.imgv_back);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        imgvFront.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.ic_jn,options));
        imgvBack.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.ic_m, options));
    }

    public void open(){
        imgvFront.setVisibility(View.VISIBLE);
        imgvFront.clearAnimation();
        imgvBack.setVisibility(View.INVISIBLE);
        imgvBack.clearAnimation();
        if(frontAnim == null){
            final float centerX = imgvFront.getWidth() / 2.0f;
            final float centerY = imgvFront.getHeight() / 2.0f;
            frontAnim= new Rotate3dAnimation(getContext(),0, -90, centerX, centerY, 0f, true,0);
            frontAnim.setDuration(130);
            frontAnim.setFillAfter(true);
            frontAnim.setInterpolator(new LinearInterpolator()); 

            backAnim = new Rotate3dAnimation(getContext(),90, 0, centerX, centerY, 0f, true,0);
            backAnim.setDuration(130);
            backAnim.setFillAfter(true);
            backAnim.setInterpolator(new LinearInterpolator());

            frontAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    imgvFront.setVisibility(View.INVISIBLE);
                    imgvFront.clearAnimation();

                    imgvBack.setVisibility(View.VISIBLE);
                    imgvBack.startAnimation(backAnim);
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
                    imgvBack.clearAnimation();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
        imgvFront.startAnimation(frontAnim);


//        frontAnim.start();
    }
    
    
}
