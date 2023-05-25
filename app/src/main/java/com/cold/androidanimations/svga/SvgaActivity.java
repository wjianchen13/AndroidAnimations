package com.cold.androidanimations.svga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class SvgaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svga);
    }

    /**
     * 从assets目录播放动画
     * @param
     * @return
     */
    public void onAssets(View v) {
        Intent it = new Intent();
        it.setClass(SvgaActivity.this, AssetsActivity.class);
        startActivity(it);
    }

    /**
     * 从文件目录中读取
     * @param
     * @return
     */
    public void onOther(View v) {
        startActivity(new Intent(this, AssetsOtherActivity.class));
    }

    /**
     * 公用缓存
     * @param
     * @return
     */
    public void onCache(View v) {
        startActivity(new Intent(this, CacheActivity.class));
    }

    /**
     * 预加载 占位
     * @param
     * @return
     */
    public void onPreload(View v) {
        startActivity(new Intent(this, PreloadActivity.class));
    }

    /**
     * 如果是静态图，直接显示，不动画
     * @param
     * @return
     */
    public void onStatic(View v) {
        startActivity(new Intent(this, StaticActivity.class));
    }
    
}
