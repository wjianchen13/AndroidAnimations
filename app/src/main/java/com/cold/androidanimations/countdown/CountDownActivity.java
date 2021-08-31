package com.cold.androidanimations.countdown;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cold.androidanimations.R;
import com.cold.androidanimations.property.valueanimator.BizerPointEvaluator;

public class CountDownActivity extends AppCompatActivity {

    private View mView;
    private int duration = 1000;
    private float initX;
    private float initY;
    private CountDownView cdvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        mView = findViewById(R.id.btn_test2);
        cdvTest = (CountDownView)findViewById(R.id.cdv_test);
    }

    public void onTest(View v) {
//        if(cdvTest != null)
//            cdvTest.startCountDown(10);

        CustomCountDownTimer mEndTimer = new CustomCountDownTimer(10 * 1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                int sec = (int)millisUntilFinished / 1000;
                Log.i("CountDownActivity", "=========================================================================");
                Log.i("CountDownActivity", "millisUntilFinished = " + millisUntilFinished);
                Log.i("CountDownActivity", "sec = " + sec);
            }

            @Override
            public void onFinish() {
                Log.i("CountDownActivity", "onFinish");
            }
        };
        mEndTimer.start();
    }

    /**
     * ;
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
    public void onTest1(View v) {
        // 平移动画
//        ObjectAnimator translation = ObjectAnimator.ofFloat(mView, "translationY", 0, -500).setDuration(duration);
        initX = mView.getX();
        initY = mView.getY();
//        ObjectAnimator translation = ObjectAnimator.ofFloat(mView, mView.X, mView.Y, getPath()).setDuration(duration);
        ValueAnimator translation = ValueAnimator.ofObject(new BizerPointEvaluator(), new PointF((float)initX, (float)initY), new PointF((float)initX, (float)(initY - 600)));
        translation.setDuration(duration);
        translation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF)animation.getAnimatedValue();
                mView.setX(pointF.x);
                mView.setY(pointF.y);
            }
        });

//        translation.start();


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
//        animSet.play(translation);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(CountDownActivity.this, "end animation", Toast.LENGTH_SHORT).show();
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
        path.cubicTo(x - 400, y - 200, x + 400, y - 400, x, y - 600);
        return path;
    }

}
