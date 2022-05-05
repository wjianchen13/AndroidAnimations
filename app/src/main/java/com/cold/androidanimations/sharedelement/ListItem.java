package com.cold.androidanimations.sharedelement;

import java.io.Serializable;
import java.util.ArrayList;

public class ListItem implements Serializable {
    
    private String t;
    private String url1;
    private String url2;
    private String url3;
    
    private ArrayList<String> mList;
    
    public ListItem(String t, String url1, String url2, String url3) {
        this.t = t;
        this.url1 = url1;
        this.url2 = url2;
        this.url3 = url3;
        mList = new ArrayList<>();
        mList.add(this.url1);
        mList.add(this.url2);
        mList.add(this.url3);
    }

    public String getT() {
        return t;
    }

    public String getUrl1() {
        return url1;
    }

    public String getUrl2() {
        return url2;
    }

    public String getUrl3() {
        return url3;
    }

    public ArrayList<String> getList() {
        return mList;
    }
}
