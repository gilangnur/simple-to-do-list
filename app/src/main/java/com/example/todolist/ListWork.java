package com.example.todolist;

public class ListWork {
    private int mImageResource;
    private String mText1;
    //private String mText2;

    public ListWork(int imageResource, String text1) {
        mImageResource = imageResource;
        mText1 = text1;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getJudul() {
        return mText1;
    }
}
