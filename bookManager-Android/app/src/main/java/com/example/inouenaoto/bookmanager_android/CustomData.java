package com.example.inouenaoto.bookmanager_android;

import android.graphics.Bitmap;

/**
 * Created by inouenaoto on 2016/07/27.
 */
public class CustomData {
    private Bitmap mIcon;
    private String mId;
    private String mTitle;
    private String mPrice;
    private String mDate;

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
    public Bitmap getIcon() {
        return mIcon;
    }

    public void setIcon(Bitmap icon) {
        mIcon = icon;
    }

    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }

    public String getId() {
        return mId;
    }
    public void setId(String id) {
        mId = id;
    }
}