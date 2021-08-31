package com.cold.androidanimations.property.fans;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.cold.androidanimations.R;
import com.cold.androidanimations.rotate.RotateView;

public class TestFansActivity extends AppCompatActivity {

    private ImageView imgvFans;
    private Button btnTest;
    private ImageView imgvLight;

    private int duration = 2000 * 5; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fans);
        imgvFans = (ImageView)findViewById(R.id.imgv_fans);
        imgvLight = (ImageView)findViewById(R.id.imgv_light);
        btnTest = (Button) findViewById(R.id.btn_test);
    }


    public void onStart(View v) {
        ObjectAnimator animR1 = ObjectAnimator.ofFloat(imgvFans,"rotationY",0, 1800).setDuration(duration / 10 * 3);
        animR1.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator animR2 = ObjectAnimator.ofFloat(imgvFans,"rotationY",0, 360).setDuration(duration / 10 * 7);
        animR2.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator animSx1 =ObjectAnimator.ofFloat(imgvFans,"scaleX",0, 0.5f).setDuration(duration / 10 * 3); //X轴平移
        ObjectAnimator animSy1 = ObjectAnimator.ofFloat(imgvFans,"scaleY",0,0.5f).setDuration(duration / 10 * 3); //Y轴从零拉伸

        ObjectAnimator animSx2 =ObjectAnimator.ofFloat(imgvFans,"scaleX",0.5f, 1.0f).setDuration(duration / 10 * 2); //X轴平移
        ObjectAnimator animSy2 = ObjectAnimator.ofFloat(imgvFans,"scaleY",0.5f, 1.0f).setDuration(duration / 10 * 2); //Y轴从零拉伸

        ObjectAnimator animY1 = ObjectAnimator.ofFloat(imgvFans,"translationY",321, -324).setDuration(duration / 10 * 3); //Y轴从零拉伸
        animY1.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator animY2 = ObjectAnimator.ofFloat(imgvFans,"translationY",-324, 0).setDuration(duration / 10 * 2); //Y轴从零拉伸
        animY2.setInterpolator(new AccelerateInterpolator());

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animSx1, animSy1);
        set.play(animR2).after(animR1);
        set.play(animY1);
        set.play(animY2).after(animR1);
        set.play(animSx2).after(animR1);
        set.play(animSy2).after(animR1);
        set.start();

    }

    public void onStop(View v) {

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imgvLight,"rotation",0,360).setDuration(4800); //绕X轴翻转
        AnimatorSet set = new AnimatorSet();
        anim1.setRepeatCount(ValueAnimator.INFINITE);
        anim1.setRepeatMode(ValueAnimator.RESTART);
        anim1.setInterpolator(new LinearInterpolator());
        set.playTogether(anim1);
        set.start();//时间
    }

    public void onButton(View v) {
        ObjectAnimator anim1 =ObjectAnimator.ofFloat(btnTest,"scaleX",0, 0.5f); //X轴平移
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(btnTest,"scaleY",0,0.5f);//Y轴从零拉伸
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
//                ObjectAnimator.ofFloat(imgvFans,"rotationX",0,360),//绕X轴翻转
                //绕Y轴旋转
//                ObjectAnimator.ofFloat(imgvFans,"rotation",0,-90),//绕中心点逆时针旋转
//                ObjectAnimator.ofFloat(btnTest,"translationX",0,800)//X轴平移
//                ObjectAnimator.ofFloat(imgvFans,"translationY",0,90),//y轴平移
//                ObjectAnimator.ofFloat(imgvFans,"scaleX",1,1.5f),//X轴拉伸
//                ObjectAnimator.ofFloat(imgvFans,"scaleY",0,0.5f),//Y轴从零拉伸
//                ObjectAnimator.ofFloat(imgvFans,"alpha",1,0.25f,1)//透明度
                anim1, anim2
        );
        set.setDuration(5 * 1000).start();//时间
    }
}
