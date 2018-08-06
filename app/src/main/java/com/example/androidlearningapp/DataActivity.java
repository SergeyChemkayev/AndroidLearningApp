package com.example.androidlearningapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androidlearningapp.listeners.GetMoviesListener;
import com.example.androidlearningapp.services.MoviesRemoteSource;
import com.example.androidlearningapp.services.impl.MoviesNetwork;

import java.util.List;

public class DataActivity extends AppCompatActivity implements GetMoviesListener {
    private RecyclerView recyclerView;
    private View emptyView;
    private MoviesAdapter adapter;
    private MoviesRemoteSource moviesRemoteSource = MoviesNetwork.getInstance();
    private ProgressBar progressBar;

    public static void open(Context context) {
        Intent intent = new Intent(context, DataActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        recyclerView = (RecyclerView) findViewById(R.id.data_movies_recycler_view);
        emptyView = (View) findViewById(R.id.data_empty_view);
        progressBar = (ProgressBar) findViewById(R.id.data_loading_progress_bar);
        adapter = new MoviesAdapter();
        recyclerView.setAdapter(adapter);
        getMovies();
    }

    @Override
    protected void onStart() {
        super.onStart();
        moviesRemoteSource.setGetMoviesListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        moviesRemoteSource.setGetMoviesListener(null);
    }

    @Override
    public void onGetMoviesSuccess(List<Movie> movies) {
        progressBar.setVisibility(View.GONE);
        if (movies == null || movies.isEmpty()) {
            setViewsVisibility(true);
        } else {
            setViewsVisibility(false);
            adapter.setMoviesList(movies);
        }
    }

    @Override
    public void onGetMoviesError(Throwable error) {
        progressBar.setVisibility(View.GONE);
        setViewsVisibility(true);
        Toast.makeText(DataActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    private void setViewsVisibility(boolean isMoviesEmpty) {
        if (isMoviesEmpty) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    private void getMovies() {
        progressBar.setVisibility(View.VISIBLE);
        moviesRemoteSource.getMovies();
    }
}
