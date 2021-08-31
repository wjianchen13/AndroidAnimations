package com.cold.androidanimations.rotate;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.cold.androidanimations.R;

import java.util.ArrayList;
import java.util.List;

public class RotateViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

//    private RotateView rtvTest;
    private NestedGridView gvTest;
    private RotateAdapter adapter;
    private List<ToolsBean> datas =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_view);
//        rtvTest = (RotateView)findViewById(R.id.rtv_test);
        gvTest = (NestedGridView)findViewById(R.id.gv_test);
        adapter = new RotateAdapter(this, datas);
        gvTest.setAdapter(adapter);
        gvTest.setOnItemClickListener(this);
        initDatas();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (datas != null && datas.size() > 0) {
            datas.get(position).setSelect(true);
            int index = adapter.getsIndex();
            if(index >= 0 && index < datas.size()) {
                datas.get(index).setSelect(false);
            }
            adapter.setsIndex(position);
//            boolean isSelect = datas.get(position).isSelect();
//            datas.get(position).setSelect(!isSelect);
//            for (int i = 0; i < datas.size(); i++) {
//                if (i != position) {
//                    datas.get(i).isSeen = false;
//                }
//            }
            adapter.notifyDataSetChanged();
        }
    }

    public void initDatas() {
        for(int i = 0; i < 2; i ++) {
            ToolsBean bean = new ToolsBean(i);
            datas.add(bean);
        }
        adapter.notifyDataSetChanged();
    }

    public void onTest(View v) {
//        rtvTest.flipCard(v);
    }

}
