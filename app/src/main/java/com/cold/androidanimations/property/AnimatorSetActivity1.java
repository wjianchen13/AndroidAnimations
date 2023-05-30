package com.cold.androidanimations.property;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Path;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.cold.androidanimations.R;
import com.cold.androidanimations.Utils;

/**
 * AnimatorSet 组合动画测试
 */
public class AnimatorSetActivity1 extends AppCompatActivity {

    private TextView tvTest;
    private int duration = 10000;
    private int factor = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animtor_set1);
        tvTest = findViewById(R.id.tv_test);
    }

    /**
     * 返回屏幕宽(px)
     */
    public int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * ViewPropertyAnimator
     * @param
     * @return
     * AnimatorSet.play(Animator anim)   ：播放当前动画
     * AnimatorSet.after(long delay)   ：将现有动画延迟x毫秒后执行
     * AnimatorSet.with(Animator anim)   ：将现有动画和传入的动画同时执行
     * AnimatorSet.after(Animator anim)   ：将现有动画插入到传入的动画之后执行
     * AnimatorSet.before(Animator anim) ：  将现有动画插入到传入的动画之前执行
     */
    public void onTest1(View v) {
        ObjectAnimator transAnim1 = ObjectAnimator.ofFloat(tvTest, "translationX", getScreenWidth() - Utils.dip2px(this, 9), 0);
        transAnim1.setDuration(320 * factor);

        ObjectAnimator transAnim2 = ObjectAnimator.ofFloat(tvTest, "translationX", 0, Utils.dip2px(this, 40));
        transAnim2.setDuration(160 * factor);

        ObjectAnimator transAnim3 = ObjectAnimator.ofFloat(tvTest, "translationX", Utils.dip2px(this, 40), Utils.dip2px(this, 56));
        transAnim3.setDuration(160 * factor);

        ObjectAnimator transAnim4 = ObjectAnimator.ofFloat(tvTest, "translationX", Utils.dip2px(this, 56), -getScreenWidth());
        transAnim4.setDuration(400 * factor);

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(transAnim1).before(transAnim2);
        animSet.play(transAnim3).after(2960 * factor);
        animSet.play(transAnim4).after(transAnim3);
//        animSet.play(alpha2).after(alpha1);
        animSet.setInterpolator(new LinearInterpolator());
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(AnimatorSetActivity1.this, "end animation", Toast.LENGTH_SHORT).show();
            }
        });
        // 步骤4：启动动画
        animSet.start();
    }

    public void onTest2(View v) {

    }

    /**
     * 圆弧动画
     * @return
     */
    private Path getPath() {
        Path  path =  new Path();
        int x = (int)tvTest.getX();
        int y = (int)tvTest.getY();
        path.moveTo(x, y);
        path.cubicTo(x - 100, y - 200, x + 100, y - 400, x, y - 600);
        return path;
    }

}
