package com.cold.androidanimations.open;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class TestOpenActivity extends AppCompatActivity {

    private OpenView mOpenView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_open);
        mOpenView = findViewById(R.id.ov_test);
        
    }
    
    public void onTest(View v) {
        mOpenView.open(R.drawable.ic_poker_a);
    }

    public void onTest1(View v) {
        mOpenView.reset();
    }

    public void onTest2(View v) {
        mOpenView.light();
    }
}
