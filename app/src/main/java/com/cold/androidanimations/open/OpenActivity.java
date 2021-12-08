package com.cold.androidanimations.open;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.androidanimations.R;

public class OpenActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private Rotate3dView rotate3dView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        rotate3dView = findViewById(R.id.rotate_view);

        seekBar = findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rotate3dView.setDegrees(180 - progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
                                               
    }
   
    
}
