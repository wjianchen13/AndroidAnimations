package com.cold.androidanimations.kk;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RotateTestActivity extends AppCompatActivity {

    private RotateTestLayout plTest;
    private TotateTestAdapter mAdapter;
    private float rotation = 10.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_test);

        plTest = findViewById(R.id.pl_test);
    }

    /**
     * 开奖测试
     * @param v
     */
    public void onOpen1(View v) {
        plTest.open(0);
    }

    /**
     * 开奖测试
     * @param v
     */
    public void onOpen2(View v) {
//        int start = 5;
//        int end = 8;
//        Random random = new Random();
//        int a = random.nextInt(end - start) + start;
//        for(int i = 0; i < 20; i ++)
//            System.out.println("==============> a" + i + " : " + (random.nextInt(end - start) + start));
        plTest.open(1);
    }

    /**
     * 开奖测试
     * @param v
     */
    public void onOpen3(View v) {
        plTest.open(2);
    }


    /**
     * 开奖测试
     * @param v
     */
    public void onOpen4(View v) {
        plTest.open(3);
    }


    /**
     * 开奖测试
     * @param v
     */
    public void onOpen5(View v) {
        plTest.open(4);
    }


    /**
     * 开奖测试
     * @param v
     */
    public void onOpen6(View v) {
        plTest.open(5);
    }


    /**
     * 开奖测试
     * @param v
     */
    public void onOpen7(View v) {
        plTest.open(6);
    }


    /**
     * 开奖测试
     * @param v
     */
    public void onOpen8(View v) {
        plTest.open(7);
    }

    /**
     * 删除测试
     * @param v
     */
    public void onDelete1(View v) {
        plTest.deleteItem(4);
    }

    
    /**
     * 旋转动画
     * @param v
     */
    public void onAdd11(View v) {
        plTest.setRotation1(rotation);
        rotation += 10.0f;
    }

    public void onAdd10(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test21", ""));
        data.add(new PanItem("test22", ""));
        plTest.setAdapter(mAdapter = new TotateTestAdapter(this, data));
    }
    
    public void onTest(View v) {
        final LayoutInflater inflater = LayoutInflater.from(this);

        // 布局参数有效
//        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.item_pan, plTest, false);
//        plTest.addView(layout);

//        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.item_pan, null);
//        PanLayoutParams lp = new PanLayoutParams(dip2px(60),dip2px(150));
//        lp.position = PanLayoutParams.POSITION_CENTERHORIZONTAL;
//        layout.setLayoutParams(lp); 
//        plTest.addView(layout);

        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test1", ""));
        data.add(new PanItem("test2", ""));
        data.add(new PanItem("test3", ""));
        data.add(new PanItem("test4", ""));
        data.add(new PanItem("test5", ""));
        data.add(new PanItem("test6", ""));
        data.add(new PanItem("test7", ""));
        data.add(new PanItem("test8", ""));
        data.add(new PanItem("test9", ""));
        plTest.setAdapter(mAdapter = new TotateTestAdapter(this, data));
    }

    public void onAdd2(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test21", ""));
        data.add(new PanItem("test22", ""));
        plTest.setAdapter(mAdapter = new TotateTestAdapter(this, data));
    }

    public void onAdd3(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test31", ""));
        data.add(new PanItem("test32", ""));
        data.add(new PanItem("test33", ""));
        plTest.setAdapter(mAdapter = new TotateTestAdapter(this, data));
    }

    public void onAdd4(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test41", ""));
        data.add(new PanItem("test42", ""));
        data.add(new PanItem("test43", ""));
        data.add(new PanItem("test44", ""));
        plTest.setAdapter(mAdapter = new TotateTestAdapter(this, data));
    }

    public void onAdd5(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test51", ""));
        data.add(new PanItem("test52", ""));
        data.add(new PanItem("test53", ""));
        data.add(new PanItem("test54", ""));
        data.add(new PanItem("test55", ""));
        plTest.setAdapter(mAdapter = new TotateTestAdapter(this, data));
    }

    public void onAdd6(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test61", ""));
        data.add(new PanItem("test62", ""));
        data.add(new PanItem("test63", ""));
        data.add(new PanItem("test64", ""));
        data.add(new PanItem("test65", ""));
        data.add(new PanItem("test66", ""));
        plTest.setAdapter(mAdapter = new TotateTestAdapter(this, data));
    }

    public void onAdd7(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test71", ""));
        data.add(new PanItem("test72", ""));
        data.add(new PanItem("test73", ""));
        data.add(new PanItem("test74", ""));
        data.add(new PanItem("test75", ""));
        data.add(new PanItem("test76", ""));
        data.add(new PanItem("test77", ""));
        plTest.setAdapter(mAdapter = new TotateTestAdapter(this, data));
    }

    public void onAdd8(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test81", ""));
        data.add(new PanItem("test82", ""));
        data.add(new PanItem("test83", ""));
        data.add(new PanItem("test84", ""));
        data.add(new PanItem("test85", ""));
        data.add(new PanItem("test86", ""));
        data.add(new PanItem("test87", ""));
        data.add(new PanItem("test88", ""));
        plTest.setAdapter(mAdapter = new TotateTestAdapter(this, data));
    }

    public void onAdd9(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test91", ""));
        data.add(new PanItem("test92", ""));
        data.add(new PanItem("test93", ""));
        data.add(new PanItem("test94", ""));
        data.add(new PanItem("test95", ""));
        data.add(new PanItem("test96", ""));
        data.add(new PanItem("test97", ""));
        data.add(new PanItem("test98", ""));
        data.add(new PanItem("test99", ""));
        plTest.setAdapter(mAdapter = new TotateTestAdapter(this, data));
    }
    
    /**
     * 插入View
     * @param v
     */
    public void onInsert(View v) {
        plTest.insertView(new PanItem("insert", ""));
    }

    /**
     * 调整已经存在的View的位置
     * @param v
     */
    public void onAdjust(View v) {
//        mAdapter.set();
        plTest.adjustView();
    }

    /**
     * 启动旋转
     * @param v
     */
    public void onStart(View v) {
        plTest.setPivotX(plTest.getWidth() / 2);
        plTest.setPivotY(plTest.getHeight() / 2);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                plTest, "rotation", 0, 90);
        objectAnimator.setDuration(1000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }


    /**
     * 插入数据
     * @param v
     */
    public void onAdd(View v) {
        plTest.addItem(new PanItem("add", ""));
    }


    public int dip2px(float dpValue) {
        return dip2px(this,dpValue);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5f);
    }

    /**
     * 返回屏幕密度
     */
    public float getDensity(Context context) {
        try {
            return context.getResources().getDisplayMetrics().density;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2.0f;
    }
}
