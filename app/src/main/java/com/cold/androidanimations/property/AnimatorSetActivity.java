package com.cold.androidanimations.property;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cold.androidanimations.R;

public class AnimatorSetActivity extends AppCompatActivity {

    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animatorset);
        mView = findViewById(R.id.btn_test2);
    }

    /**
     * ViewPropertyAnimator
     * @param
     * @return
     * AnimatorSet.play(Animator anim)   ：播放当前动画
     * AnimatorSet.after(long delay)   ：将现有动画延迟x毫秒后执行
     * AnimatorSet.with(Animator anim)   ：将现有动画和传入的动画同时执行
     * AnimatorSet.after(Animator anim)   ：将现有动画插入到传入的动画之后执行
     *  AnimatorSet.before(Animator anim) ：  将现有动画插入到传入的动画之前执行
     */
    public void onTest(View v) {
        // 步骤1：设置需要组合的动画效果
        ObjectAnimator translation = ObjectAnimator.ofFloat(mView, "translationX", 0, 300, 0);
        // 平移动画
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mView, "rotation", 0f, 360f);
        // 旋转动画
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mView, "alpha", 1f, 0f, 1f);
        // 透明度动画

        // 步骤2：创建组合动画的对象
        AnimatorSet animSet = new AnimatorSet();

        // 步骤3：根据需求组合动画
        animSet.play(translation).with(rotate).before(alpha);
        animSet.setDuration(5000);

        // 步骤4：启动动画
        animSet.start();
    }

}
