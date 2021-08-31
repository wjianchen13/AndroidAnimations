package com.cold.androidanimations.pan;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.cold.androidanimations.R;

public class PanActivity extends AppCompatActivity implements View.OnClickListener{
    
    private PanView pvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pan);
        pvTest = (PanView)findViewById(R.id.pv_test);
        pvTest.setOnClickListener(this);
    }

    public void onStart(View v) {
        rotation(pvTest);
    }

    public void onStop(View v) {

    }

    @Override
    public void onClick(View v) {

    }

    private void rotation(View mArrowImageView) {
        mArrowImageView.setPivotX(450);
        mArrowImageView.setPivotY(450);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                mArrowImageView, "rotation", 0, 7200);
        objectAnimator.setDuration(3000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();

    }
}
