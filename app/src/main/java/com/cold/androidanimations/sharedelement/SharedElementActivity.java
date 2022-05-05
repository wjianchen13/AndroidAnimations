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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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
public class SharedElementActivity extends AppCompatActivity {
    
    RecyclerView rvWx2;
    private WxAdapter wxAdapter;
    private ArrayList<String> list;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_shared_element);
        rvWx2 = findViewById(R.id.rv_test);
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        list.add("http://timg.95.cn/upload/anchor_image/345X257/34/1617834_5620a6651eec0.jpg");
        list.add("http://timg.95.cn/upload/anchor_image/345X257/46/5005546_54cf47ac18d65.jpg");
        list.add("http://timg.95.cn/upload/anchor_image/345X257/10/4665210_54c61de379357.jpg");
        list.add("http://timg.95.cn/upload/anchor_image/345X257/10/4992810_54cf16c34d8fe.jpg");
        list.add("http://timg.95.cn/upload/anchor_image/345X257/70/4096170_5666c6b8d6bce.jpg");
        list.add("http://timg.95.cn/upload/anchor_image/345X257/58/1561658_546b08af7aa1b.jpg");
        list.add("http://timg.95.cn/upload/anchor_image/345X257/34/1617834_5620a6651eec0.jpg");
        list.add("http://timg.95.cn/upload/anchor_image/345X257/46/5005546_54cf47ac18d65.jpg");
        list.add("http://timg.95.cn/upload/anchor_image/345X257/10/4992810_54cf16c34d8fe.jpg");


        wxAdapter = new WxAdapter(list);
        rvWx2.setLayoutManager(new GridLayoutManager(SharedElementActivity.this, 3));
        rvWx2.setAdapter(wxAdapter);
        wxAdapter.bindToRecyclerView(rvWx2);


        wxAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageView iv = view.findViewById(R.id.iv_wx_img);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    share(iv, position);
                }
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void share(View view, int position) {
        Intent intent = new Intent(SharedElementActivity.this, SharedElementActivity2.class);
        intent.putStringArrayListExtra(SharedElementActivity2.IMG_KEY, list);
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
        if (wxAdapter != null) {
            return wxAdapter.getViewByPosition(position, R.id.iv_wx_img);
        }
        return null;
    }

    class WxAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public WxAdapter(List<String> list) {
            super(R.layout.item_shared_element_wx2, list);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView iv_img = helper.getView(R.id.iv_wx_img);
            Glide.with(mContext).load(item).apply(new RequestOptions().centerCrop()).into(iv_img);
        }


    }

    
}
