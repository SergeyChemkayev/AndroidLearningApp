package com.example.androidlearningapp;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class MoviesDiffUtilCallback extends DiffUtil.Callback {
    private final List<Movie> oldList;
    private final List<Movie> newList;

    public MoviesDiffUtilCallback(List<Movie> oldList, List<Movie> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition) != null && oldList.get(oldItemPosition) != null && oldList.get(oldItemPosition).getName().equals(newList.get(newItemPosition).getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition) != null && oldList.get(oldItemPosition) != null && oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
