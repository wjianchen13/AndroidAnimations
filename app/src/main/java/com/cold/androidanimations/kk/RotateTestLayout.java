package com.cold.androidanimations.kk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cold.androidanimations.R;

/**
 * 测试子View旋转动画
 * 
 */
public class RotateTestLayout extends FrameLayout {
    
    private static final String TAG = "CustomParamsLayout";
    
    private Adapter mAdapter;

    private float mWidth;
    private float mHeight;
    private Bitmap bitmap;
    private int mBitmapWidth;

    private Paint mPaint = new Paint();

    private Matrix matrix = new Matrix();
    private Context mContext;

    /**
     * 绘制背景
     */
    private Paint mBackgroundPaint = new Paint();

    public RotateTestLayout(Context context) {
        super(context);
        this.mContext = context;
        initBackgroundPaint();
        init();
    }

    public RotateTestLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
        initBackgroundPaint();
        init();
    }
    
    public RotateTestLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
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
            mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.argb(255, 0, 0, 255));       //设置画笔颜色
        mBackgroundPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mBackgroundPaint.setStrokeWidth(5.0f);         //设置画笔宽度
    }
    
    private void init() {
        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_count_down_2);
        mBitmapWidth = bitmap.getWidth();
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
//            canvas.rotate(degrees / 2);
            for(int i = 0; i < mAdapter.getCount(); i ++) {
                canvas.drawLine(0, 0, 0, -center, mBackgroundPaint);
                canvas.rotate(degrees);
            }
            canvas.restore();
        }
    }

    /**
     * 绘制半圆背景 绘制背景，几个颜色
     * @param degrees 每次画布旋转角度
     */
    private void drawArcBackground(Canvas canvas, float degrees) {
//        if(canvas != null && mAdapter != null && mAdapter.getCount() > 0) {
//            canvas.save();
//            canvas.translate(mWidth / 2 - mBitmapWidth / 2, mHeight / 2);
//            for(int i = 0; i < mAdapter.getCount(); i ++) {
//                canvas.drawBitmap(bitmap, matrix, mBackgroundPaint);
//                canvas.rotate(degrees,mBitmapWidth / 2,0);
//            }
//            canvas.restore();
//        }
    }

//    @Override
//    protected void onLayout( boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//        if(mAdapter == null || getChildCount() != mAdapter.getCount())
//            return ;
//        for(int i = 0; i < mAdapter.getCount(); i ++) {
//            View v = getChildAt(i);
////            setRotation(v, getClildRotation(i));
//        }
//        
//    }


    /**
     * 获取每个View的旋转角度
     * @return
     */
    private float getClildRotation(int position) {
        if(mAdapter == null || mAdapter.getCount() == 0) {
            return 0f;
        }
//        return (float)360 / mAdapter.getCount() * position;
        return 360 / mAdapter.getCount() * (mAdapter.getCount() - (position + 1)) + 360 / mAdapter.getCount() / 2;
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
            final View v = mAdapter.getView(i);
            final int j = i;
            addView(v);
            v.post(new Runnable() {
                @Override
                public void run() {
                    setRotation(v, getClildRotation(j));
                }
            });
        }
        
    }

    /**
     * 设置旋转角度，setPivotX setPivotY是相对于View自身的位置
     * @param v
     * @param rotation
     */
//    private void setRotation(View v, float rotation) {
//        if(v != null) {
//            v.setPivotX(v.getWidth() / 2);
//            v.setPivotY(v.getHeight());
//            v.setRotation(rotation);
//        }
//    }

    /**
     * 设置一个View的角度，不带动画
     * @param v
     * @param rotate
     */
    private void setRotation(final View v, final float rotate) {
        if(v == null) {
            Toast.makeText(mContext, "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        v.post(new Runnable() {
            @Override
            public void run() {
                v.setPivotX(v.getWidth() / 2);
                v.setPivotY(v.getHeight());
                v.setRotation(rotate);
                v.setVisibility(View.VISIBLE);
            }
        });

    }

    /**
     * 旋转动画分步测试
     * @param rotation
     */
    public void setRotation1(float rotation) {
        View v = getChildAt(0);
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





















































