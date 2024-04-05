package com.example.courseendproject.Domain;

import android.view.View;

public class TrendSDomain {

    private String title;
    private String subtitle;
    private String picAddress;

    private View.OnClickListener clickListener;
    public String getTitle() {
        return title;
    }

    public TrendSDomain(String title, String subtitle, String picAddress, View.OnClickListener onClickListener) {
        this.title = title;
        this.subtitle = subtitle;
        this.picAddress = picAddress;
        this.clickListener=onClickListener;
    }

    public void setTitle(String title) {
        this.title=title;

    }

    public View.OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle=subtitle;

    }
    public String getPicAddress() {
        return picAddress;
    }
    public void setPicAddress(String picAddress) {
        this.picAddress=picAddress;

    }


}
