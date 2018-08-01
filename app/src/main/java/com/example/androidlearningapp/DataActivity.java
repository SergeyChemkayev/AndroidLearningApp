package com.example.androidlearningapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    public static void open(Context context) {
        Intent intent = new Intent(context,DataActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        App.getMoviesApi().movies().enqueue(new Callback<ResultList>() {
            @Override
            public void onResponse(@NonNull Call<ResultList> call, @NonNull Response<ResultList> response) {
                if (response.body() != null) {
                    DataAdapter adapter = new DataAdapter();
                    recyclerView.setAdapter(adapter);
                    adapter.setMoviesList(Objects.requireNonNull(response.body()).getList());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResultList> call, @NonNull Throwable t) {
                Toast.makeText(DataActivity.this, "Something goes wrong :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
