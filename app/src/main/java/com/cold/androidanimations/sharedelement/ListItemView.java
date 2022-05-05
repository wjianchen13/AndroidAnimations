package com.cold.androidanimations.sharedelement;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cold.androidanimations.R;

/**
 * 雷达扫描
 */
public class ListItemView extends LinearLayout implements View.OnClickListener{
    
    private Context mContext;
    private TextView tvTest1;
    private ImageView imgvTest1;
    private ImageView imgvTest2;
    private ImageView imgvTest3;
    private ListCallBack mCallBack;

    public void setCallBack(ListCallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    public ListItemView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ListItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ListItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context) {
        initView(context, null);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.item_shared_element, this, true);
        this.mContext = context;
        tvTest1 = findViewById(R.id.tv_test);
        imgvTest1 = findViewById(R.id.imgv_test1);
        imgvTest2 = findViewById(R.id.imgv_test2);
        imgvTest3 = findViewById(R.id.imgv_test3);
        imgvTest1.setOnClickListener(this);
        imgvTest2.setOnClickListener(this);
        imgvTest3.setOnClickListener(this);
    }
    
    public void setUi(String t, String url1, String url2, String url3) {
        tvTest1.setText(t);
        Glide.with(mContext).load(url1).apply(new RequestOptions().centerCrop()).into(imgvTest1);
        Glide.with(mContext).load(url2).apply(new RequestOptions().centerCrop()).into(imgvTest2);
        Glide.with(mContext).load(url3).apply(new RequestOptions().centerCrop()).into(imgvTest3);
    }

    public ImageView getView(int p) {
        if(p == 2) {
            return imgvTest3;
        } else if(p == 1) {
            return imgvTest2;
        }
        return imgvTest1;
    }

    @Override
    public void onClick(View v) {
        if(mCallBack == null)
            return ;
        int id = v.getId();
        if(id == R.id.imgv_test1) {
            mCallBack.onChildClick(this, imgvTest1, 0);
        } else if(id == R.id.imgv_test2) {
            mCallBack.onChildClick(this, imgvTest2, 1);
        } else if(id == R.id.imgv_test3) {
            mCallBack.onChildClick(this, imgvTest3, 2);
        }
    }

    public interface ListCallBack {
        void onChildClick(View parent, View v, int position);
    }
    
    public int dip2px(float dpValue) {
        return dip2px(getContext(),dpValue);
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
    
    public static void println(String str) {
        System.out.println("==========================> " + str);
    }
    
}
