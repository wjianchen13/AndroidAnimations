package com.cold.androidanimations.other;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by Administrator on 2014/9/9.
 */
public class GradientTextView extends AppCompatTextView {

    public GradientTextView(Context context) {
        super(context);
    }

    public GradientTextView(Context context,
                            AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientTextView(Context context,
                            AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//    @Override
//    protected void onLayout(boolean changed,
//                            int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//        if (changed) {
//            getPaint().setShader(new LinearGradient(
//                    0, 0, 0, getHeight(),
//                    0xffc8fafe, 0xff055ef1,
//                    Shader.TileMode.CLAMP));
//        }
//    }

    @Override
    protected void onLayout(boolean changed,
                            int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            getPaint().setShader(new LinearGradient(
                    0, 0, 0, getHeight(),
                    new int[]{0xffc8fafe, 0xff055ef1}, new float[]
                    { 0.3f, 0.7f},
                    Shader.TileMode.CLAMP));
        }
    }
    
}
