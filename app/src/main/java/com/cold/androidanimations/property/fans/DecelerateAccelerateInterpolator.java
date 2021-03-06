package com.cold.androidanimations.property.fans;

import android.view.animation.Interpolator;

public class DecelerateAccelerateInterpolator implements Interpolator {

    @Override
    public float getInterpolation(float input) {
        float x = 2.0f * input - 1.0f;
        return 0.5f*(x*x*x + 1.0f);
    }
}