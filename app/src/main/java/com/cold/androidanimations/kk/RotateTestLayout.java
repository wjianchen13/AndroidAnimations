package com.cold.androidanimations.kk;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cold.androidanimations.R;

/**
 * 测试子View旋转动画
 * 
 */
public class RotateTestLayout extends FrameLayout {

    /**
     * 插入View动画时间
     */
    private final int INSERT_TIME = 200;
    
    private static final String TAG = "CustomParamsLayout";
    
    private TestAdapter mAdapter;

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
     * 插入数据，其他View调整位置，View从中心移出
     * 顺序：
     * 1.已经存在的View调整位置
     * 2.新加的View从中心弹出
     * 顺序不能改变，计算方式已经固定了
     */
    public void addItem(PanItem item) {
        adjustView();
        insertView(item);
    }

    /**
     * 插入一个View，在Y轴负方向和X轴正方形处
     */
    public void insertView(PanItem item) {
        if(mAdapter == null || item == null) {
            Toast.makeText(mContext, "adapter == null || item == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        View v = mAdapter.insertItem(item);
        // 计算插入角度
        int angle = 360 / mAdapter.getCount() / 2;
        addView(v); // RelativeLayout添加子View
        v.setVisibility(View.INVISIBLE); // 先隐藏，后显示，否则会闪烁
        setRotate(v, angle);
    }

    /**
     * 插入View的时候，需要调整已经存在的View的角度
     */
    public void adjustView() {
        int count = getChildCount();
        for(int i = 0; i < count; i ++) {
            System.out.println("==================> view " + i + " rotate: " + getChildAt(i).getRotation() 
                    + "   rotateX: " + getChildAt(i).getRotationX() + "   rotateY: " + getChildAt(i).getRotationY());
        }
        float totalRotate = 360 / (count + 1); // 插入一个View，需要移动总共角度
        float vRotate = totalRotate / count / 2; // 移动一半的角度，因为view已经在中间 
        for(int i = 0; i < count; i ++ ) {
            setRotateWithAnim(getChildAt(i), getChildAt(i).getRotation(), getChildAt(i).getRotation() + vRotate * (2 * i + 1));
        }
    }

    /**
     * 设置一个View的初始角度，然后从中心处往外移动
     * @param v
     * @param rotate
     */
    private void setRotate(final View v, final float rotate) {
        if(v == null) {
            Toast.makeText(mContext, "setRotate view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        v.post(new Runnable() {
            @Override
            public void run() {
                v.setPivotX(v.getWidth() / 2);
                v.setPivotY(v.getHeight());
                v.setRotation(rotate);
                v.setVisibility(View.VISIBLE);
                Point p = getStartPoint(rotate, v.getHeight());
                setTranslation(v, p.x, -0.0f, p.y, 0.0f);
            }
        });

    }

    /**
     * 给定一个角度，获取一个View弹出的起始坐标
     * @param angle 角度，相对于Y轴负方向
     * @param currentAdius 半径
     * @return 计算好的起始点
     */
    private Point getStartPoint(float angle, int currentAdius) {
        Point center = new Point();
        center.x = 0;
        center.y = 0;
        double radian = Math.toRadians(convertAngle(angle));
        double x = center.x + Math.cos(radian) * currentAdius;
        double y = center.y + Math.sin(radian) * currentAdius;
        int x1 = (int)Math.ceil(x);
        int y1 = (int)Math.ceil(y);
        System.out.println("====================> x: " + x + "   y: " + y);
        System.out.println("====================> x1: " + x1 + "   y1: " + y1);
        return new Point(-x1, y1); // 这里计算的是相对于X轴正方形，Y坐标本来是正确的，只需要把X轴坐标取反
    }

    /**
     * 转换角度，传入的是相对Y轴负方向，转换成X轴计算圆点坐标
     * @return
     */
    private double convertAngle(float angle) {
        if(angle > 0  && angle < 90) { // 插入的时候都在右上方，所以角度都在这个范围
            return 90 - angle;
        }
        return angle;
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
            Toast.makeText(mContext, "setTranslation view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, "translationX", xFrom, xTo);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(v, "translationY", yFrom, yTo);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(translationX).with(translationY).with(alpha);
        animSet.setDuration(INSERT_TIME);
//        animSet.setStartDelay(50);
        animSet.start();
    }

    /**
     * 设置一个View的角度，带动画
     * @param v
     * @param from
     * @param to
     */
    private void setRotateWithAnim(View v, float from, float to) {
        if(v == null) {
            Toast.makeText(getContext(), "view == null", Toast.LENGTH_SHORT).show();
            return ;
        }
        v.setPivotX(v.getWidth() / 2);
        v.setPivotY(v.getHeight());
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v, "rotation", from, to);
        objectAnimator.setDuration(INSERT_TIME);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
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
    public void setAdapter(TestAdapter mAdapter) {
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





















































