package com.cold.androidanimations.property;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;
import com.cold.androidanimations.kk.Utils;

public class TestRotateActivity1 extends AppCompatActivity {
    
    private RelativeLayout rlytFrame;
    private TextView tvTest;
    private View tv1;

    /**
     * 这个角度已Y轴负方向为准，顺时针方向为正
     */
    private int angle = 75;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rotate1);
        rlytFrame = findViewById(R.id.rlyt_frame);
        tv1 = findViewById(R.id.tv_test1);
        setRotate2(tv1, angle);
    }
    
    /**
     * 添加一个View
     * @param
     * @return
     */
    public void onAddView(View v) {
        tvTest = new TextView(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(Utils.dip2px(this, 30), Utils.dip2px(this, 80));
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);//与父容器的上侧对齐
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.topMargin = Utils.dip2px(this, 10);
        tvTest.setGravity(Gravity.CENTER);
        tvTest.setAllCaps(false);
        tvTest.setText("add");
        tvTest.setId(R.id.id_test);//设置这个View 的id 
        tvTest.setBackgroundColor(Color.argb(100, 0, 0, 255));
        tvTest.setLayoutParams(lp); // 设置布局参数
        rlytFrame.addView(tvTest); // RelativeLayout添加子View
        tvTest.setVisibility(View.INVISIBLE);
//        tvTest.post(new Runnable() {
//            @Override
//            public void run() {
                setRotate(tvTest, angle);
//            }
//        });


    }

    /**
     * 
     * @param
     * @return
     */
    public void onShow(View v) {
        tvTest.setVisibility(View.VISIBLE);
//        setRotate(tvTest, angle);
    }
    
    public void onAdd(View v) {
    }
    
    /**
     * 
     * @param
     * @return
     */
    public void onGet(View v) {

    }

    /**
     * 移动测试
     * @param
     * @return
     */
    public void onTrans(View v) {

    }

    /**
     * 
     * @param
     * @return
     */
    public void onCoordinate(View v) {

    }

    /**
     * 
     * @param v
     */
    public void onSetRotate(View v) {

    }

    /**
     * 设置一个View的角度，带动画
     * @param v
     * @param from
     * @param to
     */
    private void setRotateAnim(View v, float from, float to) {
        if(v == null) {
            Toast.makeText(this, "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        v.setPivotX(v.getWidth() / 2);
        v.setPivotY(v.getHeight());
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v, "rotation", from, to);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }

    /**
     * 设置一个View的角度，不带动画
     * @param v
     * @param rotate
     */
    private void setRotate2(final View v, final float rotate) {
        if(v == null) {
            Toast.makeText(this, "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        v.post(new Runnable() {
            @Override
            public void run() {
                v.setPivotX(v.getWidth() / 2);
                v.setPivotY(v.getHeight());
                v.setRotation(rotate);
            }
        });

    }
    
    /**
     * 设置一个View的角度，不带动画
     * @param v
     * @param rotate
     */
    private void setRotate(final View v, final float rotate) {
        if(v == null) {
            Toast.makeText(this, "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        v.post(new Runnable() {
            @Override
            public void run() {
                v.setPivotX(v.getWidth() / 2);
                v.setPivotY(v.getHeight());
                v.setRotation(rotate);
                v.setVisibility(View.VISIBLE);
                Point p = getStartPoint(angle, v.getHeight());
                setTranslation(v, p.x, -0.0f, p.y, 0.0f);
            }
        });

    }
    
    /**
     * 设置一个View的角度，不带动画
     * @param v
     * @param rotate
     */
    private void setRotate1(final View v, final float rotate) {
        if(v == null) {
            Toast.makeText(this, "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        v.setPivotX(v.getWidth() / 2);
        v.setPivotY(v.getHeight());
        v.setRotation(rotate);
    }

    /**
     * 移动一个view，测试从圆形移动到右上角效果，模拟Item插入
     * @param v
     * @param xFrom
     * @param xTo
     * @param yFrom
     * @param yTo
     */
    private void setTranslation(View v, float xFrom, float xTo, float yFrom, float yTo) {
        if(v == null) {
            Toast.makeText(this, "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, "translationX", xFrom, xTo);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(v, "translationY", yFrom, yTo);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(translationX).with(translationY).with(alpha);
        animSet.setDuration(500);
//        animSet.setStartDelay(50);
        animSet.start();
    }

    /**
     * 给定一个角度，获取一个View弹出的起始坐标
     * @param angle 角度，相对于Y轴负方向
     * @param currentAdius 半径
     * @return 计算好的起始点
     */
    private Point getStartPoint(int angle, int currentAdius) {
        Point center = new Point();
        center.x = 0;
        center.y = 0;
        double radian = Math.toRadians(convertAngle(angle));
        double x = center.x + Math.cos(radian) * currentAdius;
        double y = center.y + Math.sin(radian) * currentAdius;
        int x1 = (int)Math.ceil(x);
        int y1 = (int)Math.ceil(y);
        System.out.println("====================> x: " + x + "   y: " + y);
        System.out.println("====================> x1: " + x1 + "   y1: " + y1);
        return new Point(-x1, y1); // 这里计算的是相对于X轴正方形，Y坐标本来是正确的，只需要把X轴坐标取反
    }

    /**
     * 转换角度，传入的是相对Y轴负方向，转换成X轴计算圆点坐标
     * @return
     */
    private double convertAngle(int angle) {
        if(angle > 0  && angle < 90) { // 插入的时候都在右上方，所以角度都在这个范围
            return 90 - angle;
        }
        return angle;
    }
    
}
