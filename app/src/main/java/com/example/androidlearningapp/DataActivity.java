package com.example.androidlearningapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
    private SwipeRefreshLayout swipeRefreshLayout;

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
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.data_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovies();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);
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
        if (movies == null || movies.isEmpty()) {
            setViewsVisibility(true);
        } else {
            setViewsVisibility(false);
            adapter.setMoviesList(movies);
        }
    }

    @Override
    public void onGetMoviesError(Throwable error) {
        setViewsVisibility(true);
        Toast.makeText(DataActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    private void setViewsVisibility(boolean isMoviesEmpty) {
        swipeRefreshLayout.setRefreshing(false);
        if (isMoviesEmpty) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    private void getMovies() {
        swipeRefreshLayout.setRefreshing(true);
        moviesRemoteSource.getMovies();
    }
}
