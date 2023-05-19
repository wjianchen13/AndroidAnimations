package com.cold.androidanimations.kk;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Utils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5f);
    }

    /**
     * 返回屏幕密度
     */
    public static float getDensity(Context context) {
        try {
            return context.getResources().getDisplayMetrics().density;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2.0f;
    }
    
    public static void println(String str) {
        System.out.println("=================================> " + str);
    }

    /**
     * play svga anim
     */
    public static void playSvgaFromFile(Context context, String name, final SVGAImageView view, final @DrawableRes int resId) {
        playSvgaFromFile(context, name, view, resId, 0, true, null);
    }

    /**
     * play svga anim
     */
    public static void playSvgaFromFile(Context context, String name, final SVGAImageView view, final @DrawableRes int resId, int loop, final boolean autoPlay, SVGACallback callback) {
        try {
            if (!TextUtils.isEmpty(name) && view != null && context != null) {
                if (loop > 0)
                    view.setLoops(loop);
                if (callback != null)
                    view.setCallback(callback);

                SVGAParser.Companion.shareParser().init(context);
                SVGAParser svgaParser = SVGAParser.Companion.shareParser();

                File animFile = new File(name);
                FileInputStream fileInputStream = new FileInputStream(animFile);
                svgaParser.decodeFromInputStream(fileInputStream, name, new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NonNull SVGAVideoEntity svgaVideoEntity) {
                        try {
                            view.setVideoItem(svgaVideoEntity);
                            view.setVisibility(View.VISIBLE);
                            if (autoPlay){
                                view.startAnimation();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError() {
                        if (resId != 0) {
                            view.setBackgroundResource(resId);
                            view.setVisibility(View.VISIBLE);
                        }
                    }
                }, true, null, name);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            if (resId != 0 && view != null) {
                view.setBackgroundResource(resId);
                view.setVisibility(View.VISIBLE);
            }
        }
    }
}
