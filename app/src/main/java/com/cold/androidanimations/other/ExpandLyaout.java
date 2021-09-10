package com.cold.androidanimations.other;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.cold.androidanimations.R;
import com.cold.androidanimations.kk.Utils;

/**
 * 展开Layout
 */
public class ExpandLyaout extends RelativeLayout {

    /**
     * 动画正常执行时间
     */
    private static final int duration = 200;

    /**
     * 从放到最大缩小到原来大小时间
     */
    private static final int duration1 = 80;
    
    private Context mContext;


    /**
     * 屏幕宽度
     */
    private int sWidth;

    /**
     * 一个item的宽度
     */
    private int itemWidth;

    /**
     * 左右边距
     */
    private float margin;
    private TextView tvTest;
    
    public ExpandLyaout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ExpandLyaout(Context context) {
        super(context);
        initView(context);
    }
    
    public ExpandLyaout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        sWidth = dm.widthPixels;
        itemWidth = Utils.dip2px(mContext, 39);
        margin = getResources().getDimension(R.dimen.expand_view_item_margin);
    }
    
    public void setTvTest(String s) {
        if(tvTest != null)
            tvTest.setText(s);
    }

    public void insertView(final int count) {
        removeAllViews();
        for(int i = 0; i < count; i ++) {
            addView(count, i);
        }
        if(count >= 1) {
            View child = getChildAt(count - 1); // 因为排列在第一个的View在最上层，所以要从最后开始
            scaleView(child, new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    transOtherView(count);
                }
            });
        }
    }

    /**
     * 根据需要加入View的个数，添加View
     * @param count View个数，需要根据这个参数算出margin值
     * @param index 当前加入的是第几个View，从0开始
     */
    private View addView(int count, int index) {
        final ExpandItemView item = new ExpandItemView(mContext);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);//与父容器的上侧对齐
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        lp.rightMargin = (int)((sWidth - (itemWidth + 2 * margin) * count) / 2 + margin + (itemWidth + 2 * margin) * index);
        item.setBackgroundColor(Color.argb(100, 0, 0, 255));

        item.setId(R.id.id_test);//设置这个View 的id
        item.setLayoutParams(lp); // 设置布局参数
        addView(item); // RelativeLayout添加子View
        item.setVisibility(View.INVISIBLE);
        return item;
    }

    /**
     * 移动除了最上层的View
     */
    private void transOtherView(int count) {
        for(int i = count - 2; i >= 0; i --) {
            View child = getChildAt(i);
            int times = count - 1 - i; // 从最上层倒数第二个开始，倒数第一个是缩放
            transView(child, -(margin * (2 * times) + itemWidth * times), 0);
        }
    }

    /**
     * 其他item位移 + 反弹
     * @param v
     */
    public void onTrans(View v) {
        int count = getChildCount();
        if(count == 0) {
            Toast.makeText(mContext, "count == 0", Toast.LENGTH_SHORT).show();
            return ;
        }
        View view = getChildAt(0);
        if(view == null) {
            Toast.makeText(mContext, "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        float margin = getResources().getDimension(R.dimen.expand_view_item_margin);

        float x = view.getX();
        float y = view.getY();
        int width = view.getWidth();
//        int i = 1;
        for(int i = 1; i < count; i ++) {
            transView(getChildAt(count - 1 - i), -(margin * (2 * i) + width * i), 0);
        }
    }

    /**
     * 其他item位移 + 反弹
     * @param v
     */
    public void onTrans1(View v) {
        int count = getChildCount();
        if(count == 0) {
            Toast.makeText(mContext, "count == 0", Toast.LENGTH_SHORT).show();
            return ;
        }
        View view = getChildAt(0);
        if(view == null) {
            Toast.makeText(mContext, "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        float margin = getResources().getDimension(R.dimen.expand_view_item_margin);

        float x = view.getX();
        float y = view.getY();
        int width = view.getWidth();
//        int i = 1;
        for(int i = 1; i < count; i ++) {
            transView(getChildAt(i), -(margin * (2 * i) + width * i), 0);
        }
    }

    /**
     * 第一个item缩放控制
     * @param view
     */
    public void scaleView(final View view, final AnimatorListenerAdapter adapter) {
        if(view == null) {
            Toast.makeText(mContext, "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(view, "scaleX", 0.6f, 1.2f).setDuration(duration);
                ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(view, "scaleY", 0.6f, 1.2f).setDuration(duration);

                ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(view, "scaleX", 1.2f, 1f).setDuration(duration1);
                ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(view, "scaleY", 1.2f, 1f).setDuration(duration1);

                ObjectAnimator alpha1 = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1f).setDuration(duration);

                AnimatorSet animSet = new AnimatorSet();

                animSet.play(scaleX1).with(scaleY1).with(alpha1);
                animSet.play(scaleX2).with(scaleY2).after(scaleY1);

                scaleY1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if(adapter != null)
                            adapter.onAnimationEnd(animation);
                        Toast.makeText(mContext, "end animation", Toast.LENGTH_SHORT).show();

                    }
                });
                view.setVisibility(View.VISIBLE);
                animSet.start();
            }
        });

    }

    private void transView(final View v, final float xFrom, final float xTo) {
        if(v == null) {
            Toast.makeText(mContext, "v == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        v.post(new Runnable() {
            @Override
            public void run() {
                // 相对当前坐标偏移
                ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationX", xFrom, xTo + 50).setDuration(duration);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "translationX", xTo + 50, xTo).setDuration(duration1);
                AnimatorSet animSet = new AnimatorSet();
//                animSet.play(animator);
                animSet.play(animator1).after(animator);
                animator.setInterpolator(new LinearInterpolator());
//                animator.setDuration(duration);
                v.setVisibility(View.VISIBLE);
                animSet.start();
            }
        });

    }

    /**
     * 移动一个view，测试从圆形移动到右上角效果，模拟Item插入
     * @param v
     * @param xFrom
     * @param xTo
     * @param yFrom
     * @param yTo
     */
    private void setTranslation(View v, float xFrom, float xTo, float yFrom, float yTo) {
        if(v == null) {
            Toast.makeText(mContext, "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, "translationX", xFrom, xTo);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(v, "translationY", yFrom, yTo);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(translationX).with(translationY).with(alpha);
        animSet.setDuration(5000);
        animSet.start();
    }
    
}
