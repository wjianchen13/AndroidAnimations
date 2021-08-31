package com.cold.androidanimations.kk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.cold.androidanimations.R;
/**
 * 转盘
 * 1. 点击2次添加数据的时候报错：did not set the measured dimension by calling setMeasuredDimension()
 * 原因，因为在onMeasure判断了getChildCount和mAdapter.getCount不一样，导致没有调用setMeasuredDimension
 * 
 */
public class PanParamsLayout extends ViewGroup {
    
    private static final String TAG = "CustomParamsLayout";
    
    private Adapter mAdapter;

    private float mWidth;
    private float mHeight;
    private Bitmap bitmap;
    private int mBitmapWidth;

    private Paint mPaint = new Paint();

    private Matrix matrix = new Matrix();

    /**
     * 绘制背景
     */
    private Paint mBackgroundPaint = new Paint();

    public PanParamsLayout(Context context) {
        super(context);
        initBackgroundPaint();
        init();
    }

    public PanParamsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initBackgroundPaint();
        init();
    }
    
    public PanParamsLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initBackgroundPaint();
        init();
    }

    /**
     * 绘制背景画笔
     */
    private void initBackgroundPaint() {
        if(mBackgroundPaint == null)
            return ;
        mBackgroundPaint.setColor(Color.argb(255, 255, 0, 0));       //设置画笔颜色
        mBackgroundPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mBackgroundPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    /**
     * 绘制背景画笔
     */
    private void initLinesPaint() {
        if(mBackgroundPaint == null)
            return ;
        mBackgroundPaint.setColor(Color.argb(255, 0, 0, 255));       //设置画笔颜色
        mBackgroundPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mBackgroundPaint.setStrokeWidth(5.0f);         //设置画笔宽度
    }
    
    private void init() {
        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_count_down_2);
        mBitmapWidth = bitmap.getWidth();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new PanLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new PanLayoutParams (p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new PanLayoutParams (LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof PanLayoutParams;
    }

    /**
     * 要求所有的孩子测量自己的大小，然后根据这些孩子的大小完成自己的尺寸测量
     */
    @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec) {
        // 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式  
        if(mAdapter != null && getChildCount() != mAdapter.getCount()) // 每次addView都会onMeasure和onLayout，防止过度measure
            return ;
        int widthMode = MeasureSpec. getMode(widthMeasureSpec);
        int heightMode = MeasureSpec. getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec. getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec. getSize(heightMeasureSpec);
        int layoutWidth = 0;
        int layoutHeight = 0;
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int cWidth = 0;
        int cHeight = 0;
        int count = getChildCount();

        if(widthMode == MeasureSpec. EXACTLY){
            // 如果布局容器的宽度模式是确定的（具体的size或者match_parent），直接使用父窗体建议的宽度
            layoutWidth = sizeWidth;
        } else {
            // 如果是未指定或者wrap_content，我们都按照包裹内容做，宽度方向上只需要拿到所有子控件中宽度做大的作为布局宽度
            for ( int i = 0; i < count; i++)  {
                View child = getChildAt(i);
                cWidth = child.getMeasuredWidth();
                //获取子控件最大宽度
                layoutWidth = cWidth > layoutWidth ? cWidth : layoutWidth;
            }
        }
        // 高度很宽度处理思想一样
        if(heightMode == MeasureSpec. EXACTLY){
            layoutHeight = sizeHeight;
        } else{
            for ( int i = 0; i < count; i++)  {
                View child = getChildAt(i);
                cHeight = child.getMeasuredHeight();
                layoutHeight = cHeight > layoutHeight ? cHeight : layoutHeight;
            }
        }

        // 测量并保存layout的宽高
        setMeasuredDimension(layoutWidth, layoutHeight);
    }

    @Override
    protected void onLayout( boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        if(mAdapter != null && getChildCount() != mAdapter.getCount())
            return ;
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;
        PanLayoutParams params = null;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            // 注意此处不能使用getWidth和getHeight，这两个方法必须在onLayout执行完，才能正确获取宽高
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();

            params = (PanLayoutParams) child.getLayoutParams();
            switch (params.position) {
                case PanLayoutParams.POSITION_MIDDLE:    // 中间
                    left = (getWidth() - childMeasureWidth) / 2;
                    top = (getHeight() - childMeasureHeight) / 2;
                    break;
                case PanLayoutParams.POSITION_LEFT:      // 左上方
                    left = 0;
                    top = 0;
                    break;
                case PanLayoutParams.POSITION_RIGHT:     // 右上方
                    left = getWidth() - childMeasureWidth;
                    top = 0;
                    break;
                case PanLayoutParams.POSITION_BOTTOM:    // 左下角
                    left = 0;
                    top = getHeight() - childMeasureHeight;
                    break;
                case PanLayoutParams.POSITION_RIGHTANDBOTTOM:// 右下角
                    left = getWidth() - childMeasureWidth;
                    top = getHeight() - childMeasureHeight;
                    break;
                case PanLayoutParams.POSITION_CENTERHORIZONTAL: // 水平居中
                    left = (getWidth() - childMeasureWidth) / 2;
                    top = 0;
                    break;

                default:
                    break;
            }

            // 确定子控件的位置
            child.layout(left, top, left + childMeasureWidth, top + childMeasureHeight);
            setRotation(child, getClildRotation(i));
        }
        mWidth = getWidth();
        mHeight = getHeight();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        drawBackground(canvas);
        drawLines(canvas, mAdapter != null && mAdapter.getCount() > 0 ? (float)360 / mAdapter.getCount() : 1);
        drawArcBackground(canvas, mAdapter != null && mAdapter.getCount() > 0 ? (float)360 / mAdapter.getCount() : 1);
        super.dispatchDraw(canvas);
    }

    /**
     * 绘制背景
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        if(mBackgroundPaint != null && canvas != null) {
            initBackgroundPaint();
            int center = getWidth() / 2;
            canvas.drawCircle(center,center,center, mBackgroundPaint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆。
        }
    }

    /**
     * 绘制每块区域的分割线
     * @param degrees 画布旋转角度
     */
    private void drawLines(Canvas canvas, float degrees) {
        initLinesPaint();
        if(canvas != null && mAdapter != null && mAdapter.getCount() > 0) {
            int center = getWidth() / 2;
            canvas.save();
            canvas.translate(center, center);
            canvas.rotate(degrees / 2);
            for(int i = 0; i < mAdapter.getCount(); i ++) {
                canvas.drawLine(0, 0, 0, -center, mBackgroundPaint);
                canvas.rotate(degrees);
            }
            canvas.restore();
        }
    }

    /**
     * 绘制半圆背景
     * @param degrees 每次画布旋转角度
     */
    private void drawArcBackground(Canvas canvas, float degrees) {
        if(canvas != null && mAdapter != null && mAdapter.getCount() > 0) {
            canvas.save();
            canvas.translate(mWidth / 2 - mBitmapWidth / 2, mHeight / 2);
            for(int i = 0; i < mAdapter.getCount(); i ++) {
                canvas.drawBitmap(bitmap, matrix, mBackgroundPaint);
                canvas.rotate(degrees,mBitmapWidth / 2,0);
            }
            canvas.restore();
        }
    }

    /**
     * 获取每个View的旋转角度
     * @return
     */
    private float getClildRotation(int position) {
        if(mAdapter == null || mAdapter.getCount() == 0) {
            return 0f;
        }
        return (float)360 / mAdapter.getCount() * position;
        
    }

    /**
     * setAdapter
     * @param mAdapter
     */
    public void setAdapter(Adapter mAdapter) {
        this.mAdapter = mAdapter;
        removeAllViews(); // 
        addAllView();
    }
    
    
