package com.cold.androidanimations.svga;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

public class AssetsOtherActivity extends AppCompatActivity {

    private SVGAImageView svgaMatchMatch;
    private SVGAImageView sImgvTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_other);
        svgaMatchMatch = findViewById(R.id.svga_match_match);
        svgaMatchMatch.setCallback(new SVGACallback() {
            @Override
            public void onFinished() {
                System.out.println("==========================> onFinished: ");
            }

            @Override
            public void onPause() {

            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int i, double v) {
                System.out.println("==========================> i: " + i + "  v: " + v);
            }
        });
//        svgaMatchMatch.post(new Runnable() {
//            @Override
//            public void run() {
//                svgaMatchMatch.stepToPercentage(0f, false);
//            }
//        });
        loadAnim(svgaMatchMatch, "bg_match_match.svga");
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
                v.stepToFrame(0, false);
            }

            @Override
            public void onError() {
                Log.e("zzzz", "onComplete: ");
            }

        }, null);
    }

    /**
     *
     * @param
     * @return
     */
    public void onTest1(View v) {
        svgaMatchMatch.startAnimation();
//        svgaMatchMatch.res
    }

    /**
     *
     * @param
     * @return
     */
    public void onTest2(View v) {

    }

    /**
     *
     * @param
     * @return
     */
    public void onTest3(View v) {

    }

    /**
     *
     * @param
     * @return
     */
    public void onTest4(View v) {

    }
    
}
