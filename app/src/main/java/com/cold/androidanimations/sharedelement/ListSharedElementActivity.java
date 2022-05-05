package com.cold.androidanimations.sharedelement;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cold.androidanimations.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * https://blog.csdn.net/weixin_43901866/article/details/86598277
 */
public class ListSharedElementActivity extends AppCompatActivity {
    
    RecyclerView rvTest;
    private ListAdapter wxAdapter;
    private ArrayList<ListItem> list;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_shared_element);
        rvTest = findViewById(R.id.rv_test);
        initData();
        initView();
    }
    
    private void initData() {
        list = new ArrayList<>();
        ListItem item1 = new ListItem("test1",
                "http://timg.95.cn/upload/anchor_image/345X257/34/1617834_5620a6651eec0.jpg", 
                "http://timg.95.cn/upload/anchor_image/345X257/46/5005546_54cf47ac18d65.jpg", 
                "http://timg.95.cn/upload/anchor_image/345X257/10/4665210_54c61de379357.jpg");
        list.add(item1);
        ListItem item2 = new ListItem("test2",
                "http://timg.95.cn/upload/anchor_image/345X257/34/1617834_5620a6651eec0.jpg",
                "http://timg.95.cn/upload/anchor_image/345X257/46/5005546_54cf47ac18d65.jpg",
                "http://timg.95.cn/upload/anchor_image/345X257/10/4665210_54c61de379357.jpg");
        list.add(item2);

        ListItem item3 = new ListItem("test3",
                "http://timg.95.cn/upload/anchor_image/345X257/34/1617834_5620a6651eec0.jpg",
                "http://timg.95.cn/upload/anchor_image/345X257/46/5005546_54cf47ac18d65.jpg",
                "http://timg.95.cn/upload/anchor_image/345X257/10/4665210_54c61de379357.jpg");
        list.add(item3);
    }

    private void initView() {
//        list = new ArrayList<>();
//        list.add("http://timg.95.cn/upload/anchor_image/345X257/34/1617834_5620a6651eec0.jpg");
//        list.add("http://timg.95.cn/upload/anchor_image/345X257/46/5005546_54cf47ac18d65.jpg");
//        list.add("http://timg.95.cn/upload/anchor_image/345X257/10/4665210_54c61de379357.jpg");
//        list.add("http://timg.95.cn/upload/anchor_image/345X257/10/4992810_54cf16c34d8fe.jpg");
//        list.add("http://timg.95.cn/upload/anchor_image/345X257/70/4096170_5666c6b8d6bce.jpg");
//        list.add("http://timg.95.cn/upload/anchor_image/345X257/58/1561658_546b08af7aa1b.jpg");
//        list.add("http://timg.95.cn/upload/anchor_image/345X257/34/1617834_5620a6651eec0.jpg");
//        list.add("http://timg.95.cn/upload/anchor_image/345X257/46/5005546_54cf47ac18d65.jpg");
//        list.add("http://timg.95.cn/upload/anchor_image/345X257/10/4992810_54cf16c34d8fe.jpg");
//

        wxAdapter = new ListAdapter(list);
        rvTest.setLayoutManager(new LinearLayoutManager(ListSharedElementActivity.this));
        rvTest.setAdapter(wxAdapter);
        wxAdapter.bindToRecyclerView(rvTest);
//        wxAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                int pos = position;
//            }
//        });
//
//        wxAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if(view != null && view instanceof ListItemView) {
//                    ListItemView v = (ListItemView)view;
//                    ImageView iv = v.getView(position);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        share(iv, position);
//                    }
//                }
//
//            }
//        });
    }
    
    private ListItemView mItemView;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void share(View parent, View view, int position) {
        mItemView = (ListItemView)parent;
        Intent intent = new Intent(ListSharedElementActivity.this, SharedElementActivity2.class);
        intent.putStringArrayListExtra(SharedElementActivity2.IMG_KEY, list.get(position).getList());
        intent.putExtra(SharedElementActivity2.IMG_POSITION, position);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this, view, "share").toBundle();
        startActivity(intent, bundle);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            int exitPos = data.getIntExtra(SharedElementActivity2.IMG_CURRENT_POSITION, -1);
            final View exitView = getExitView(exitPos);
            if (exitView != null) {
                ActivityCompat.setExitSharedElementCallback(this, new SharedElementCallback() {
                    @Override
                    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                        names.clear();
                        sharedElements.clear();
                        names.add(ViewCompat.getTransitionName(exitView));
                        sharedElements.put(Objects.requireNonNull(ViewCompat.getTransitionName(exitView)), exitView);
                        setExitSharedElementCallback(new SharedElementCallback() {
                        });
                    }
                });
            }
        }
    }


    private View getExitView(int position) {
        if (position == -1) {
            return null;
        }
        if (mItemView != null) {
            return mItemView.getView(position);
        }
        return null;
    }

    class ListAdapter extends BaseQuickAdapter<ListItem, BaseViewHolder> implements ListItemView.ListCallBack {
        public ListAdapter(List<ListItem> list) {
            super(R.layout.item_list_shared_element, list);
        }

        @Override
        protected void convert(BaseViewHolder helper, ListItem item) {
            ListItemView lv = helper.getView(R.id.lv_test);
            lv.setCallBack(this);
            lv.setUi(item.getT(), item.getUrl1(), item.getUrl2(), item.getUrl3());
        }

        @Override
        public void onChildClick(View parent, View v, int position) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                share(parent, v, position);
            }
        }
    }

    
}
