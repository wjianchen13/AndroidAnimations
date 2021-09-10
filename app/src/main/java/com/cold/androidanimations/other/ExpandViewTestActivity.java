package com.cold.androidanimations.other;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class ExpandViewTestActivity extends AppCompatActivity {
    
    private static final int duration = 2000;
    
    private ExpandLyaout elView;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_view_test);
        elView = findViewById(R.id.llyt_expand);
    }
    
    /**
     * 第一个item缩放控制
     * @param v
     */
    public void onAdd1(View v) {
        elView.insertView(1);
    }

    /**
     * 第一个item缩放控制
     * @param v
     */
    public void onAdd2(View v) {
        elView.insertView(2);
    }

    /**
     * 第一个item缩放控制
     * @param v
     */
    public void onAdd3(View v) {
        elView.insertView(3);
    }

    /**
     * 第一个item缩放控制
     * @param v
     */
    public void onAdd4(View v) {
        elView.insertView(4);
    }

    /**
     * 
     * @param v
     */
    public void onAdd5(View v) {

    }
    
    
    
    
    
}
