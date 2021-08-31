package com.cold.androidanimations.property;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Path;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.cold.androidanimations.R;

public class TestActivity extends AppCompatActivity {

    private View mView;
    private int duration = 10000;
    private float initX;
    private float initY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
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
     * AnimatorSet.before(Animator anim) ：  将现有动画插入到传入的动画之前执行
     */
    @TargetApi(21)
    public void onTest(View v) {
        // 平移动画
//        ObjectAnimator translation = ObjectAnimator.ofFloat(mView, "translationY", 0, -500).setDuration(duration);
        initX = mView.getX();
        initY = mView.getY();
        ObjectAnimator translation = ObjectAnimator.ofFloat(mView, mView.X, mView.Y, getPath()).setDuration(duration);
        // 放大
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mView, "scaleX", 0.3f, 1f).setDuration(duration / 5);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mView, "scaleY", 0.3f, 1f).setDuration(duration / 5);
        // 透明度动画
        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(mView, "alpha", 0.2f, 1f).setDuration(duration / 5);
        ObjectAnimator alpha2 = ObjectAnimator.ofFloat(mView, "alpha", 1f, 0f).setDuration(duration / 5);
        alpha2.setStartDelay(duration / 5 * 3);

        // 步骤2：创建组合动画的对象
        AnimatorSet animSet = new AnimatorSet();

        // 步骤3：根据需求组合动画
        animSet.play(translation).with(scaleX).with(scaleY).with(alpha1);
        animSet.play(alpha2).after(alpha1);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(TestActivity.this, "end animation", Toast.LENGTH_SHORT).show();
                mView.setX(initX);
                mView.setY(initY);
            }
        });
        // 步骤4：启动动画
        animSet.start();
    }

    /**
     * 圆弧动画
     * @return
     */
    private Path getPath() {
        Path  path =  new Path();
        int x = (int)mView.getX();
        int y = (int)mView.getY();
        path.moveTo(x, y);
        path.cubicTo(x - 100, y - 200, x + 100, y - 400, x, y - 600);
        return path;
    }

}
