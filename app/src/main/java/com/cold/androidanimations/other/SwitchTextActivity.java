package com.cold.androidanimations.other;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class SwitchTextActivity extends AppCompatActivity {

    private TextSwitchView tsvTest;
    private ImageView imgvTest;
    private int i = 1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_text);
        tsvTest = findViewById(R.id.tsv_test);
        imgvTest = findViewById(R.id.imgv_test);
        tsvTest.setText(i + "/" + i);
        i ++;
    }

    /**
     * test
     * @param v
     */
    public void onTest(View v) {
        String [] res={
                "床前明月光",
                "疑是地上霜",
                "举头望明月",
                "低头思故乡"
        };
        tsvTest.setResources(res);
//        tsvTest.setTextStillTime(5000);
    }

    /**
     * 设置显示文字
     * @param v
     */
    public void onSet(View v) {
        tsvTest.setText(i + "/" + i);
        i ++;
    }

    /**
     * 缩放
     * @param v
     */
    public void onScale(View v) {
//        imgvTest.setAnimation(getGameAnimSet());
        imgvTest.startAnimation(getGameAnimSet());
    }

    public AnimationSet getGameAnimSet() {
        AnimationSet animSet = new AnimationSet(true);
        AlphaAnimation alphaAnim = new AlphaAnimation(0.8f, 1.0f);
        alphaAnim.setDuration(500);
        alphaAnim.setRepeatMode(Animation.REVERSE);
        alphaAnim.setRepeatCount(Animation.INFINITE);

        ScaleAnimation scaleAnim = new ScaleAnimation(0.92f, 1.0f, 0.92f, 1.0f, Animation.RELATIVE_TO_SELF, 0.92f, Animation.RELATIVE_TO_SELF, 0.92f);
        scaleAnim.setDuration(500);
        scaleAnim.setRepeatMode(Animation.REVERSE);
        scaleAnim.setRepeatCount(Animation.INFINITE);

        animSet.addAnimation(alphaAnim);
        animSet.addAnimation(scaleAnim);

        return animSet;
    }
    
}
