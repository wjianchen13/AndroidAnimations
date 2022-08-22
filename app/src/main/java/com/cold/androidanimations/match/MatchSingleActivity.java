package com.cold.androidanimations.match;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class MatchSingleActivity extends AppCompatActivity {

    private HeadView imgvHead;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_single);
        imgvHead = findViewById(R.id.imgv_head);
    }

    /**
     * 转盘测试
     * @param
     * @return
     */
    public void onTest1(View v) {
        imgvHead.startAnim();
//        ObjectAnimator animator = ObjectAnimator.ofFloat(imgvHead, "scaleX", 0f, 1f);
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgvHead, "scaleY", 0f, 1f);
//        // 表示的是:
//        // 动画作用对象是mButton
//        // 动画作用的对象的属性是X轴缩放
//        // 动画效果是:放大到3倍,再缩小到初始大小
//        AnimatorSet set = new AnimatorSet();
//        set.play(animator).with(animator1);
//        set.setDuration(2000);
//        set.setInterpolator(new BounceInterpolator());
//        set.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation, boolean isReverse) {
//                if(imgvHead != null)
//                    imgvHead.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation, boolean isReverse) {
//
//            }
//
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
//        set.start();
    }

    /**
     * 补间动画
     * @param
     * @return
     */
    public void onTest2(View v) {
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
        imgvHead.startAnimation(translateAnimation);
    }
    
}
