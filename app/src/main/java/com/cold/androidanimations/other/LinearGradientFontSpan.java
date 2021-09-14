package com.cold.androidanimations.other;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.style.ReplacementSpan;

/**
 * FileName: LinearGradientFontSpan
 * Author: bai
 * Date: 2019/4/17 15:26
 */
public class LinearGradientFontSpan extends ReplacementSpan {
    private int mSize;
    private int mode;

    public LinearGradientFontSpan(int mode) {
        this.mode = mode;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        mSize = Math.round(paint.measureText(text, start, end));
        //这段不可以去掉，字体高度没设置，可能出现draw（）方法没有被调用，如果你调用SpannableStringBuilder后append一个字符串，效果也是会出来，下面这段就不需要了
        // 原因详见https://stackoverflow.com/questions/20069537/replacementspans-draw-method-isnt-called
        Paint.FontMetricsInt metrics = paint.getFontMetricsInt();
        if (fm != null) {
            fm.top = metrics.top;
            fm.ascent = metrics.ascent;
            fm.descent = metrics.descent;
            fm.bottom = metrics.bottom;
        }
        return mSize;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        float rrr = paint.descent() - paint.ascent();
        LinearGradient lg = null;
        if(mode == 0){//上下渐变
            lg = new LinearGradient(0, 0, 0, paint.descent() - paint.ascent(),
                    0xffc8fafe,
                    0xff055ef1,
                    Shader.TileMode.CLAMP); //从上到下渐变
        }else{//左右渐变

//            lg = new LinearGradient(0, 0, mSize, 0,
//                    new int[] { 0xffff0000, 0xffffffff, 0xff00ff00 },
//                    new float[] { 0,0.5f, 0.8f},
//                    Shader.TileMode.REPEAT); //从左到右渐变

            //lg = new LinearGradient(paint.descent() - paint.ascent(),0,0,0,new int[]{Color.RED,Color.BLACK,Color.YELLOW},new float[]{0,0.5f,1}, Shader.TileMode.REPEAT);

            lg = new LinearGradient(mSize,0,0,0,new int[]{Color.RED,Color.BLACK,Color.YELLOW},new float[]{0,0.5f,1}, Shader.TileMode.REPEAT);
        }
        paint.setShader(lg);
        canvas.drawText(text, start, end, x, y, paint);//绘制文字
    }
}