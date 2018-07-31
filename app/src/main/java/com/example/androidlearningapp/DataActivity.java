package com.example.androidlearningapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;


    public static void open(Context context) {
        Intent intent = new Intent(context, DataActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        List<String> list = fillItemsList();
        DataAdapter adapter = new DataAdapter();
        recyclerView.setAdapter(adapter);
        ItemsDiffUtilCallback itemsDiffUtilCallback = new ItemsDiffUtilCallback(adapter.getItemsList(), list);
        DiffUtil.DiffResult itemsDiffResult = DiffUtil.calculateDiff(itemsDiffUtilCallback);
        adapter.setItemsList(list);
        itemsDiffResult.dispatchUpdatesTo(adapter);
    }

    private List<String> fillItemsList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add("Item #" + (i + 1));
        }
        return list;
    }
}
