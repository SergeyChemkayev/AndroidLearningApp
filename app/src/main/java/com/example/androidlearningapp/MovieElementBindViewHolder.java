package com.example.androidlearningapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.androidlearningapp.movieitems.MovieElement;

public abstract class MovieElementBindViewHolder extends RecyclerView.ViewHolder {
    MovieElementBindViewHolder(View v){
        super(v);
    }

    abstract void bind(MovieElement movieElement);
}
