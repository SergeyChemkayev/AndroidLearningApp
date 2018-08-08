package com.example.androidlearningapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.androidlearningapp.movieitems.MovieListElement;

public abstract class AbstractViewHolder extends RecyclerView.ViewHolder {
    AbstractViewHolder(View v){
        super(v);
    }

    abstract void bind(MovieListElement movieListElement);
}
