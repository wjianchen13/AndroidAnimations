package com.cold.androidanimations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class ShowActivity extends AppCompatActivity {

    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        btnTest = (Button)findViewById(R.id.btn_test);

    }

    /**
     * 显示
     * @param
     * @return
     */
    public void onShow(View v) {
//        btnTest.setVisibility(View.VISIBLE);
////        // 向右边移出
////        ll_first.setAnimation(AnimationUtils.makeOutAnimation(this, true));
//        // 向右边移入
//        btnTest.setAnimation(AnimationUtils.makeInAnimation(this, false));
        show();
    }



    /**
     * 隐藏
     * @param
     * @return
     */
    public void onHide(View v) {
//        btnTest.setVisibility(View.GONE);
//        // 向右边移出
//        btnTest.setAnimation(AnimationUtils.makeOutAnimation(this, true));
        hide();
    }

    private void show() {
        if(btnTest != null) {
            TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            mShowAction.setDuration(300);
            btnTest.startAnimation(mShowAction);
            btnTest.setVisibility(View.VISIBLE);
        }
    }

    private void hide() {
        if(btnTest.getVisibility() == View.VISIBLE) {
            TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    0.0f);
            mHiddenAction.setDuration(300);

            mHiddenAction.setAnimationListener(new Animation.AnimationListener() {
                                                         @Override
                                                         public void onAnimationStart(Animation animation) {
                                                             //动画开始
                                                         }

                                                         @Override
                                                         public void onAnimationEnd(Animation animation) {
                                                             //动画结束
                                                             btnTest.setVisibility(View.GONE);
                                                         }

                                                         @Override
                                                         public void onAnimationRepeat(Animation animation) {
                                                             //动画重复
                                                         }

                                                     });

            btnTest.startAnimation(mHiddenAction);

        }
    }
}
