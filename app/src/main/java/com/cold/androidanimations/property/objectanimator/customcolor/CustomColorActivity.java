package com.cold.androidanimations.property.objectanimator.customcolor;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cold.androidanimations.R;

public class CustomColorActivity extends AppCompatActivity {

    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_color);
        mView = findViewById(R.id.MyView2);
    }

    /**
     * 缩放
     * @param
     * @return
     */
    public void onColor(View v) {
        ObjectAnimator anim = ObjectAnimator.ofObject(mView, "color", new ColorEvaluator(),
                "#0000FF", "#FF0000");
        // 设置自定义View对象、背景颜色属性值 & 颜色估值器
        // 本质逻辑：
        // 步骤1：根据颜色估值器不断 改变 值
        // 步骤2：调用set（）设置背景颜色的属性值（实际上是通过画笔进行颜色设置）
        // 步骤3：调用invalidate()刷新视图，即调用onDraw（）重新绘制，从而实现动画效果

        anim.setDuration(8000);
        anim.start();
    }

}
