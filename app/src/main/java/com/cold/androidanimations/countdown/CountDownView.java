package com.cold.androidanimations.countdown;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cold.androidanimations.R;

import java.lang.ref.WeakReference;
import java.util.Arrays;

public class CountDownView extends RelativeLayout{

    private Context mContext;
    private ImageView imgvCountDown;
    private static MyHandler handler;
    private int countIndex;
    private int[] nums = new int[] {R.drawable.ic_count_down_1, R.drawable.ic_count_down_2, R.drawable.ic_count_down_3, R.drawable.ic_count_down_4,
            R.drawable.ic_count_down_5, R.drawable.ic_count_down_6, R.drawable.ic_count_down_7, R.drawable.ic_count_down_8,
            R.drawable.ic_count_down_9, R.drawable.ic_count_down_10};

    private Integer[] effects = new Integer[]{4, 3, 2, 1, 0};

    class MyHandler extends Handler {

        WeakReference<CountDownView> outerClass;

        MyHandler(CountDownView activity) {
            outerClass = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    start(--countIndex);
                    if(countIndex > 0) {
                        postCountDownMessageDelay(1000);
                    break;
                }
            }
        }
    }

    private void start(int index) {
        if(imgvCountDown == null && index > 9)
            return ;
        if(isNeedEffect(index)) {
            imgvCountDown.setVisibility(View.INVISIBLE);
            imgvCountDown.setBackgroundResource(nums[index]);
            playEffectAnimation();
        } else {
            imgvCountDown.setVisibility(View.VISIBLE);
            imgvCountDown.setBackgroundResource(nums[index]);
        }
    }

    private void playEffectAnimation() {
        ObjectAnimator translation = ObjectAnimator.ofFloat(imgvCountDown, "translationX", 0, 6, 0, -6, 0, 6, 0).setDuration(240);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imgvCountDown, "scaleX", 11f, 1f).setDuration(160);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imgvCountDown, "scaleY", 11f, 1f).setDuration(160);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(scaleX).with(scaleY);
        animSet.play(translation).after(scaleY);
        animSet.addListener(new  AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                imgvCountDown.setVisibility(View.VISIBLE);
            }
        });
        animSet.start();
    }

    private boolean isNeedEffect(int index) {
        return Arrays.asList(effects).contains(index);
    }

    /************************************************************************************************
     *
     * construct
     *
     ***********************************************************************************************/
    public CountDownView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init(){
        handler = new MyHandler(this);
        initView();
    }

    private void initView() {
        if(mContext != null) {
            inflate(mContext, R.layout.item_count_down_view, this);
            imgvCountDown = (ImageView)findViewById(R.id.imgv_count);
        }
    }

    public void startCountDown(int index) {
        if(index > 10)
            return ;
        countIndex = index;
        if(handler != null) {
            if(handler.hasMessages(1)) {
                handler.removeMessages(1);
            }
            Message msg = handler.obtainMessage();
            msg.what = 1;
            handler.sendMessage(msg);
        }
    }

    private void postCountDownMessageDelay(int delay) {
        if(handler != null) {
            if(handler.hasMessages(1)) {
                handler.removeMessages(1);
            }
            Message msg = handler.obtainMessage();
            msg.what = 1;
            handler.sendMessageDelayed(msg, delay);
        }
    }

    public void clear() {
        if(handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }


}
