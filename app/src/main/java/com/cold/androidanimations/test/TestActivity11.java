package com.cold.androidanimations.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;
import com.cold.androidanimations.test.wheel.WheelActivity;

public class TestActivity11 extends AppCompatActivity {

    private ImageView imgvBet;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test11);
        imgvBet = findViewById(R.id.imgv_bet_10);
    }

    /**
     * 转盘测试
     * @param
     * @return
     */
    public void onWheel(View v) {
        Intent it = new Intent();
        it.setClass(TestActivity11.this, WheelActivity.class);
        startActivity(it);
    }

    /**
     * 补间动画
     * @param
     * @return
     */
    public void onOther1(View v) {
//        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//                0.5f, Animation.RELATIVE_TO_SELF, 0.0f);
//        mShowAction.setDuration(300);
//        imgvBet.startAnimation(mShowAction);
////        imgvBet.setVisibility(View.VISIBLE);


        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setRepeatCount(Animation.INFINITE);  //  设置动画重复次数

        translateAnimation.setRepeatMode(Animation.REVERSE);
        //translateAnimation.setRepeatMode(Animation.RESTART);    //重新从头执行  
        //translateAnimation.setRepeatMode(Animation.REVERSE);  //反方向执行  

        animationSet.addAnimation(translateAnimation);
        imgvBet.startAnimation(translateAnimation);
    }
    
}
