package com.cold.androidanimations.match;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.cold.androidanimations.R;

public class MatchView extends FrameLayout {

    private Context mContext;
    private HeadView imgvHead1;
    private HeadView imgvHead2;
    private HeadView imgvHead3;
    private HeadView imgvHead4;
    private HeadView imgvHead5;
    private HeadView imgvHead6;
    private HeadView imgvHead7;
    private HeadView imgvHead8;
    private HeadView imgvHead9;
    private HeadView imgvHead10;
    private HeadView imgvHead11;
    private HeadView imgvHead12;

    public MatchView(Context context) {
        super(context);
        initView(context, null);
    }

    public MatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = context;
        inflate(context, R.layout.view_custom_match, this);
        imgvHead1 = findViewById(R.id.imgv_head1);
        imgvHead2 = findViewById(R.id.imgv_head2);
        imgvHead3 = findViewById(R.id.imgv_head3);
        imgvHead4 = findViewById(R.id.imgv_head4);
        imgvHead5 = findViewById(R.id.imgv_head5);
        imgvHead6 = findViewById(R.id.imgv_head6);
        imgvHead7 = findViewById(R.id.imgv_head7);
        imgvHead8 = findViewById(R.id.imgv_head8);
        imgvHead9 = findViewById(R.id.imgv_head9);
        imgvHead10 = findViewById(R.id.imgv_head10);
        imgvHead11 = findViewById(R.id.imgv_head11);
        imgvHead12 = findViewById(R.id.imgv_head12);

        imgvHead1.startAnim();
        imgvHead2.startAnim();
        imgvHead3.startAnim();
        imgvHead4.startAnim();
        imgvHead5.startAnim();
        imgvHead6.startAnim();
        imgvHead7.startAnim();
        imgvHead8.startAnim();
        imgvHead9.startAnim();
        imgvHead10.startAnim();
        imgvHead11.startAnim();
        imgvHead12.startAnim();
    }


}
