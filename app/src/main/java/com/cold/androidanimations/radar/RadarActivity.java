package com.cold.androidanimations.radar;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.cold.androidanimations.R;

public class RadarActivity extends AppCompatActivity implements View.OnClickListener{

//    private ImageView imgvPointer;
    private RadarView rvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
//        imgvPointer = (ImageView) findViewById(R.id.imgv_pointer);
        rvTest = (RadarView) findViewById(R.id.rv_test);
        rvTest.setOnClickListener(this);
    }

    public void onStart(View v) {
//        ObjectAnimator animLightR = ObjectAnimator.ofFloat(imgvPointer, "rotation", 0, 360).setDuration(1000); // 绕X轴翻转
//        animLightR.setRepeatCount(ValueAnimator.INFINITE);
//        animLightR.setRepeatMode(ValueAnimator.RESTART);
//        animLightR.setInterpolator(new LinearInterpolator());
//        AnimatorSet set = new AnimatorSet();
//        set.play(animLightR);
//        set.start();
        rvTest.start();
    }

    public void onStop(View v) {
        rvTest.stop();
    }

    @Override
    public void onClick(View v) {

    }
}
