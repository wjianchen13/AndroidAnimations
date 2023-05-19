package com.cold.androidanimations.svga;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;
import com.cold.androidanimations.kk.Utils;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 公用缓存
 */
public class CacheActivity extends AppCompatActivity {

    private SVGAImageView svgaTest1;
    private SVGAImageView svgaTest2;
    private SVGAImageView svgaTest3;
    private SVGAImageView svgaTest4;

    private SVGAVideoEntity mSvgaVideoEntity;

    private String mPrivateRootPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        mPrivateRootPath = getFilesDir().getAbsolutePath();
        svgaTest1 = findViewById(R.id.svga_test1);
        svgaTest2 = findViewById(R.id.svga_test2);
        svgaTest3 = findViewById(R.id.svga_test3);
        svgaTest4 = findViewById(R.id.svga_test4);
    }

    /**
     * 直接播放
     * @param
     * @return
     */
    public void onTest1(View v) {
        try {
            String name = mPrivateRootPath + File.separator + "test.svga";
            if (!TextUtils.isEmpty(name)) {
                SVGAParser.Companion.shareParser().init(this);
                SVGAParser svgaParser = SVGAParser.Companion.shareParser();

                File animFile = new File(name);
                FileInputStream fileInputStream = new FileInputStream(animFile);
                svgaParser.decodeFromInputStream(fileInputStream, name, new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NonNull SVGAVideoEntity svgaVideoEntity) {
                        svgaTest1.setVideoItem(svgaVideoEntity);
                        svgaTest1.setVisibility(View.VISIBLE);
                        svgaTest1.startAnimation();
//                        svgaTest1.stopAnimation();
                    }

                    @Override
                    public void onError() {
                    }
                }, true, null, name);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单独加载
     * @param
     * @return
     */
    public void onTest2(View v) {
        long currentTime = System.currentTimeMillis();
        try {
            String name = mPrivateRootPath + File.separator + "test.svga";
            if (!TextUtils.isEmpty(name)) {
                SVGAParser.Companion.shareParser().init(this);
                SVGAParser svgaParser = SVGAParser.Companion.shareParser();

                File animFile = new File(name);
                FileInputStream fileInputStream = new FileInputStream(animFile);
                svgaParser.decodeFromInputStream(fileInputStream, name, new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NonNull SVGAVideoEntity svgaVideoEntity) {
                        mSvgaVideoEntity = svgaVideoEntity;
                    }

                    @Override
                    public void onError() {
                    }
                }, true, null, name);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long lastTime = System.currentTimeMillis();
        Utils.println("消耗时间： " + (lastTime - currentTime) + " ms" );

    }

    /**
     * 开始播放一个
     * @param
     * @return
     */
    public void onTest3(View v) {
        if(mSvgaVideoEntity != null) {
            svgaTest2.setVideoItem(mSvgaVideoEntity);
            svgaTest2.setVisibility(View.VISIBLE);
            svgaTest2.startAnimation();
        }
    }

    /**
     * 开始播放多个
     * @param
     * @return
     */
    public void onTest4(View v) {
        if(mSvgaVideoEntity != null) {
            svgaTest3.setVideoItem(mSvgaVideoEntity);
            svgaTest3.setVisibility(View.VISIBLE);
            svgaTest3.startAnimation();
            svgaTest4.setVideoItem(mSvgaVideoEntity);
            svgaTest4.setVisibility(View.VISIBLE);
            svgaTest4.startAnimation();
        }
    }

    /**
     * 停止播放
     * @param
     * @return
     */
    public void onTest5(View v) {
        svgaTest3.stopAnimation();

    }

}
