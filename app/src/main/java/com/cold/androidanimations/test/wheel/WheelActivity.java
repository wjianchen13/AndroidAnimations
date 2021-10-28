package com.cold.androidanimations.test.wheel;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class WheelActivity extends AppCompatActivity {
    
    private PrizeWheelView mWheelView;
    private TestPrizeWheelView mTestWheelView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        mWheelView = findViewById(R.id.wv);
        mTestWheelView = findViewById(R.id.wv_test);
    }
    
    public void onAdd(View v) {
        mWheelView.add("hello", "", false, false);
    }
    
    public void onRemove(View v) {
        mWheelView.stop(2, true);
    }

    public void onAdd11(View v) {
        mTestWheelView.add("hello", "", false, true);
    }

    public void onRemove11(View v) {
        mWheelView.removeIndex(2);
    }

}
