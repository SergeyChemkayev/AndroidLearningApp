package com.example.androidlearningapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ItemViewHolder> {
    private List<String> itemsList;

    public void setItemsList(List<String> itemsList) {
        ItemsDiffUtilCallback itemsDiffUtilCallback = new ItemsDiffUtilCallback(this.itemsList, itemsList);
        DiffUtil.DiffResult itemsDiffResult = DiffUtil.calculateDiff(itemsDiffUtilCallback);
        this.itemsList = itemsList;
        itemsDiffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(itemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsList == null ? 0 : itemsList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ItemViewHolder(View v) {
            super(v);
            textView = (TextView) itemView.findViewById(R.id.item_name);
        }

        public void bind(String item) {
            textView.setText(item);
        }
    }
}
