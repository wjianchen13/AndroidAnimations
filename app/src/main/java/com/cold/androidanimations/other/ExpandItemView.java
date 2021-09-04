package com.cold.androidanimations.other;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cold.androidanimations.R;

/**
 * 展开View item项
 */
public class ExpandItemView extends LinearLayout {

    private TextView tvTest;
    
    public ExpandItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ExpandItemView(Context context) {
        super(context);
        initView(context);
    }
    
    public ExpandItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.view_expand_item, this);
        tvTest = findViewById(R.id.tv_test);
    }
    
    public void setTvTest(String s) {
        if(tvTest != null)
            tvTest.setText(s);
    }
    
}
