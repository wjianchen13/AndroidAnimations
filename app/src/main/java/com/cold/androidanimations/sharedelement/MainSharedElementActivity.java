package com.cold.androidanimations.sharedelement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class MainSharedElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shared_element);

    }
    
    public void onDemo(View v) {
        startActivity(new Intent(this, SharedElementActivity.class));
    }

    public void onList(View v) {
        startActivity(new Intent(this, ListSharedElementActivity.class));
    }
    
}
