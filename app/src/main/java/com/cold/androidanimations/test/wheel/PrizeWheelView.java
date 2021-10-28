package com.cold.androidanimations.test.wheel;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrizeWheelView extends ViewGroup {
    private static final int MIN_SECTION_COUNT = 2;
    private static final int MAX_SECTION_COUNT = 9;

    private static final float FULL_ROTATION_DEGREE = 360.0F;
    private static final float STRAIGHT_ANGLE_DEGREE = 180.0F;
    private static final float RIGHT_ANGLE_DEGREE = 90.0F;
    private static final float ZERO_DEGREE = 0.0F;

    private static final long WHEEL_ROTATE_DURATION = 800;
    private static final long SECTION_DURATION = 500;

    private static final int BACKGROUND_COLOR_DARK = 0xFF652FC8;
    private static final int BACKGROUND_COLOR_PRIMARY = 0xFF7236CA;
    private static final int BACKGROUND_COLOR_LIGHT = 0xFF8046D6;
    private static final int[][] BACKGROUND_COLORS = new int[][]{
            {},
            {BACKGROUND_COLOR_PRIMARY},
            {BACKGROUND_COLOR_LIGHT, BACKGROUND_COLOR_DARK},
            {BACKGROUND_COLOR_PRIMARY, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT},
            {BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT},
            {BACKGROUND_COLOR_LIGHT, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_PRIMARY},
            {BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT},
            {BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_PRIMARY, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT, BACKGROUND_COLOR_PRIMARY, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT},
            {BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT},
            {BACKGROUND_COLOR_PRIMARY, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_PRIMARY, BACKGROUND_COLOR_LIGHT, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT, BACKGROUND_COLOR_PRIMARY, BACKGROUND_COLOR_DARK, BACKGROUND_COLOR_LIGHT}
    };

    private static final int DEFAULT_ALPHA = 255;
    private static final int DARK_ALPHA = 104;
    private static final int LIGHT_RADIUS = 16;
    private static final int LIGHT_COLOR = 0xFFFF56d8;
    private static final long LIGHT_APPEAR_DURATION = 440;
    private static final long LIGHT_ALPHA_DURATION = 920;

    private final Rect bounds = new Rect();
    private final RectF boundsF = new RectF();
    private final Paint paint = new Paint();
    private final List<Section> sections = new ArrayList<>();
    private int sectionsCount;

    /** 旋转 **/
    private float degrees;
    private ValueAnimator rotateAnimator;

    /** 闪烁 **/
    private int lightIndex = -1;
    private int lightAlpha;
    private int lightAppearY;
    private final Paint lightPaint = new Paint();
    private ValueAnimator lightAppearAnimator;
    private ValueAnimator lightAlphaAnimator;
    private Bitmap lightBitmap;
    private Canvas lightCanvas;

    private int count;
    private OnPrizeListener onPrizeListener;

    public PrizeWheelView(Context context) {
        this(context, null);
    }

    public PrizeWheelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PrizeWheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        float density = context.getResources().getDisplayMetrics().density;
        this.paint.setAntiAlias(true);
        this.lightPaint.setAntiAlias(true);
        this.lightPaint.setMaskFilter(new BlurMaskFilter(LIGHT_RADIUS * density, BlurMaskFilter.Blur.INNER));
        for (int i = 0; i < MIN_SECTION_COUNT; i++) {
            animAddView(new PrizeSectionView(context), false);
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int paddingHorizontal = getPaddingLeft() + getPaddingRight();
        int paddingVertical = getPaddingTop() + getPaddingBottom();
        int width = resolveSize(paddingHorizontal, widthMeasureSpec);
        int height = resolveSize(paddingVertical, heightMeasureSpec);
        int size = Math.max(0, Math.max(width - paddingHorizontal, height - paddingVertical));
        width = resolveSize(size + paddingHorizontal, widthMeasureSpec);
        height = resolveSize(size + paddingVertical, heightMeasureSpec); // 到这里就是计算控件宽高尺寸的最佳值
        size = Math.max(0, Math.min(width - paddingHorizontal, height - paddingVertical)); 
        int left = Math.max(0, getPaddingLeft() + (width - paddingHorizontal - size) / 2);
        int top = Math.max(0, getPaddingTop() + (height - paddingVertical - size) / 2); //  到这里是计算放置子View的实际区域，让子View可以在宽高不一样的时候能够在正方形区域显示
        this.bounds.set(left, top, left + size, top + size); // 显示控件的区域
        this.boundsF.set(this.bounds);
        measureChildren();
        setMeasuredDimension(width, height);
        this.lightBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.lightCanvas = new Canvas(this.lightBitmap);
    }

    private void measureChildren() {
        // 因为measureChildren里面会调用getChildMeasureSpec，到时候会减去padding的值
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(this.bounds.width() + getPaddingLeft() + getPaddingRight(), MeasureSpec.EXACTLY);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(this.bounds.height() / 2 + getPaddingTop() + getPaddingBottom(), MeasureSpec.EXACTLY);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(this.bounds.left, this.bounds.top, this.bounds.right, this.bounds.centerY());
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        long drawingTime = getDrawingTime();
        int rotateSaveCount = canvas.save();
        canvas.rotate(this.degrees, this.boundsF.centerX(), this.boundsF.centerY());
        System.out.println("===============================================================================================> dispatchDraw");
        for (int i = 0, j = 0; i < this.sections.size(); i++) {
            Section section = this.sections.get(i);
            System.out.println("=====================> section.degrees: " +  section.degrees);
            canvas.rotate(- section.degrees / 2, this.boundsF.centerX(), this.boundsF.centerY()); // 逆时针转动角度，绘制半圆
            this.paint.setColor(section.backgroundColor);
            this.paint.setAlpha(DEFAULT_ALPHA);
            canvas.drawArc(this.boundsF, - RIGHT_ANGLE_DEGREE - section.degrees / 2, section.degrees, true, this.paint);
            if (section.available) {
                if (this.lightIndex == j && null != this.lightBitmap && null != this.lightCanvas) {
                    int lightCount = lightCanvas.save();
                    lightCanvas.clipRect(this.bounds.left, this.lightAppearY, this.bounds.right, this.bounds.bottom);
                    this.paint.setColor(LIGHT_COLOR);
                    this.paint.setAlpha(this.lightAlpha);
                    lightCanvas.drawArc(this.boundsF, - RIGHT_ANGLE_DEGREE - section.degrees / 2, section.degrees, true, this.paint);
                    this.lightPaint.setColor(section.backgroundColor);
                    lightCanvas.drawArc(this.boundsF, - RIGHT_ANGLE_DEGREE - section.degrees / 2, section.degrees, true, this.lightPaint);
                    lightCanvas.restoreToCount(lightCount);
                    // 直接绘制可能因为硬件加速无效果
                    canvas.drawBitmap(this.lightBitmap, this.bounds, this.bounds, this.paint);
                }
                drawChild(canvas, getChildAt(j), drawingTime); // 绘制布局
                j ++;
            }
            canvas.rotate(- section.degrees / 2, this.boundsF.centerX(), this.boundsF.centerY()); // 逆时针转动角度
        }
        canvas.restoreToCount(rotateSaveCount);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onViewAdded(View child) {
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View view = getChildAt(i);
            if (view instanceof PrizeSectionView) {
                ((PrizeSectionView) view).setMaxEms(Math.max(MIN_SECTION_COUNT, MAX_SECTION_COUNT - getChildCount()));
            } else {
                throw new IllegalArgumentException("Only support PrizeSectionView");
            }
        }
    }

    @Override
    public void onViewRemoved(View child) {
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View view = getChildAt(i);
            if (view instanceof PrizeSectionView) {
                ((PrizeSectionView) view).setMaxEms(Math.max(MIN_SECTION_COUNT, MAX_SECTION_COUNT - getChildCount()));
            } else {
                throw new IllegalArgumentException("Only support PrizeSectionView");
            }
        }
    }

    /**
     * 添加新的用户
     * @param nickname 用户昵称
     * @param avatarUrl 用户头像
     * @param isMe 是否自己
     */
    public void add(@NonNull String nickname, @NonNull String avatarUrl, boolean isMe, boolean showAnim) {
        if (MIN_SECTION_COUNT > this.count) {
            PrizeSectionView prizeSectionView = (PrizeSectionView) getChildAt(this.count);
            prizeSectionView.setData(nickname, avatarUrl, isMe);
            if (showAnim) prizeSectionView.animChange();
            this.count ++;
        } else if (MAX_SECTION_COUNT > this.count) {
            PrizeSectionView prizeSectionView = new PrizeSectionView(getContext());
            prizeSectionView.setData(nickname, avatarUrl, isMe);
            if (showAnim) prizeSectionView.animShow();
            animAddView(prizeSectionView, showAnim);
            this.count ++;
        }
    }

    /**
     * 重置转盘，回到两个空用户状态
     */
    public void reset() {
        this.sections.clear();
        this.sectionsCount = 0;

        this.degrees = ZERO_DEGREE;
        if (null != this.rotateAnimator) {
            this.rotateAnimator.cancel();
            this.rotateAnimator = null;
        }

        this.lightIndex = -1;
        if (null != this.lightAlphaAnimator) {
            this.lightAppearAnimator.cancel();
            this.lightAppearAnimator = null;
        }
        if (null != this.lightAlphaAnimator) {
            this.lightAlphaAnimator.cancel();
            this.lightAlphaAnimator = null;
        }

        this.count = 0;

        removeAllViews();
        for (int i = 0; i < MIN_SECTION_COUNT; i++) {
            animAddView(new PrizeSectionView(getContext()), false);
        }
    }

    /**
     * 开始无限顺时针转动
     */
    public void start() {
        if (null != this.rotateAnimator) {
            this.rotateAnimator.cancel();
        }
        this.degrees = this.degrees % FULL_ROTATION_DEGREE;
        this.rotateAnimator = ValueAnimator.ofFloat(this.degrees, this.degrees + FULL_ROTATION_DEGREE);
        this.rotateAnimator.setDuration(WHEEL_ROTATE_DURATION);
        this.rotateAnimator.setInterpolator(new LinearInterpolator());
        this.rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        this.rotateAnimator.setRepeatMode(ValueAnimator.RESTART);
        this.rotateAnimator.addUpdateListener(animation -> {
            this.degrees = (float) animation.getAnimatedValue();
            invalidate();
        });
        this.rotateAnimator.start();
    }

    public void stop(int index) {
        stop(index, true);
    }

    /**
     * 顺时针转动停留到扇形中间（扇形中间对齐中垂线上半部分）
     * @param index 停留的扇形index（中奖index）
     */
    public void stop(int index, boolean showAnim) {
        if (null != this.rotateAnimator) {
            this.rotateAnimator.cancel();
        }
        this.degrees = (this.degrees + FULL_ROTATION_DEGREE) % FULL_ROTATION_DEGREE; // 计算出当前转盘转动的角度
        float targetDegrees = FULL_ROTATION_DEGREE * (index * 2 + 1) / (this.sectionsCount * 2); // 计算出停止index对应的目标角度
        // 保证转动角度在180-540之间
        while (targetDegrees - this.degrees < STRAIGHT_ANGLE_DEGREE) {
            targetDegrees += FULL_ROTATION_DEGREE;
        }
        if (!showAnim) {
            this.degrees = targetDegrees;
            invalidate();
            flashLight(index);
            return;
        }
        // 转动时间为按正常无限转动时的速度转动这个角度所需时间的两倍，乘以2是因为DecelerateInterpolator的斜率在0的时候为2
        long duration = (long) ((targetDegrees - this.degrees) * WHEEL_ROTATE_DURATION * 2 / FULL_ROTATION_DEGREE);
        this.rotateAnimator = ValueAnimator.ofFloat(this.degrees, targetDegrees);
        this.rotateAnimator.setDuration(duration);
        this.rotateAnimator.setInterpolator(new DecelerateInterpolator());
        this.rotateAnimator.addUpdateListener(animation -> {
            this.degrees = (float) animation.getAnimatedValue();
            invalidate();
        });
        this.rotateAnimator.addListener(new AnimatorEndListener(() -> flashLight(index)));
        this.rotateAnimator.start();
    }

    /**
     * 直接移除，不需要动画
     * @param index 移除的index
     */
    public void removeIndex(int index) {
        if (removeSection(index, false)) {
            removeViewAt(index);
        }
        if (this.count > 0) {
            this.count --;
        }
        if (null != this.onPrizeListener) {
            onPrizeListener.onPrize(index, false);
        }
        alignSection(index, false);
        this.lightIndex = -1;
    }

    /**
     * 获取头像中心点相对于该转盘的相对坐标
     * @param point 相对坐标
     */
    public void getOutPosition(@NonNull PointF point) {
        PrizeSectionView view = (PrizeSectionView) getChildAt(0);
        view.getPosition(point);
        point.set(this.bounds.centerX() + point.x, this.bounds.top + point.y);
    }

    public void setOnPrizeListener(OnPrizeListener onPrizeListener) {
        this.onPrizeListener = onPrizeListener;
    }

    private void animAddView(@NonNull View child, boolean showAnim) {
        if (addSection(this.sectionsCount, showAnim)) {
            addView(child);
        }
    }

    /**
     * 动画添加扇形
     * @param index 添加的位置
     */
    public boolean addSection(int index, boolean showAnim) {
        if (MAX_SECTION_COUNT > this.sectionsCount && this.sectionsCount >= index) {
            this.sectionsCount++;
            float degrees = FULL_ROTATION_DEGREE / this.sectionsCount;
            int realIndex = index;
            for (int i = 0, j = 0; i < this.sections.size(); i++) {
                Section section = this.sections.get(i);
                if (section.available) {
                    if (showAnim) {
                        section.animToDegree(degrees);
                        section.animToBackgroundColor(getBackgroundColor(j));
                    } else {
                        section.degrees = degrees;
                        section.backgroundColor = getBackgroundColor(j);
                    }
                    j ++;
                } else {
                    if (showAnim) {
                        section.animToDegree(ZERO_DEGREE);
                    } else {
                        section.degrees = ZERO_DEGREE;
                    }
                    if (i < realIndex) {
                        realIndex ++;
                    }
                }
            }
            Section section = new Section();
            section.available = true;
            section.backgroundColor = getBackgroundColor(realIndex);
            if (showAnim) {
                section.animToDegree(degrees);
            } else {
                section.degrees = degrees;
            }
            this.sections.add(realIndex, section);
            if (!showAnim) {
                invalidate();
            }
            return true;
        }
        return false;
    }

    /**
     * 动画移除某一扇形
     * @param index 移除的index
     */
    public boolean removeSection(int index, boolean showAnim) {
        if (MIN_SECTION_COUNT < this.sectionsCount && this.sectionsCount > index) {
            this.sectionsCount--;
            float degrees = FULL_ROTATION_DEGREE / this.sectionsCount; // 
            Iterator<Section> iterator = this.sections.iterator();
            int j = 0;
            while (iterator.hasNext()) {
                Section section = iterator.next();
                if (section.available) {
                    if (0 == index) {
                        section.available = false;
                        if (showAnim) {
                            section.animToDegree(ZERO_DEGREE);
                        } else {
                            section.degrees = ZERO_DEGREE;
                            iterator.remove();
                        }
                    } else {
                        if (showAnim) {
                            section.animToDegree(degrees);
                            section.animToBackgroundColor(getBackgroundColor(j));
                        } else {
                            section.degrees = degrees;
                            section.backgroundColor = getBackgroundColor(j);
                        }
                        j ++;
                    }
                    index --;
                } else {
                    if (showAnim) {
                        section.animToDegree(ZERO_DEGREE);
                    } else {
                        section.degrees = ZERO_DEGREE;
                        iterator.remove();
                    }
                }
            }
            if (!showAnim) {
                invalidate();
            }
            return true;
        }
        return false;
    }

    private int getBackgroundColor(int index) {
        return BACKGROUND_COLORS[this.sectionsCount][index];
    }

    /**
     * 闪烁扇形边缘，一次只能闪烁一片
     * @param index 闪烁的扇形index
     */
    private void flashLight(int index) {
        this.lightIndex = index;
        if (null != this.lightAppearAnimator) {
            this.lightAppearAnimator.cancel();
        }
        this.lightAppearAnimator = ValueAnimator.ofInt(this.bounds.centerY(), this.bounds.top);
        this.lightAppearAnimator.setDuration(LIGHT_APPEAR_DURATION);
        this.lightAppearAnimator.addUpdateListener(animation -> {
            this.lightAppearY = (int) animation.getAnimatedValue();
            invalidate();
        });
        this.lightAppearAnimator.start();

        if (null != this.lightAlphaAnimator) {
            this.lightAlphaAnimator.cancel();
        }
        this.lightAlphaAnimator = ValueAnimator.ofInt(0, DARK_ALPHA, DEFAULT_ALPHA, DARK_ALPHA, DEFAULT_ALPHA);
        this.lightAlphaAnimator.setDuration(LIGHT_ALPHA_DURATION);
        this.lightAlphaAnimator.addUpdateListener(animation -> {
            this.lightAlpha = (int) animation.getAnimatedValue();
            invalidate();
        });
        this.lightAlphaAnimator.addListener(new AnimatorEndListener(() -> {
            if (removeSection(this.lightIndex, true)) {
                removeViewAt(this.lightIndex);
            }
            if (this.count > 0) {
                this.count --;
            }
            if (null != this.onPrizeListener) {
                onPrizeListener.onPrize(this.lightIndex, true);
            }
            alignSection(this.lightIndex, true);
            this.lightIndex = -1;
        }));
        this.lightAlphaAnimator.start();
    }

    /**
     * 转动最小的角度，将目标index的右侧对齐中垂线上半部分
     * @param index 对齐的扇形index
     */
    private void alignSection(int index, boolean showAnim) {
        // 0.0 <= alignDegrees <= 360.0
        float alignDegrees = FULL_ROTATION_DEGREE * index / this.sectionsCount;
        // -360.0 < this.degrees，如果添加了其他函数导致this.degrees可能小于-360.0的，需要修改该方法
        this.degrees = (this.degrees + FULL_ROTATION_DEGREE) % FULL_ROTATION_DEGREE;
        if (this.degrees > alignDegrees) {
            if (this.degrees - alignDegrees > alignDegrees + FULL_ROTATION_DEGREE - this.degrees) {
                alignDegrees += FULL_ROTATION_DEGREE;
            }
        } else {
            if (alignDegrees - this.degrees > this.degrees + FULL_ROTATION_DEGREE - alignDegrees) {
                alignDegrees -= FULL_ROTATION_DEGREE;
            }
        }
        if (null != this.rotateAnimator) {
            this.rotateAnimator.cancel();
        }
        if (showAnim) {
            this.rotateAnimator = ValueAnimator.ofFloat(this.degrees, alignDegrees);
            this.rotateAnimator.setDuration(SECTION_DURATION);
            this.rotateAnimator.addUpdateListener(animation -> {
                this.degrees = (float) animation.getAnimatedValue();
                invalidate();
            });
            this.rotateAnimator.addListener(new AnimatorEndListener(() -> {
                if (null != this.onPrizeListener) {
                    this.onPrizeListener.onRemoveFinish(this.count);
                }
            }));
            this.rotateAnimator.start();
        } else {
            this.degrees = alignDegrees;
            invalidate();
            if (null != this.onPrizeListener) {
                this.onPrizeListener.onRemoveFinish(this.count);
            }
        }
    }

    private class Section {
        private boolean available;
        private float degrees;
        private int backgroundColor;
        private ValueAnimator degreesAnimator;
        private ValueAnimator backgroundColorAnimator;

        private void animToBackgroundColor(int color) {
            if (null != this.backgroundColorAnimator) {
                this.backgroundColorAnimator.cancel();
            }
            this.backgroundColorAnimator = ValueAnimator.ofInt(this.backgroundColor, color);
            this.backgroundColorAnimator.setEvaluator(ColorEvaluator.getInstance());
            this.backgroundColorAnimator.setDuration(SECTION_DURATION);
            this.backgroundColorAnimator.addUpdateListener(animation -> {
                this.backgroundColor = (int) animation.getAnimatedValue();
                invalidate();
            });
            this.backgroundColorAnimator.start();
        }

        private void animToDegree(float targetDegree) {
            if (null != this.degreesAnimator) {
                this.degreesAnimator.cancel();
            }
            this.degreesAnimator = ValueAnimator.ofFloat(this.degrees, targetDegree);
            this.degreesAnimator.setDuration(SECTION_DURATION);
            this.degreesAnimator.addUpdateListener(animation -> {
                this.degrees = (float) animation.getAnimatedValue();
                System.out.println("=====================> Section: " + this + "  degrees: " + this.degrees + "  value: " + animation.getAnimatedValue());
                invalidate();
            });
            this.degreesAnimator.addListener(new AnimatorEndListener(() -> {
                if (!available) {
                    PrizeWheelView.this.sections.remove(Section.this);
                }
            }));
            this.degreesAnimator.start();
        }
    }

    private static class AnimatorEndListener implements Animator.AnimatorListener {
        @NonNull
        private final Runnable runnable;
        private boolean isCancel;

        private AnimatorEndListener(@NonNull Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (!this.isCancel) {
                this.runnable.run();
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            this.isCancel = true;
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

    public interface OnPrizeListener {
        void onPrize(int index, boolean prize); // prize 是否正常开奖回调
        void onRemoveFinish(int childCount);
    }
}
