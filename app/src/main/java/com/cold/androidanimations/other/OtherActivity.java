package com.cold.androidanimations.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.MainActivity;
import com.cold.androidanimations.R;

public class OtherActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }

    /**
     * TextView 从下往上显示文字
     * @param v
     */
    public void onSwitch(View v) {
        Intent it = new Intent();
        it.setClass(OtherActivity.this, SwitchTextActivity.class);
        startActivity(it);
    }

    /**
     * 展开子View动画
     * @param v
     */
    public void onExpand(View v) {
        Intent it = new Intent();
        it.setClass(OtherActivity.this, ExpandViewActivity.class);
        startActivity(it);
    }

    /**
     * 展开子View动画
     * @param v
     */
    public void onExpandTest(View v) {
        Intent it = new Intent();
        it.setClass(OtherActivity.this, ExpandViewTestActivity.class);
        startActivity(it);
    }

    /**
     * 字体颜色渐变
     * @param v
     */
    public void onGradient(View v) {
        Intent it = new Intent();
        it.setClass(OtherActivity.this, GradientTextViewActivity.class);
        startActivity(it);
    }

    /**
     * 揭露动画
     * @param v
     */
    public void onReveal(View v) {
        Intent it = new Intent();
        it.setClass(OtherActivity.this, RevealActivity.class);
        startActivity(it);
    }
}
