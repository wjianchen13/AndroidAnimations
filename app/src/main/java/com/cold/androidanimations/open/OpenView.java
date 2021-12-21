package com.cold.androidanimations.open;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cold.androidanimations.R;

public class OpenView extends RelativeLayout {

    private ImageView imgvFront;
    private ImageView imgvBack;
    private ImageView imgvLight;

    private Animation frontAnim;
    private Animation backAnim;
    
    private boolean isReset;
    
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
        imgvLight = findViewById(R.id.imgv_light);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
//        imgvFront.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.ic_poker_a,options));
        imgvBack.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.ic_k, options));
        imgvFront.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    public void reset() {
        isReset = true;
        imgvFront.clearAnimation();
        imgvFront.setVisibility(View.VISIBLE);

        imgvBack.clearAnimation();
        imgvBack.setVisibility(View.INVISIBLE);

        imgvLight.clearAnimation();
        imgvLight.setVisibility(View.INVISIBLE);

//        frontAnim.cancel();
//        backAnim.cancel();
    }
    
    public void open(int resId){
        isReset = false;
        imgvFront.setVisibility(View.VISIBLE);

        imgvFront.clearAnimation();
        imgvBack.setVisibility(View.INVISIBLE);
        imgvBack.clearAnimation();
        imgvBack.setImageResource(resId);
        if(frontAnim == null){
            final float centerX = imgvFront.getWidth() / 2.0f;
            final float centerY = imgvFront.getHeight() / 2.0f;
            frontAnim= new Rotate3dAnimation(getContext(),0, -90, centerX, centerY, 0f, true,0);
            frontAnim.setDuration(3130);
            frontAnim.setFillAfter(true);
            frontAnim.setInterpolator(new LinearInterpolator()); 

            backAnim = new Rotate3dAnimation(getContext(),90, 0, centerX, centerY, 0f, true,0);
            backAnim.setDuration(3130);
            backAnim.setFillAfter(true);
            backAnim.setInterpolator(new LinearInterpolator());

            frontAnim.setAnimationListener(new AnimationListenerAdapter() {
                
                @Override
                public void onAnimationEnd(Animation animation) {
                    if(!isReset) {
                        imgvFront.setVisibility(View.INVISIBLE);
                        imgvFront.clearAnimation();
                        System.out.println("==========================================> frontAnim end");
                        imgvBack.setVisibility(View.VISIBLE);
                        imgvBack.startAnimation(backAnim);
                    }
                    
                }
                
            });
            backAnim.setAnimationListener(new AnimationListenerAdapter() {

                @Override
                public void onAnimationEnd(Animation animation) {
                    imgvBack.clearAnimation();
                }
                
            });
        }
        imgvFront.startAnimation(frontAnim);
    }
    
    public void light() {
        if(imgvLight != null) {
            imgvLight.clearAnimation();
            AlphaAnimation alphaAnim = new AlphaAnimation(0.3f, 1.0f);
            alphaAnim.setDuration(2300);
            alphaAnim.setRepeatMode(Animation.REVERSE);
            alphaAnim.setRepeatCount(2);
            alphaAnim.setAnimationListener(new AnimationListenerAdapter() {
    
                @Override
                public void onAnimationStart(Animation animation) {
                    imgvLight.setVisibility(View.VISIBLE);
                }
            
            });
            imgvLight.startAnimation(alphaAnim);
        }
    }
    
    public class AnimationListenerAdapter implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            
        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
    
    
}
