package com.cold.androidanimations.other;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class RevealActivity extends AppCompatActivity {
    
    private RevealLayout mRevealLayout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        mRevealLayout = findViewById(R.id.rl_test);
    }
    
    /**
     * 测试一下
     * @param v
     */
    public void onTest1(View v) {
        mRevealLayout.startAnimal(RevealLayout.AnimaType.Circle);
    }


    
}
