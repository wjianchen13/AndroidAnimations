package com.cold.androidanimations.match;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class MatchActivity extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
    }

    /**
     * 转盘测试
     * @param
     * @return
     */
    public void onSingle(View v) {
        startActivity(new Intent(this, MatchSingleActivity.class));
    }

    /**
     * 补间动画
     * @param
     * @return
     */
    public void onGroup(View v) {
        startActivity(new Intent(this, MatchGroupActivity.class));
    }
    
}
