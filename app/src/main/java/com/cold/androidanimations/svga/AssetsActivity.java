package com.cold.androidanimations.svga;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

public class AssetsActivity extends AppCompatActivity {

    private SVGAImageView sImgvTest;
    private SVGAImageView sImgvTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);
        sImgvTest = findViewById(R.id.simgv_xml);
        sImgvTest1 = findViewById(R.id.simgv_xml2);
    }

    /**
     * 从assets目录播放动画
     * @param
     * @return
     */
    public void onAssets1(View v) {
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }

    /**
     * 从assets目录播放动画
     * @param
     * @return
     */
    public void onAssets(View v) {
        loadAnim(sImgvTest, "main_send_gift.svga");
    }

    private void loadAnim(final SVGAImageView v, String name) {
        SVGAParser.Companion.shareParser().init(this);
        SVGAParser svgaParser = SVGAParser.Companion.shareParser();
//        String name = this.randomSample();
        //asset jojo_audio.svga  cannot callback
        Log.d("SVGA", "## name " + name);
        svgaParser.setFrameSize(100, 100);
        svgaParser.decodeFromAssets(name, new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NonNull SVGAVideoEntity videoItem) {
                Log.e("zzzz", "onComplete: ");
                v.setVideoItem(videoItem);
                v.stepToFrame(0, true);
            }

            @Override
            public void onError() {
                Log.e("zzzz", "onComplete: ");
            }

        }, null);
    }

    /**
     * 补间动画
     * @param
     * @return
     */
    public void onOther(View v) {
        loadAnim(sImgvTest1, "light.svga");
    }

    /**
     * 停止动画测试
     * @param
     * @return
     */
    public void onStop(View v) {
//        sImgvTest.stopAnimation();
        sImgvTest.setVisibility(View.GONE);
    }

    /**
     * 开始动画测试
     * @param
     * @return
     */
    public void onStart(View v) {
//        sImgvTest.startAnimation();
        sImgvTest.setVisibility(View.VISIBLE);
    }

}
