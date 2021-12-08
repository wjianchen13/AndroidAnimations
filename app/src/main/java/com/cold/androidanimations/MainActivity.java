package com.cold.androidanimations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.fans.FansActivity;
import com.cold.androidanimations.fans.LeonidsActivity;
import com.cold.androidanimations.frame.FrameActivity;
import com.cold.androidanimations.kk.RotateActivity;
import com.cold.androidanimations.kk.RotateTestActivity;
import com.cold.androidanimations.open.OpenActivity;
import com.cold.androidanimations.open.Rotate3dActivity;
import com.cold.androidanimations.open.TestOpenActivity;
import com.cold.androidanimations.other.OtherActivity;
import com.cold.androidanimations.pan.PanActivity;
import com.cold.androidanimations.property.PropertyActivity;
import com.cold.androidanimations.property.TestActivity;
import com.cold.androidanimations.radar.RadarActivity;
import com.cold.androidanimations.svga.SvgaActivity;
import com.cold.androidanimations.test.TestActivity11;
import com.cold.androidanimations.tween.TweenActivity;

/**
 * anim和animator区别
 * anim文件夹下存放tween animation（补间动画）和frame animation（逐帧动画）
 * animator文件夹下存放property animation（属性动画）
 * xml文件里有animator、objectAnimator、set三个标签
 * 在代码中使用AnimatorInflater.loadAnimator（）方法加载动画
 * 使用Animator.setTarget（View）为View控件加载动画，使用Animator.start（）开启动画
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 翻牌动画效果
     * @param
     * @return
     */
    public void onOpen(View v) {
        Intent it = new Intent();
//        it.setClass(MainActivity.this, OpenActivity.class);
//        it.setClass(MainActivity.this, Rotate3dActivity.class);
        it.setClass(MainActivity.this, TestOpenActivity.class);
        startActivity(it);
    }
    
    /**
     * 属性动画
     * @param
     * @return
     */
    public void onProperty(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, PropertyActivity.class);
        startActivity(it);
    }

    /**
     * 补间动画
     * @param
     * @return
     */
    public void onTween(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, TweenActivity.class);
        startActivity(it);
    }

    /**
     * 帧动画
     * @param
     * @return
     */
    public void onFrame(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, FrameActivity.class);
        startActivity(it);
    }

    /**
     * 帧动画
     * @param
     * @return
     */
    public void onShow(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, ShowActivity.class);
        startActivity(it);
    }

    /**
     * 粒子动画
     * @param
     * @return
     */
    public void onLeonids(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, LeonidsActivity.class);
        startActivity(it);
    }

    /**
     * 项目粉丝团动画封装
     * @param
     * @return
     */
    public void onFans(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, FansActivity.class);
        startActivity(it);
    }

    /**
     * 项目粉丝团动画封装
     * @param
     * @return
     */
    public void onRadar(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, RadarActivity.class);
        startActivity(it);
    }

    /**
     * 转盘效果
     * @param
     * @return
     */
    public void onPan(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, PanActivity.class);
        startActivity(it);
    }

    /**
     * 旋转
     * @param
     * @return
     */
    public void onRotate(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, RotateActivity.class);
        startActivity(it);
    }

    /**
     * 旋转测试
     * @param
     * @return
     */
    public void onRotateTest(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, RotateTestActivity.class);
        startActivity(it);
    }

    /**
     * 其他
     * @param
     * @return
     */
    public void onOther(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, OtherActivity.class);
        startActivity(it);
    }

    /**
     * SVGA 动画
     * @param
     * @return
     */
    public void onSvga(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, SvgaActivity.class);
        startActivity(it);
    }

    /**
     * 测试
     * @param v
     */
    public void onTest(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, TestActivity11.class);
        startActivity(it);
    }
    
}