//    /**
//     * 添加数据
//     */
//    public void addData(PanItem item) {
//        if(item == null) {
//            throw new IllegalStateException("添加的数据不能为空");
//        }
//        if(mData == null) {
//           mData = new ArrayList<>();
//        }
//        mData.add(item);
//    }

    /**
     * 添加子View
     * @param 
     */
    public void addAllView() {
//        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_pan, null);
//        PanLayoutParams lp = new PanLayoutParams(Utils.dip2px(getContext(), 60), Utils.dip2px(getContext(), 150));
//        lp.position = PanLayoutParams.POSITION_CENTERHORIZONTAL;
//        layout.setLayoutParams(lp);
//        addView(layout);
        if(mAdapter == null || mAdapter.getCount() == 0) {
            return ;
        }
        for(int i = 0; i < mAdapter.getCount(); i ++) {
            addView(mAdapter.getView(i));
        }
        
    }

    /**
     * 设置旋转角度，setPivotX setPivotY是相对于View自身的位置
     * @param v
     * @param rotation
     */
    private void setRotation(View v, float rotation) {
        if(v != null) {
            v.setPivotX(v.getWidth() / 2);
            v.setPivotY(v.getHeight());
            v.setRotation(rotation);
        }
    }

    @Override
    public void addView(View child) {
        super.addView(child);
    }
}





















































