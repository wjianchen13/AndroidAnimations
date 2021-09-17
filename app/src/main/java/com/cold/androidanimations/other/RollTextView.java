package com.cold.androidanimations.other;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class RollTextView extends LinearLayout {

    private Context mContext;
    private TextView[] tvs;
    private int width;
    private int height;

    /**
     * 每一位字体显示的时间
     */
    private int duration = 240;

    /**
     * 下一位字体显示的时间
     */
    private int delay = 240; 
    
    private ValueAnimator valueAnimator;
    
    private int num;
    
    private int[] colors = new int[] {Color.argb(255, 0, 0, 255), Color.argb(255, 255, 0, 0)}; 
    
    public RollTextView(Context context) {
        super(context);
        initView(context);
    }

    public RollTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RollTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    
    private void initView(Context context) {
        this.mContext = context;
        setGravity(Gravity.CENTER);
    }
    
    public void setText(int ts) {
        num = getNum(ts);
        getNormalWidth();
        addView(num);
        transViews();
        setToValue(ts);
    }
    
    private void addView(int num) {
        tvs = new TextView[num];
        for(int i = 0; i < num; i ++) {
            TextView textView = new TextView(mContext);
//            textView.setText("hello");
            textView.setTextColor(Color.argb(255, 0, 0, 0));
            textView.setBackgroundColor(colors[i % 2]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            textView.setLayoutParams(params);
            tvs[i] = textView;
            textView.setTranslationY(height);
            addView(textView);
        }
    }

    private void setToValue(int value) {
        valueAnimator = ValueAnimator.ofInt(0, value);
        valueAnimator.setDuration(520 + 240 * num);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int result = (int) animation.getAnimatedValue();
                String rs = String.valueOf(result);
                char[] cs = rs.toCharArray();
                setText(cs);
            }
        });
        valueAnimator.start();
    }

    private void setText(char[] cs) {
        int csLen = cs.length;
        int tvsLen = tvs.length;
        if(csLen <= tvsLen) {
            for (int i = 0; i < csLen; i++) {
                tvs[i + (tvsLen - csLen)].setText(String.valueOf(cs[i]));
            }
        }
    }
    
    private void transViews() {
        AnimatorSet animSet = new AnimatorSet();
        for(int i = tvs.length - 1; i >= 0; i --) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(tvs[i], "translationY", height, 0).setDuration(duration);
            int delay1 = (tvs.length - 1 - i) * delay;
            System.out.println("====================> delay[" + i + "]: " + delay1);
            animator.setStartDelay(delay1);
            animSet.playTogether(animator);
        }
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

            }
        });
        
        
        
//        ObjectAnimator animator = ObjectAnimator.ofFloat(tvTest4, "translationY", yFrom, yTo).setDuration(5000);
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(tvTest3, "translationY", yFrom, yTo).setDuration(5000);
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(tvTest2, "translationY", yFrom, yTo).setDuration(5000);
//        ObjectAnimator animator3 = ObjectAnimator.ofFloat(tvTest1, "translationY", yFrom, yTo).setDuration(5000);
////                ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "translationX", xTo + 50, xTo).setDuration(duration1);
//
//        animator1.setStartDelay(1000);
//        animator2.setStartDelay(2000);
//        animator3.setStartDelay(3000);
////                animSet.play(animator);
//        animSet.play(animator).with(animator1).with(animator2).with(animator3);
//        animSet.play()
//        animator.setInterpolator(new LinearInterpolator());
////                animator.setDuration(duration);
//        v.setVisibility(View.VISIBLE);
        animSet.start();
    }
    
    /**
     * 显示的内容有多少位
     */
    private int getNum(int num) {
        return String.valueOf(num).length();
    }

    /**
     * 每个字的宽度
     * @return
     */
    private void getNormalWidth() {
        TextView textView = new TextView(mContext);
        textView.setText("0");
        textView.setMaxLines(1);
        textView.setTextColor((Color.argb(255, 255, 0, 0)));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(spec, spec);
        textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
        width = textView.getMeasuredWidth();
        height = textView.getMeasuredHeight();
    }
    
    
}
