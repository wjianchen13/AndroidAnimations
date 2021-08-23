package com.cold.androidanimations.property.valueanimator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by Administrator on 2018/4/17.
 */
// 实现TypeEvaluator接口
public class BizerPointEvaluator implements TypeEvaluator {

    // 复写evaluate（）
    // 在evaluate（）里写入对象动画过渡的逻辑
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        final float t = fraction;
        float oneMinusT = 1.0f - t;
        PointF point = new PointF();

        PointF point0 = (PointF)startValue;

        PointF point1 = new PointF();
        point1.set(point0.x - 200, point0.y - 200);

        PointF point2 = new PointF();
        point2.set(point0.x + 200, point0.y - 400);

        PointF point3 = (PointF)endValue;

        point.x = oneMinusT * oneMinusT * oneMinusT * (point0.x)
                + 3 * oneMinusT * oneMinusT * t * (point1.x)
                + 3 * oneMinusT * t * t * (point2.x)
                + t * t * t * (point3.x);

        point.y = oneMinusT * oneMinusT * oneMinusT * (point0.y)
                + 3 * oneMinusT * oneMinusT * t * (point1.y)
                + 3 * oneMinusT * t * t * (point2.y)
                + t * t * t * (point3.y);
        return point;
    }

}