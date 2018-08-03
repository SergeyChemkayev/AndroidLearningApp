package com.example.androidlearningapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultList {
    private List<Movie> list;

    public List<Movie> getList() {
        return list;
    }

    public void setList(List<Movie> list) {
        this.list = list;
    }
}
