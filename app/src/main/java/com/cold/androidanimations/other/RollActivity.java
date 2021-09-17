package com.cold.androidanimations.other;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.cold.androidanimations.R;

public class RollActivity extends AppCompatActivity {
    
    private LinearLayout llytTest;
    private LinearLayout llytTest1;
    private RollTextView llytTest2;
    private TextView tvTest1;
    private TextView tvTest2;
    private TextView tvTest3;
    private TextView tvTest4;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);
        llytTest = findViewById(R.id.llyt_test);
        llytTest1 = findViewById(R.id.llyt_test1);
        llytTest2 = findViewById(R.id.llyt_test2);
        tvTest1 = findViewById(R.id.tv_test1);
        tvTest2 = findViewById(R.id.tv_test2);
        tvTest3 = findViewById(R.id.tv_test3);
        tvTest4 = findViewById(R.id.tv_test4);
    }
    
    /**
     * 测试一下
     * @param v
     */
    public void onTest1(View v) {
        int height = tvTest1.getHeight();
        transView(tvTest1, height, 0);
    }

    /**
     * 测试一下
     * @param v
     */
    public void onTest2(View v) {
        String s = "53269";
        char[] cs = s.toCharArray();
        int c = 0;
        
        
//        for(int i = 0; i < 10; i ++) {
//            test(i + "");
//        }
    }
    
    private void test(String s) {
        TextView textView = new TextView(this);
        textView.setText(s);
        textView.setMaxLines(1);
        textView.setTextColor((Color.argb(255, 255, 0, 0)));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
//        llytTest1.addView(textView);
        int width = getTextViewWidth(textView);
        int c = 0;
        System.out.println("=====================> " + s + " width: " + width);
//        char d = '';
//        textView.setText(String.valueOf(d));
    }

    /**
     * 获取文本宽度
     * @param tvScroll 获取对象
     * @return 文本宽度
     */
    private int getTextViewWidth(TextView tvScroll) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tvScroll.measure(spec, spec);
        tvScroll.layout(0, 0, tvScroll.getMeasuredWidth(), tvScroll.getMeasuredHeight());
        return tvScroll.getMeasuredWidth();
    }
    

    /**
     * 测试一下
     * @param v
     */
    public void onTest3(View v) {
        TextView textView = new TextView(this);
        textView.setText("hello");
        textView.setTextColor((Color.argb(255, 255, 0, 0)));
        textView.setBackgroundColor((Color.argb(255, 0, 0, 255)));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        llytTest1.addView(textView);

    }

    /**
     * 测试一下
     * @param v
     */
    public void onTest4(View v) {
        llytTest2.setText(123456);
    }

    private void transView(final View v, final float yFrom, final float yTo) {
        if (v == null) {
            Toast.makeText(RollActivity.this, "v == null", Toast.LENGTH_SHORT).show();
            return;
        }
        v.post(new Runnable() {
            @Override
            public void run() {
                // 相对当前坐标偏移
                ObjectAnimator animator = ObjectAnimator.ofFloat(tvTest4, "translationY", yFrom, yTo).setDuration(5000);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(tvTest3, "translationY", yFrom, yTo).setDuration(5000);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(tvTest2, "translationY", yFrom, yTo).setDuration(5000);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(tvTest1, "translationY", yFrom, yTo).setDuration(5000);
//                ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "translationX", xTo + 50, xTo).setDuration(duration1);
                AnimatorSet animSet = new AnimatorSet();
                animator1.setStartDelay(1000);
                animator2.setStartDelay(2000);
                animator3.setStartDelay(3000);
//                animSet.play(animator);
//                animSet.play(animator).with(animator1).with(animator2).with(animator3);
                animSet.playTogether(animator);
                animSet.playTogether(animator1);
                animSet.playTogether(animator2);
                animSet.playTogether(animator3);
                animator.setInterpolator(new LinearInterpolator());
//                animator.setDuration(duration);
                v.setVisibility(View.VISIBLE);
                animSet.start();
            }
        });
    }
    
}
