package com.cold.androidanimations;

import android.content.Context;

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
        return context.getResources().getDisplayMetrics().density;
    }


}
