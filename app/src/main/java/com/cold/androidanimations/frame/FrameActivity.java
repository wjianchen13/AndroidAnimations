package com.cold.androidanimations.frame;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.cold.androidanimations.R;

public class FrameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
    }

    /**
     * 属性动画
     * @param
     * @return
     */
    public void onProperty(View v) {

    }

    /**
     * 补间动画
     * @param
     * @return
     */
    public void onView(View v) {

    }

    /**
     * 帧动画
     * @param
     * @return
     */
    public void onDrawable(View v) {

    }
}
