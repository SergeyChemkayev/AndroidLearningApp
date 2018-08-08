package com.example.androidlearningapp.movieitems;

public class Progress implements MovieListElement {
    private static final String TYPE = "PROGRESS";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getName() { //method must be in interface, but there is nothing to return
        return null;
    }
}
