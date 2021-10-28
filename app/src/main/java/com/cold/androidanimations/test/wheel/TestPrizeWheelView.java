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

public class TestPrizeWheelView extends ViewGroup {
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

    public TestPrizeWheelView(Context context) {
        this(context, null);
    }

    public TestPrizeWheelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestPrizeWheelView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        int paddingVertical = getPaddingTop() + getPaddingBottom(); // 计算padding的值
        int width = resolveSize(paddingHorizontal, widthMeasureSpec); 
        int height = resolveSize(paddingVertical, heightMeasureSpec); // 根据padding调整尺寸
        int size = Math.max(0, Math.max(width - paddingHorizontal, height - paddingVertical)); // 取宽高较大的那一个数值
        width = resolveSize(size + paddingHorizontal, widthMeasureSpec);
        height = resolveSize(size + paddingVertical, heightMeasureSpec);  // 以实际尺寸较大的那个值加上对应的padding
        size = Math.max(0, Math.min(width - paddingHorizontal, height - paddingVertical));
        int left = Math.max(0, getPaddingLeft() + (width - paddingHorizontal - size) / 2);
        int top = Math.max(0, getPaddingTop() + (height - paddingVertical - size) / 2);
        this.bounds.set(left, top, left + size, top + size);
        this.boundsF.set(this.bounds);
        measureChildren();
        setMeasuredDimension(width, height);
        this.lightBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.lightCanvas = new Canvas(this.lightBitmap);
    }

    private void measureChildren() {
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
        for (int i = 0, j = 0; i < this.sections.size(); i++) {
            Section section = this.sections.get(i);
//            canvas.rotate(- section.degrees / 2, this.boundsF.centerX(), this.boundsF.centerY());
            this.paint.setColor(section.backgroundColor);
            this.paint.setAlpha(DEFAULT_ALPHA);
            canvas.drawArc(this.boundsF, - RIGHT_ANGLE_DEGREE - section.degrees / 2, section.degrees, true, this.paint);
            if (section.available) {
//                if (this.lightIndex == j && null != this.lightBitmap && null != this.lightCanvas) {
//                    int lightCount = lightCanvas.save();
//                    lightCanvas.clipRect(this.bounds.left, this.lightAppearY, this.bounds.right, this.bounds.bottom);
//                    this.paint.setColor(LIGHT_COLOR);
//                    this.paint.setAlpha(this.lightAlpha);
//                    lightCanvas.drawArc(this.boundsF, - RIGHT_ANGLE_DEGREE - section.degrees / 2, section.degrees, true, this.paint);
//                    this.lightPaint.setColor(section.backgroundColor);
//                    lightCanvas.drawArc(this.boundsF, - RIGHT_ANGLE_DEGREE - section.degrees / 2, section.degrees, true, this.lightPaint);
//                    lightCanvas.restoreToCount(lightCount);
//                    // 直接绘制可能因为硬件加速无效果
//                    canvas.drawBitmap(this.lightBitmap, this.bounds, this.bounds, this.paint);
//                }
                drawChild(canvas, getChildAt(j), drawingTime);
                j ++;
            }
//            canvas.rotate(- section.degrees / 2, this.boundsF.centerX(), this.boundsF.centerY());
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
        if (MAX_SECTION_COUNT > this.sectionsCount && this.sectionsCount >= index) { // 不能大于9个
            this.sectionsCount++;
            float degrees = FULL_ROTATION_DEGREE / this.sectionsCount; // 平分圆的角度
            int realIndex = index; // 插入位置
            for (int i = 0, j = 0; i < this.sections.size(); i++) {
                TestPrizeWheelView.Section section = this.sections.get(i);
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
            TestPrizeWheelView.Section section = new TestPrizeWheelView.Section();
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

    private int getBackgroundColor(int index) {
        return BACKGROUND_COLORS[this.sectionsCount][index];
    }

    private class Section {
        private boolean available;
        private float degrees;
        private int backgroundColor;
        private ValueAnimator degreesAnimator;
        private ValueAnimator backgroundColorAnimator;

        private void animToDegree(float targetDegree) {
            if (null != this.degreesAnimator) {
                this.degreesAnimator.cancel();
            }
            this.degreesAnimator = ValueAnimator.ofFloat(this.degrees, targetDegree);
            this.degreesAnimator.setDuration(SECTION_DURATION);
            this.degreesAnimator.addUpdateListener(animation -> {
                this.degrees = (float) animation.getAnimatedValue();
                invalidate();
            });
            this.degreesAnimator.addListener(new AnimatorEndListener(() -> {
                if (!available) {
                    TestPrizeWheelView.this.sections.remove(Section.this);
                }
            }));
            this.degreesAnimator.start();
        }

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
