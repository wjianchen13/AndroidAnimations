package com.cold.androidanimations.notification;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TestView extends FrameLayout {

    private Context mContext;
    private List<View> mViewCache;


    public TestView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public TestView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TestView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        this.mViewCache = new ArrayList<>();
        addView("hello");
    }

    public void addNotification(String str) {
        View v = getView();
        if(v != null) {
            animIn(v);
        }
    }

    private View addView(String text) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);//定义显示组件参数
//        params.gravity = Gravity.BOTTOM;
        params.topMargin = 100;
        TextView tv = new TextView(mContext);//定义组件
        tv.setText(text);
        tv.setBackgroundColor(Color.argb(100, 0, 0, 255));

        addView(tv, params);//添加组件
        return tv;
    }

    private View getView() {
        View v = getCache();
        if(v == null) {
            v = addView("hello");
        }
        return v;
    }

    private void animIn(final View view) {
        if(view != null) {
            ObjectAnimator transY = ObjectAnimator.ofFloat(view, "translationY", -300, 0);
            transY.setInterpolator(new LinearInterpolator());
            transY.setDuration(2500);
            transY.addListener(new MyAnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animOut(view);
                        }
                    }, 2000);
                }
            });
            transY.start();
        }
    }

    private void animOut(final View view) {
        ObjectAnimator transY = ObjectAnimator.ofFloat(view, "translationY", 0, -300);
        transY.setInterpolator(new LinearInterpolator());
        transY.setDuration(2500);
        transY.addListener(new MyAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                saveCache(view);
            }
        });
        transY.start();
    }


    /**
     * 从缓存里面取显示的View
     * @return
     */
    public View getCache() {
        System.out.println("==========================> getCache size: " + mViewCache.size());
        if(mViewCache != null && mViewCache.size() > 0) {
            View v = mViewCache.get(0);
            mViewCache.remove(v);
            return v;
        }
        return null;
    }

    /**
     * 隐藏某个View，并且加入到可用缓存
     * @param v
     */
    public void saveCache(View v) {
        if(v != null && mViewCache != null) {
            mViewCache.add(v);
            System.out.println("==========================> saveCache size: " + mViewCache.size());
        }
    }

    class MyAnimatorListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {

        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    }

}
