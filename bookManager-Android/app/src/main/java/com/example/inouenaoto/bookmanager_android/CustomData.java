package com.example.inouenaoto.bookmanager_android;

import android.graphics.Bitmap;

/**
 * Created by inouenaoto on 2016/07/27.
 */
public class CustomData {
    private Bitmap micon;
    private String mtitle;
    private String mprice;
    private String mdate;


    public String getPrice() {
        return mprice;
    }

    public void setPrice(String price) {
        mprice = price;
    }

    public String getDate() {
        return mdate;
    }

    public void setDate(String date) {
        mdate = date;
    }
    public Bitmap getIcon() {
        return micon;
    }

    public void setIcon(Bitmap icon) {
        micon = icon;
    }

    public String getTitle() {
        return mtitle;
    }
    public void setTitle(String title) {
        mtitle = title;
    }

}