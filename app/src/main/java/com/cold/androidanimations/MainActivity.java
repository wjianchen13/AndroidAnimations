package com.cold.androidanimations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cold.androidanimations.fans.FansActivity;
import com.cold.androidanimations.fans.LeonidsActivity;
import com.cold.androidanimations.frame.FrameActivity;
import com.cold.androidanimations.pan.PanActivity;
import com.cold.androidanimations.property.PropertyActivity;
import com.cold.androidanimations.radar.RadarActivity;
import com.cold.androidanimations.tween.TweenActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
