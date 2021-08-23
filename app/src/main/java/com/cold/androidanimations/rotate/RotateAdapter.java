package com.cold.androidanimations.rotate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.cold.androidanimations.R;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Created by admin on 2016/8/12.
 */
public class RotateAdapter extends BaseAdapter {

    private SoftReference<Context> mContextSoftReference;
    private List<ToolsBean> datas;
    private int sIndex = -1; // 选中Item

    public RotateAdapter(Context context, List<ToolsBean> datas){
        mContextSoftReference = new SoftReference<>(context);
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContextSoftReference.get()).inflate(R.layout.item_rotate_view, parent, false);
            holder.rtv_test = (RotateView)convertView.findViewById(R.id.rtv_test);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ToolsBean myToolBean = datas.get(position);
        fillData(holder,myToolBean);
        return convertView;
    }

    public int getsIndex() {
        return sIndex;
    }

    public void setsIndex(int sIndex) {
        this.sIndex = sIndex;
    }

    private void fillData(ViewHolder holder, ToolsBean myToolBean) {
        if(holder == null || myToolBean == null) {
            return ;
        }
        holder.rtv_test.setContent(myToolBean.getIndex());
        holder.rtv_test.flipCard(myToolBean.isSelect());
    }



    static class ViewHolder{

        private RotateView rtv_test;

    }
}
