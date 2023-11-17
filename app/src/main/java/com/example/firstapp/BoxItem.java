package com.example.firstapp;

public class BoxItem {
    private int imageResId;
    private String text;

    public BoxItem(int imageResId, String text) {
        this.imageResId = imageResId;
        this.text = text;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getText() {
        return text;
    }
}
