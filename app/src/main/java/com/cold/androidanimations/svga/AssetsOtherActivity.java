package com.cold.androidanimations.svga;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public class AssetsOtherActivity extends AppCompatActivity {

    private SVGAImageView svgaMatchMatch;
    private SVGAImageView svgaEntry;
    private SVGAParser svgaParser;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_other);

        SVGAParser.Companion.shareParser().init(this);
        svgaParser = SVGAParser.Companion.shareParser();

        svgaMatchMatch = findViewById(R.id.svga_match_match);
        svgaMatchMatch.setCallback(new SVGACallback() {
            @Override
            public void onFinished() {
//                System.out.println("==========================> onFinished: ");
            }

            @Override
            public void onPause() {

            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int i, double v) {
//                System.out.println("==========================> i: " + i + "  v: " + v);
            }
        });

        svgaEntry = findViewById(R.id.svga_entry);
        svgaEntry.setCallback(new SVGACallback() {
            @Override
            public void onFinished() {
//                System.out.println("==========================> onFinished: ");
            }

            @Override
            public void onPause() {

            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int i, double v) {
                count ++;
                System.out.println("==========================> i: " + i + "  v: " + v + " count: " + count);
            }
        });


    }

    /**
     * 启动动画
     * @param
     * @return
     */
    public void onTest1(View v) {
        loadAnim(svgaMatchMatch, "bg_match_match.svga");
    }

    private void loadAnim(final SVGAImageView v, String name) {
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
    public void onTest2(View v) {
        svgaEntry.startAnimation();
    }

    /**
     *
     * @param
     * @return
     */
    public void onTest3(View v) {
        svgaEntry.stopAnimation();
    }

    /**
     *
     * @param
     * @return
     */
    public void onTest4(View v) {
        try {
            String path = getFilesDir().getAbsolutePath();
            String name = path + File.separator + "vip_entry_1.svga";
            SVGAParser.Companion.shareParser().init(this);
            SVGAParser svgaParser = SVGAParser.Companion.shareParser();

            File animFile = new File(name);
            FileInputStream fileInputStream = new FileInputStream(animFile);
            if (svgaParser != null) {
                svgaParser.decodeFromInputStream(fileInputStream, name, new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(SVGAVideoEntity svgaVideoEntity) {
                        SVGADynamicEntity svgaDynamicEntity = new SVGADynamicEntity();
                        try {
                            svgaEntry.setVideoItem(svgaVideoEntity);
                            svgaEntry.stepToFrame(0, true);

                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }

                    @Override
                    public void onError() {

                    }
                }, true, null, name);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void test() {
        try {
            SVGAParser.Companion.shareParser().init(this);
            SVGAParser parser = SVGAParser.Companion.shareParser();
            parser.parse(new URL("https://github.com/yyued/SVGA-Samples/blob/master/kingset.svga?raw=true"),
                    new SVGAParser.ParseCompletion() {
                        @Override
                        public void onComplete(SVGAVideoEntity videoItem) {
                            SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
                            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("Pony sends many flowers.");
                            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.YELLOW), 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                            TextPaint textPaint = new TextPaint();
                            textPaint.setColor(Color.WHITE);
                            textPaint.setTextSize(28);
                            dynamicEntity.setDynamicText(new StaticLayout(
                                    spannableStringBuilder,
                                    0,
                                    spannableStringBuilder.length(),
                                    textPaint,
                                    0,
                                    Layout.Alignment.ALIGN_CENTER,
                                    1.0f,
                                    0.0f,
                                    false
                            ), "banner");

                            SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);
                            svgaMatchMatch.setImageDrawable(drawable);
                            svgaMatchMatch.startAnimation();
                        }
                        @Override
                        public void onError() {

                        }
                    }
            );
        } catch (Exception e) {
            System.out.print(true);
        }
    }
    
}
