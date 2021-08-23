package com.cold.androidanimations.fans;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cold.androidanimations.R;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.AlphaModifier;

public class FansActivity extends AppCompatActivity {

    private ImageView imgvFans;
    private Button btnTest;
    private LinearLayout llytTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fans);
        btnTest = (Button)findViewById(R.id.btn_test);
        llytTest = (LinearLayout)findViewById(R.id.llyt_test);
        imgvFans = (ImageView)findViewById(R.id.imgv_fans);
    }

    public void onStart(View v) {
        new ParticleSystem(llytTest, 1000, ContextCompat.getDrawable(this, R.drawable.white_start), 3000)
                .addModifier(new AlphaModifier(255, 75, 0, 2000)) // 透明度范围
                .setSpeedModuleAndAngleRange(0.05f, 0.3f, 0, 360) // 速度范围，角度范围
                .setRotationSpeedRange(0, 90) // 旋转角度范围
                .setScaleRange(0.6f, 1.2f) // 缩放范围
                .setAcceleration(0, 90)
//                .oneShot(btnTest, 200);
                .emit(llytTest, 12);
    }

    public void onStop(View v) {

    }

    public void onButton(View v) {

    }
}
