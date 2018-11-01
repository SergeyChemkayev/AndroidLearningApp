package com.example.androidlearningapp.movies.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.androidlearningapp.movies.entity.MovieElement;

public abstract class MovieElementViewHolder extends RecyclerView.ViewHolder {
    public MovieElementViewHolder(View v) {
        super(v);
    }

    public abstract void bind(MovieElement movieElement);
}
