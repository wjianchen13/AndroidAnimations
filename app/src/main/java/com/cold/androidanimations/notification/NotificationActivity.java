package com.cold.androidanimations.notification;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

public class NotificationActivity extends AppCompatActivity {

    private TestView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        tv = findViewById(R.id.tv);
    }

    /**
     * 模拟通知从上弹出
     * @param
     * @return
     */
    public void onTest1(View v) {
        tv.addNotification("test1");
    }

    public void onTest2(View v) {
        tv.addNotification("test2");
    }

    public void onTest3(View v) {
        tv.addNotification("test3");
    }

    public void onTest4(View v) {
        tv.addNotification("test4");
    }


}
