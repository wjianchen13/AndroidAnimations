package com.cold.androidanimations.rotate;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cold.androidanimations.R;

public class RotateView extends FrameLayout {

    private Context mContext;
    private FrameLayout mFlContainer;
    private FrameLayout mFlCardBack;
    private FrameLayout mFlCardFront;
    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画

    private AnimatorSet mLeftOutSet; // 左出动画
    private AnimatorSet mRightInSet; // 右入动画

    private TextView tvBack;
    private TextView tvFront;

    private boolean mIsShowBack;

    public RotateView(@NonNull Context context) {
        super(context);
        this.mContext = context;
        initView();
        initAnimator();
    }

    public RotateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
        initAnimator();
    }

    public RotateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
        initAnimator();
    }

    public void setContent(int index) {
        tvBack.setText("背面" + index);
        tvFront.setText("正面" + index);
    }

    protected void initAnimator() {
        setAnimators(); // 设置动画
        setCameraDistance(); // 设置镜头距离
    }

    // 设置动画
    private void setAnimators() {
        if(mContext != null) {
            mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.anim_out);
            mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.anim_in);
            mLeftOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.anim_out_back);
            mRightInSet = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.anim_in_back);
        }
    }

    // 改变视角距离, 贴近屏幕
    private void setCameraDistance() {
        int distance = 30000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mFlCardFront.setCameraDistance(scale);
        mFlCardBack.setCameraDistance(scale);
    }

    // 翻转卡片
    // isFont 是否正面
    public void flipCard(boolean isShowBack) {
        if(isShowBack != mIsShowBack) {
            if (mRightOutSet == null || mLeftInSet == null)
                return;

            if (mRightOutSet.isRunning()) {
                mRightOutSet.end();
            }
            if (mLeftInSet.isRunning()) {
                mLeftInSet.end();
            }

            // 正面朝上
            if (!mIsShowBack) {
                mRightOutSet.setTarget(mFlCardFront);
                mLeftInSet.setTarget(mFlCardBack);
                mRightOutSet.start();
                mLeftInSet.start();
                mIsShowBack = true;
            } else { // 背面朝上
                mLeftOutSet.setTarget(mFlCardBack);
                mRightInSet.setTarget(mFlCardFront);
                mLeftOutSet.start();
                mRightInSet.start();
                mIsShowBack = false;
            }
        }
    }

    public void stop() {
        if(mRightOutSet != null) {
//            mRightOutSet.cancel();
            mRightOutSet.end();
        }
        if(mLeftInSet != null) {
            mLeftInSet.end();
        }


    }

    private void initView() {
        if(mContext != null) {
            inflate(mContext, R.layout.view_rotate_view, this);
            mFlContainer = (FrameLayout)findViewById(R.id.flyt_container);
            mFlCardBack = (FrameLayout)findViewById(R.id.main_fl_card_back);
            mFlCardFront = (FrameLayout)findViewById(R.id.main_fl_card_front);
            tvBack = (TextView) findViewById(R.id.tv_back);
            tvFront = (TextView) findViewById(R.id.tv_front);
        }
    }

}
