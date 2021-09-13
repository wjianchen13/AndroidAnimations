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
     * 补间动画
     * @param
     * @return
     */
    public void onOther(View v) {

    }
    
}
