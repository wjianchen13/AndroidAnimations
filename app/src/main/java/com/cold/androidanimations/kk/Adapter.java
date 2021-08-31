package com.cold.androidanimations.kk;

import android.view.View;

public interface Adapter {
    
    int getCount();
    Object getItem(int position);
    long getItemId(int position);
    View getView(int position);
    
}
