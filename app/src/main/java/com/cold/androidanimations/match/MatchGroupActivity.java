package com.cold.androidanimations.match;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;
import com.cold.androidanimations.test.wheel.WheelActivity;

public class MatchGroupActivity extends AppCompatActivity {

    private MatchView mv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_group);
        mv = findViewById(R.id.mv);
    }

    /**
     * 转盘测试
     * @param
     * @return
     */
    public void onSingle(View v) {
        Intent it = new Intent();
        it.setClass(MatchGroupActivity.this, WheelActivity.class);
        startActivity(it);
    }

    /**
     * 补间动画
     * @param
     * @return
     */
    public void onGroup(View v) {

    }
    
}
