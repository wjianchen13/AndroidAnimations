package com.cold.androidanimations.pan;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.cold.androidanimations.R;

/**
 * 雷达扫描
 */
public class PanView extends FrameLayout {
    
    private int mWidth;
    private int mHeight;
    
    public PanView(@NonNull Context context) {
        super(context);
        initView();
    }

    public PanView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public PanView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView() {
        initView(null);
    }

    private void initView(AttributeSet attrs) {
//        LayoutInflater.from(getContext()).inflate(R.layout.item_pan, this, true);
        getAttrs(attrs);
        addItemView();


        
    }
    
    private void getAttrs(@Nullable AttributeSet attrs) {
        if(attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.PanView);
            mWidth = (int)ta.getDimension(R.styleable.PanView_android_layout_width, 0);
            mHeight = (int)ta.getDimension(R.styleable.PanView_android_layout_height, 0);
            ta.recycle();
        }
    } 

    /**
     * 动态添加布局
     * 显示在中间位置区域
     */
    private void addItemView() {
        FrameLayout.LayoutParams headParams = new FrameLayout.LayoutParams(dip2px(getContext(),60), dip2px(getContext(),150));
        View headGroupView = LayoutInflater.from(getContext()).inflate(R.layout.item_pan, this, false);
        headGroupView.setLayoutParams(headParams);
        headParams.gravity = Gravity.CENTER_HORIZONTAL;
        addView(headGroupView);
        float pivotx = headGroupView.getPivotX();
        float pivoty = headGroupView.getPivotY();
        println("pivotx: " + pivotx + "  pivoty: " + pivoty);
        

        headGroupView.setPivotX(90);
        headGroupView.setPivotY(450);
        headGroupView.setRotation(180);
//        rotation(headGroupView);
    }
    
    private void rotation(View mArrowImageView) {
        mArrowImageView.setPivotX(90);
        mArrowImageView.setPivotY(450);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                mArrowImageView, "rotation", 0, 180);
        objectAnimator.setDuration(3000);
        objectAnimator.start();

    }

    public int dip2px(float dpValue) {
        return dip2px(getContext(),dpValue);
    }
    
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5f);
    }


    /**
     * 返回屏幕密度
     */
    public float getDensity(Context context) {
        try {
            return context.getResources().getDisplayMetrics().density;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2.0f;
    }
    
    public static void println(String str) {
        System.out.println("==========================> str");
    }
    
}
