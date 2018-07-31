package com.example.androidlearningapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        List<String> list = fillItemsList();
        DataAdapter adapter = new DataAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private List<String> fillItemsList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add("Item #" + (i + 1));
        }
        return list;
    }
}
