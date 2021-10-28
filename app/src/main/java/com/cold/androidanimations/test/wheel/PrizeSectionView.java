package com.cold.androidanimations.test.wheel;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cold.androidanimations.R;
import com.cold.androidanimations.glide.GlideApp;


public class PrizeSectionView extends FrameLayout {
    private static final long ANIMATION_DURATION = 500;

    private final View container;
    private final ImageView ivAvatar;
    private final ImageView ivTagMe;
    private final TextView tvNickname;

    public PrizeSectionView(Context context) {
        this(context, null);
    }

    public PrizeSectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PrizeSectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_prize_section, this, true);
        container = findViewById(R.id.prize_section_container);
        ivAvatar = findViewById(R.id.iv_avatar);
        ivTagMe = findViewById(R.id.iv_tag_me);
        tvNickname = findViewById(R.id.tv_nickname);
    }

    public void setData(@NonNull String nickname, @NonNull String avatarUrl, boolean isMe) {
        tvNickname.setText(nickname);
        ivTagMe.setVisibility(isMe ? VISIBLE : GONE);
//        ImageDinoHelper.loadCircleImage(getContext(), avatarUrl, ivAvatar, R.drawable.dino_img_user_icon);
        GlideApp.with(getContext())
                .load(avatarUrl)
                .circleCrop()
                .into(ivAvatar);
    }

    public void animChange() {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        translateAnimation.setDuration(ANIMATION_DURATION);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0F, 1.0F);
        alphaAnimation.setDuration(ANIMATION_DURATION);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        tvNickname.startAnimation(animationSet);
        Drawable drawable = ivAvatar.getDrawable();
        if (null != drawable) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 10000);
            valueAnimator.setDuration(ANIMATION_DURATION);
            if (!(drawable instanceof ClipDrawable)) {
                drawable = new ClipDrawable(drawable, Gravity.BOTTOM, ClipDrawable.VERTICAL);
                ivAvatar.setImageDrawable(drawable);
            }
            valueAnimator.addUpdateListener(animation -> ivAvatar.getDrawable().setLevel((Integer) animation.getAnimatedValue()));
            valueAnimator.start();
        }
    }

    public void animShow() {
        if(ivAvatar != null && ivAvatar.getDrawable() != null)
            ivAvatar.getDrawable().setAlpha(255);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        translateAnimation.setDuration(ANIMATION_DURATION);
        container.startAnimation(translateAnimation);
    }

    public void getPosition(PointF point) {
        point.set(-ivAvatar.getWidth() >> 1, container.getTop());
    }

    public void setMaxEms(int maxEms) {
        tvNickname.setMaxEms(maxEms);
    }
}
