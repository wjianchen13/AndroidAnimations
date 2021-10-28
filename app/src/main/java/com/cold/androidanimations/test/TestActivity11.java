package com.cold.androidanimations.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;
import com.cold.androidanimations.test.wheel.WheelActivity;

public class TestActivity11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test11);
    }

    /**
     * 转盘测试
     * @param
     * @return
     */
    public void onWheel(View v) {
        Intent it = new Intent();
        it.setClass(TestActivity11.this, WheelActivity.class);
        startActivity(it);
    }

    /**
     * 补间动画
     * @param
     * @return
     */
    public void onOther1(View v) {

    }
    
}
