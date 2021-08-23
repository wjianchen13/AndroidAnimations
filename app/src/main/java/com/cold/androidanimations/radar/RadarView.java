package com.cold.androidanimations.radar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cold.androidanimations.R;

/**
 * 雷达扫描
 */
public class RadarView extends FrameLayout {

    private ImageView imgvPointer;
    AnimatorSet set = new AnimatorSet();

    public RadarView(@NonNull Context context) {
        super(context);
        initView();
    }

    public RadarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(R.drawable.bg_studio_pk_radar);
        initView();
    }

    public RadarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_radar, this, true);
        imgvPointer = (ImageView)findViewById(R.id.imgv_pointer);
    }

    public void start() {
        ObjectAnimator animLightR = ObjectAnimator.ofFloat(imgvPointer, "rotation", 0, 360).setDuration(1000); // 绕X轴翻转
        animLightR.setRepeatCount(ValueAnimator.INFINITE);
        animLightR.setRepeatMode(ValueAnimator.RESTART);
        animLightR.setInterpolator(new LinearInterpolator());
        set.play(animLightR);
        set.start();
    }

    public void stop() {
        set.end();
    }
}
