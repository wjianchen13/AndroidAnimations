package com.cold.androidanimations.property;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.cold.androidanimations.R;
import com.cold.androidanimations.countdown.CountDownActivity;
import com.cold.androidanimations.property.fans.TestFansActivity;
import com.cold.androidanimations.property.objectanimator.ObjectAnimatorActivity;
import com.cold.androidanimations.property.objectanimator.customcolor.CustomColorActivity;
import com.cold.androidanimations.property.valueanimator.ScaleActivity;
import com.cold.androidanimations.property.valueanimator.ValueAnimatorActivity;
import com.cold.androidanimations.rotate.RotateActivity;
import com.cold.androidanimations.rotate.RotateViewActivity;
import com.cold.androidanimations.rotate.TestRotateViewActivity;

public class PropertyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
    }

    /**
     * 卡顿测试
     */
    public void onKa(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, KaActivity.class);
        startActivity(it);
    }

    /**
     * 旋转
     * @param
     * @return
     */
    public void onTestRoatae(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, TestRotateActivity.class);
        startActivity(it);
    }

    /**
     * 旋转
     * @param
     * @return
     */
    public void onTestRoatae1(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, TestRotateActivity1.class);
        startActivity(it);
    }


    /**
     * 缩放
     * @param
     * @return
     */
    public void onScale(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, ScaleActivity.class);
        startActivity(it);
    }

    /**
     * ValueAnimator
     * @param
     * @return
     */
    public void onValueAnimator(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, ValueAnimatorActivity.class);
        startActivity(it);
    }

    /**
     * object animator
     * @param
     * @return
     */
    public void onObjectAnimator(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, ObjectAnimatorActivity.class);
        startActivity(it);
    }

    /**
     * 自定义控件颜色改变
     * @param
     * @return
     */
    public void onCustomColor(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, CustomColorActivity.class);
        startActivity(it);
    }

    /**
     * ViewPropertyAnimator
     * @param
     * @return
     */
    public void onViewPropertyAnimator(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, ViewPropertyAnimatorActivity.class);
        startActivity(it);
    }

    /**
     * 组合动画
     * @param
     * @return
     */
    public void onAnimatorSet(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, AnimatorSetActivity.class);
        startActivity(it);
    }

    /**
     * AnimatorSet 测试
     * @param
     * @return
     */
    public void onTest(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, AnimatorSetActivity1.class);
        startActivity(it);
    }

    /**
     * 测试
     * @param
     * @return
     */
    public void onTest1(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, TestActivity1.class);
        startActivity(it);
    }

    /**
     * 倒计时测试
     * @param
     * @return
     */
    public void onTest2(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, CountDownActivity.class);
        startActivity(it);
    }

    /**
     * 旋转
     * @param
     * @return
     */
    public void onRotate(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, RotateActivity.class);
        startActivity(it);
    }

    /**
     * 旋转View封装
     * @param
     * @return
     */
    public void onRotateView(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, RotateViewActivity.class);
        startActivity(it);
    }

    /**
     * 旋转View封装
     * @param
     * @return
     */
    public void onTestRotateView(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, TestRotateViewActivity.class);
        startActivity(it);
    }

    /**
     * 粉丝团
     * @param
     * @return
     */
    public void onFans(View v) {
        Intent it = new Intent();
        it.setClass(PropertyActivity.this, TestFansActivity.class);
        startActivity(it);
    }

}
