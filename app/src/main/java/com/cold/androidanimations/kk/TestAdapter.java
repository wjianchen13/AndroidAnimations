package com.cold.androidanimations.kk;

import android.view.View;

public interface TestAdapter {
    
    int getCount();
    Object getItem(int position);
    long getItemId(int position);
    View getView(int position);
    View insertItem(PanItem item);
    void deleteItem(int index);
}
