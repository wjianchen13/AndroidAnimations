package com.cold.androidanimations.property.objectanimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;

import com.cold.androidanimations.R;

public class ObjectAnimatorActivity extends AppCompatActivity {

    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
        mView = findViewById(R.id.btn_test2);
    }

    /**
     * 缩放
     * @param
     * @return
     */
    public void onAlpha(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "alpha", 1f, 0f, 1f);
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是透明度alpha
        // 动画效果是:常规 - 全透明 - 常规
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * 旋转
     * @param
     * @return
     */
    public void onRotate(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "rotation", 0f, -360f);

        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是旋转alpha
        // 动画效果是:0 - 360
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * 平移
     * @param
     * @return
     */
    public void onTranslate(View v) {
        // 相对当前坐标偏移
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "translationX", 0, 300);


        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationY"
        // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(5000);
        animator.start();
    }

    /**
     * 平移
     * @param
     * @return
     */
    public void onTranslate1(View v) {
        // 相对当前坐标偏移
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "translationX", 0, -300);


        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationY"
        // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.start();
    }

    /**
     * 缩放
     * @param
     * @return
     */
    public void onScale(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView, "scaleX", 1f, 3f, 1f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mView, "scaleY", 1f, 3f, 1f);
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴缩放
        // 动画效果是:放大到3倍,再缩小到初始大小
        AnimatorSet set = new AnimatorSet();
        set.play(animator).with(animator1);
        set.setDuration(5000);
        set.start();
    }

    /**
     * 测试
     * @param
     * @return
     */
    public void onTest(View v) {
        mView.clearAnimation();
        mView.setRotation(0);
    }
    
    
}
