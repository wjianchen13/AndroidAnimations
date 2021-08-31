package com.cold.androidanimations.rotate;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.cold.androidanimations.R;

import java.util.ArrayList;
import java.util.List;

public class TestRotateViewActivity extends AppCompatActivity{

    private RotateView rtvTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rotate_view);
        rtvTest = (RotateView)findViewById(R.id.rtv_test);
    }


    public void onTest(View v) {
//        rtvTest.flipCard();
    }

    public void onStop(View v) {
        rtvTest.stop();
    }


}
