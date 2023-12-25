package com.cold.androidanimations.expand;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.cold.androidanimations.R;

/**
 * 伸缩动画
 */
public class ExpandActivity extends AppCompatActivity {

    private ImageView ivSearch,ivClose;
    private EditText edSearch;
    private RelativeLayout laySearch;
    private AutoTransition autoTransition;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        initView();
    }

    private void initView() {
        laySearch = (RelativeLayout) findViewById(R.id.lay_search);
        ivSearch = (ImageView) findViewById(R.id.iv_search);
        ivClose = (ImageView) findViewById(R.id.iv_close);
        edSearch = (EditText) findViewById(R.id.ed_search);


        WindowManager manager = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;  //获取屏幕的宽度 像素

        /**
         * 输入法键盘的搜索监听
         */
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String city = edSearch.getText().toString();
                    if (!TextUtils.isEmpty(city)) {
                        Toast.makeText(ExpandActivity.this,city,Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ExpandActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });
    }

    /**
     * 点击事件
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search://点击搜索 伸展
                initExpand();
                break;
            case R.id.iv_close://点击close 关闭
                initClose();
                break;
        }
    }


    /*设置伸展状态时的布局*/
    public void initExpand() {
        edSearch.setHint("输入城市名");
        edSearch.setHintTextColor(Color.WHITE);
        edSearch.setVisibility(View.VISIBLE);
        ivClose.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams LayoutParams = (LinearLayout.LayoutParams) laySearch.getLayoutParams();
        LayoutParams.width = dip2px(px2dip(width) - 40);
        LayoutParams.setMargins(dip2px(0), dip2px(0), dip2px(0), dip2px(0));
        laySearch.setPadding(14,0,14,0);
        laySearch.setLayoutParams(LayoutParams);

        edSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                edSearch.setFocusable(true);
                edSearch.setFocusableInTouchMode(true);
                return false;
            }
        });
        //开始动画
        beginDelayedTransition(laySearch);

    }

    /*设置收缩状态时的布局*/
    private void initClose() {
        edSearch.setVisibility(View.GONE);
        edSearch.setText("");
        ivClose.setVisibility(View.GONE);

        LinearLayout.LayoutParams LayoutParams = (LinearLayout.LayoutParams) laySearch.getLayoutParams();
        LayoutParams.width = dip2px(48);
        LayoutParams.height = dip2px(48);
        LayoutParams.setMargins(dip2px(0), dip2px(0), dip2px(0), dip2px(0));
        laySearch.setLayoutParams(LayoutParams);

        //隐藏键盘
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);
        edSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edSearch.setCursorVisible(true);
            }
        });
        //开始动画
        beginDelayedTransition(laySearch);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void beginDelayedTransition(ViewGroup view) {
        autoTransition = new AutoTransition();
        autoTransition.setDuration(500);
        TransitionManager.beginDelayedTransition(view, autoTransition);
    }

    // dp 转成 px
    private int dip2px(float dpVale) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }

    // px 转成 dp
    private int px2dip(float pxValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
