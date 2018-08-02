package com.example.androidlearningapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.androidlearningapp.listeners.GetMoviesListener;
import com.example.androidlearningapp.services.MoviesRemoteSource;
import com.example.androidlearningapp.services.impl.MoviesNetwork;

import java.util.List;

public class DataActivity extends AppCompatActivity implements GetMoviesListener {
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private MoviesRemoteSource moviesRemoteSource = MoviesNetwork.getInstance();


    @Override
    protected void onStart() {
        super.onStart();
        moviesRemoteSource.setGetMoviesListener(this);
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, DataActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        adapter = new MoviesAdapter();
        recyclerView.setAdapter(adapter);
        moviesRemoteSource.getMovies();
    }

    @Override
    public void onGetMoviesSuccess(List<Movie> movies) {
        adapter.setMoviesList(movies);
    }

    @Override
    public void onGetMoviesError(Throwable error) {
        Toast.makeText(DataActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        moviesRemoteSource.setGetMoviesListener(null);
    }
}
