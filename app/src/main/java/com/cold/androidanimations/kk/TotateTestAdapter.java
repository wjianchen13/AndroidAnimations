package com.cold.androidanimations.kk;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cold.androidanimations.R;
import com.cold.androidanimations.glide.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class TotateTestAdapter implements TestAdapter {
    
    View V3 = null;

    private String url = "https://tstatic.lelevideo.com/upload/anchor_image/372X380/31/1032231_1619695214.jpg";

    /**
     * 需要显示的View数据列表
     */
    private List<PanItem> mData;
    private Context mContext;

    public TotateTestAdapter(Context context, List<PanItem> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }
    
    @Override
    public Object getItem(int position) {
        return mData != null ? mData.get(position) : null;
    }
    
    @Override
    public long getItemId(int position) {
        return mData != null ? mData.get(position).getId() : -1;
    }
    
    @Override
    public View getView(int position) {
        if(mContext != null) {
            RelativeLayout view = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.item_pan, null);
//            PanLayoutParams lp = new PanLayoutParams(Utils.dip2px(mContext, 60), Utils.dip2px(mContext, 150));
//            lp.position = PanLayoutParams.POSITION_CENTERHORIZONTAL;
//            view.setLayoutParams(lp);
            FrameLayout.LayoutParams headParams = new FrameLayout.LayoutParams(Utils.dip2px(mContext, 60), Utils.dip2px(mContext, 150));
            headParams.gravity = Gravity.CENTER_HORIZONTAL;
            view.setLayoutParams(headParams);
            TextView tvTest = view.findViewById(R.id.tv_test);
            tvTest.setText(mData.get(position).getName());
            ImageView imgvTest = view.findViewById(R.id.imgv_test);
            GlideApp.with(mContext)
                    .load(url)
                    .circleCrop()
                    .into(imgvTest);
            V3 = view;
            return view;
        }
        return null;
    }

    @Override
    public View insertItem(PanItem item) {
        if(mData == null)
            mData = new ArrayList<>();
        mData.add(item);
        return getView(mData.size() - 1);
    }

    public void set() {
        if(V3 != null) {
            V3.findViewById(R.id.imgv_test).setBackgroundResource(R.drawable.ic_test);
        }
    }
    
}
