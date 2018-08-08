package com.example.androidlearningapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.androidlearningapp.movieitems.MovieElement;

public abstract class BindViewHolder extends RecyclerView.ViewHolder {
    BindViewHolder(View v){
        super(v);
    }

    abstract void bind(MovieElement movieElement);
}
