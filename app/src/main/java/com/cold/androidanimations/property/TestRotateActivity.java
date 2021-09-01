package com.cold.androidanimations.property;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class TestRotateActivity extends AppCompatActivity {

    private View tv1;
    private View tv2;
    private View tv3;
    private View tv4;
    private View tv5;

    /**
     * 这个角度已Y轴负方向为准，顺时针方向为正
     */
    private int angle = 75;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rotate);
        tv1 = findViewById(R.id.tv_test1);
        tv2 = findViewById(R.id.tv_test2);
        tv3 = findViewById(R.id.tv_test3);
        tv4 = findViewById(R.id.tv_test4);
        tv5 = findViewById(R.id.tv_test5);
        initRotate();
        initRotate1();
    }

    private void initRotate() {
        setRotate(tv1, 300);
        setRotate(tv2, 180);
        setRotate(tv3, 60);
    }

    /**
     * 动态设置角度，测试从圆心插入一个View效果
     */
    private void initRotate1() {
        setRotate(tv4, angle);
        setRotate(tv5, angle);
    }
    
    /**
     * 缩放
     * @param
     * @return
     */
    public void onRotate(View v) {
        setRotateAnim(tv1, 0, 160);
    }

    /**
     * 缩放
     * @param
     * @return
     */
    public void onRotate1(View v) {
        setRotateAnim(tv1, 160, 270);
    }
    
    public void onAdd(View v) {
        setRotateAnim(tv1, 300, 315);
        setRotateAnim(tv2, 180, 225);
        setRotateAnim(tv3, 60, 135);
    }
    
    /**
     * 获取中心点坐标
     * @param
     * @return
     */
    public void onGet(View v) {
        float x = tv4.getX() + tv4.getWidth() / 2;
        float y = tv4.getY() + tv4.getHeight();
        System.out.println("================= x: " + x + "  y: " + y);
    }

    /**
     * 移动测试
     * @param
     * @return
     */
    public void onTrans(View v) {
        setTranslation(tv4, -120.0f, -0.0f, 120.0f, 0.0f);
    }

    /**
     * 以正东面为0度起点计算指定角度所对应的圆周上的点的坐标
     * https://www.jianshu.com/p/754b356239c1
     * 根据给定的半径，角度，圆心坐标，计算圆上每个点的坐标
     * angle为弧度，center.x为圆心所在的X坐标，center.y为圆心所在Y坐标，currentAdius为圆的半径
     * @param
     * @return
     */
    public void onCoordinate(View v) {
        Point center = new Point();
        center.x = 0;
        center.y = 0;
        double angle = 30;
        int currentAdius = 100;
        double radian = Math.toRadians(angle);
        double x = center.x + Math.cos(radian) * currentAdius;
        double y = center.y + Math.sin(radian) * currentAdius;
        int x1 = (int)Math.ceil(x);
        int y1 = (int)Math.ceil(y);
        System.out.println("====================> x: " + x + "   y: " + y);
        System.out.println("====================> x1: " + x1 + "   y1: " + y1);
    }

    /**
     * 设定角度移动View
     * @param v
     */
    public void onSetRotate(View v) {
        Point p = getStartPoint(angle, tv4.getHeight());
        setTranslation(tv4, p.x, -0.0f, p.y, 0.0f);
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
            }
        });

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
        animSet.setDuration(5000);
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
