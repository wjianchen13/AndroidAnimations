package com.cold.androidanimations.other;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;
import com.cold.androidanimations.kk.Utils;

public class ExpandViewActivity extends AppCompatActivity {

    /**
     * 动画正常执行时间
     */
    private static final int duration = 200;

    /**
     * 从放到最大缩小到原来大小时间
     */
    private static final int duration1 = 80;
    
    private RelativeLayout rlytExpand;

    private ExpandItemView et1;
    private ExpandItemView et2;
    private ExpandItemView et3;
    private ExpandItemView et4;
    private ExpandItemView et5;
    
    /**
     * 需要添加多少个View
     */
    private int count = 5;

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
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_view);
        rlytExpand = findViewById(R.id.rlyt_expand);
        et1 = findViewById(R.id.ex_item1);
        et2 = findViewById(R.id.ex_item2);
        et3 = findViewById(R.id.ex_item3);
        et4 = findViewById(R.id.ex_item4);
        et5 = findViewById(R.id.ex_item5);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        sWidth = dm.widthPixels;
        itemWidth = Utils.dip2px(this, 39);
        margin = getResources().getDimension(R.dimen.expand_view_item_margin);
    }
    
    /**
     * 测试一下
     * @param v
     */
    public void onTest1(View v) {
        final ExpandItemView item = new ExpandItemView(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);//与父容器的上侧对齐
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        lp.rightMargin = (int)((sWidth - (itemWidth + 2 * margin) * count) / 2 + margin);
        item.setBackgroundColor(Color.argb(100, 0, 0, 255));

        item.setId(R.id.id_test);//设置这个View 的id
        item.setLayoutParams(lp); // 设置布局参数
        rlytExpand.addView(item); // RelativeLayout添加子View
        item.setVisibility(View.INVISIBLE);
        item.post(new Runnable() {
            @Override
            public void run() {
                item.setVisibility(View.VISIBLE);
                transView(item, -(margin * (2 * (count - 1)) + itemWidth * (count - 1)), 0);
            }
        });
    }

    /**
     * 测试一下
     * @param v
     */
    public void onTest2(View v) {
        ExpandItemView item = new ExpandItemView(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);//与父容器的上侧对齐
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        lp.rightMargin = (int)((sWidth - (itemWidth + 2 * margin) * count) / 2 + margin + (itemWidth + 2 * margin) * 1);
        item.setBackgroundColor(Color.argb(100, 0, 0, 255));

        item.setId(R.id.id_test);//设置这个View 的id
        item.setLayoutParams(lp); // 设置布局参数
        rlytExpand.addView(item); // RelativeLayout添加子View
    }

    /**
     * 第一个item缩放控制
     * @param v
     */
    public void onTest3(View v) {
        ExpandItemView item = new ExpandItemView(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);//与父容器的上侧对齐
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        lp.rightMargin = (int)((sWidth - (itemWidth + 2 * margin) * count) / 2 + margin + (itemWidth + 2 * margin) * 2);
        item.setBackgroundColor(Color.argb(100, 0, 0, 255));

        item.setId(R.id.id_test);//设置这个View 的id
        item.setLayoutParams(lp); // 设置布局参数
        rlytExpand.addView(item); // RelativeLayout添加子View
    }

    /**
     * 根据需要加入View的个数，添加View
     * @param count View个数，需要根据这个参数算出margin值
     * @param index 当前加入的是第几个View，从0开始
     */
    private View addView(int count, int index) {
        final ExpandItemView item = new ExpandItemView(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);//与父容器的上侧对齐
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        lp.rightMargin = (int)((sWidth - (itemWidth + 2 * margin) * count) / 2 + margin + (itemWidth + 2 * margin) * index);
        item.setBackgroundColor(Color.argb(100, 0, 0, 255));

        item.setId(R.id.id_test);//设置这个View 的id
        item.setLayoutParams(lp); // 设置布局参数
        rlytExpand.addView(item); // RelativeLayout添加子View
        item.setVisibility(View.INVISIBLE);
        return item;
    }

    /**
     * 添加2个item
     * @param v
     */
    public void onAdd2(View v) {
        insertView(2);
    }

    /**
     * 添加3个item
     * @param v
     */
    public void onAdd3(View v) {
        insertView(3);
    }

    /**
     * 添加4个item
     * @param v
     */
    public void onAdd4(View v) {
        insertView(4);
    }

    /**
     * 添加5个item
     * @param v
     */
    public void onAdd5(View v) {
        insertView(5);
    }

    /**
     * 添加6个item
     * @param v
     */
    public void onAdd6(View v) {
        insertView(6);
    }

    /**
     * 添加7个item
     * @param v
     */
    public void onAdd7(View v) {
        insertView(7);
    }

    /**
     * 添加8个item
     * @param v
     */
    public void onAdd8(View v) {
        insertView(8);
    }
    
    private void insertView(final int count) {
        rlytExpand.removeAllViews();
        for(int i = 0; i < count; i ++) {
            addView(count, i);
        }
        if(count >= 1) {
            View child = rlytExpand.getChildAt(count - 1); // 因为排列在第一个的View在最上层，所以要从最后开始
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
     * 移动除了最上层的View
     */
    private void transOtherView(int count) {
        for(int i = count - 2; i >= 0; i --) {
            View child = rlytExpand.getChildAt(i);
            int times = count - 1 - i; // 从最上层倒数第二个开始，倒数第一个是缩放
            transView(child, -(margin * (2 * times) + itemWidth * times), 0);
        }
    }

    /**
     * 其他item位移 + 反弹
     * @param v
     */
    public void onTrans(View v) {
        int count = rlytExpand.getChildCount();
        if(count == 0) {
            Toast.makeText(ExpandViewActivity.this, "count == 0", Toast.LENGTH_SHORT).show();
            return ;
        }
        View view = rlytExpand.getChildAt(0);
        if(view == null) {
            Toast.makeText(ExpandViewActivity.this, "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        float margin = getResources().getDimension(R.dimen.expand_view_item_margin);

        float x = view.getX();
        float y = view.getY();
        int width = view.getWidth();
//        int i = 1;
        for(int i = 1; i < count; i ++) {
            transView(rlytExpand.getChildAt(count - 1 - i), -(margin * (2 * i) + width * i), 0);
        }
    }

    /**
     * 其他item位移 + 反弹
     * @param v
     */
    public void onTrans1(View v) {
        int count = rlytExpand.getChildCount();
        if(count == 0) {
            Toast.makeText(ExpandViewActivity.this, "count == 0", Toast.LENGTH_SHORT).show();
            return ;
        }
        View view = rlytExpand.getChildAt(0);
        if(view == null) {
            Toast.makeText(ExpandViewActivity.this, "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        float margin = getResources().getDimension(R.dimen.expand_view_item_margin);
        
        float x = view.getX();
        float y = view.getY();
        int width = view.getWidth();
//        int i = 1;
        for(int i = 1; i < count; i ++) {
            transView(rlytExpand.getChildAt(i), -(margin * (2 * i) + width * i), 0);
        }
    }

    /**
     * 第一个item缩放控制
     * @param view
     */
    public void scaleView(final View view, final AnimatorListenerAdapter adapter) {
        if(view == null) {
            Toast.makeText(ExpandViewActivity.this, "view == null", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ExpandViewActivity.this, "end animation", Toast.LENGTH_SHORT).show();

                    }
                });
                view.setVisibility(View.VISIBLE);
                animSet.start();
            }
        });

    }
    
    private void transView(final View v, final float xFrom, final float xTo) {
        if(v == null) {
            Toast.makeText(ExpandViewActivity.this, "v == null", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "view == null", Toast.LENGTH_SHORT).show();
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

    /**
     * 模拟开始那一刻，所有动画一起执行
     * @param v
     */
    public void onStart(View v) {
        
    }
    
}
