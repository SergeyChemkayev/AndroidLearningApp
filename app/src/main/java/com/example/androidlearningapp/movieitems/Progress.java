package com.example.androidlearningapp.movieitems;

public class Progress implements MovieElement {
    public static final int TYPE = 0;

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public String getName() { //method must be in interface, but there is nothing to return
        return null;
    }
}
