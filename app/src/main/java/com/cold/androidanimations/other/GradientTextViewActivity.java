package com.cold.androidanimations.other;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class GradientTextViewActivity extends AppCompatActivity {
    
    private static final int duration = 2000;
    
    private GradientTextView elView;
    private TextView tvTest;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient_textview);
        elView = findViewById(R.id.llyt_expand);
        tvTest = findViewById(R.id.tv_test);
    }
    
    /**
     * 第一个item缩放控制
     * @param v
     */
    public void onAdd1(View v) {
        SpannableString result = getRadiusGradientSpan("5/8");
        tvTest.setText(result);


    }

    private SpannableString getRadiusGradientSpan(String string){
        SpannableString spannableStringBuilder = new SpannableString(string);
        LinearGradientFontSpan span = new LinearGradientFontSpan(0);
        spannableStringBuilder.setSpan(span, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableStringBuilder;
    }
    
    /**
     * 第一个item缩放控制
     * @param v
     */
    public void onAdd2(View v) {

    }

    /**
     * 第一个item缩放控制
     * @param v
     */
    public void onAdd3(View v) {

    }

    /**
     * 第一个item缩放控制
     * @param v
     */
    public void onAdd4(View v) {

    }

    /**
     * 
     * @param v
     */
    public void onAdd5(View v) {

    }
    
    
    
    
    
}
