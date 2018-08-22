package com.example.androidlearningapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.androidlearningapp.listeners.GetMoviesListener;
import com.example.androidlearningapp.movieitems.Movie;
import com.example.androidlearningapp.movieitems.MovieElement;
import com.example.androidlearningapp.services.MoviesRemoteSource;
import com.example.androidlearningapp.services.impl.MoviesNetwork;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity implements GetMoviesListener {
    public static final int MOVIES_PER_PAGE = 7;
    private RecyclerView recyclerView;
    private View emptyView;
    private MoviesAdapter adapter;
    private MoviesRemoteSource moviesRemoteSource = MoviesNetwork.getInstance();
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isAbleToLoadMovies = true;
    private int pageNumber = 1;

    public static void open(Context context) {
        Intent intent = new Intent(context, DataActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        emptyView = findViewById(R.id.data_empty_view);
        adapter = new MoviesAdapter();
        initRecyclerView();
        initSwipeRefreshLayout();
        getMovies(pageNumber);
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

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.data_movies_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && isAbleToAddMovies()) {
                    getMovies(pageNumber + 1);
                }
            }
        });
    }

    private boolean isAbleToAddMovies() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        return linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == (adapter.getItemCount() - 2) && isAbleToLoadMovies;
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.data_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isAbleToLoadMovies) {
                    getMovies(1);
                }
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void getMovies(int pageNumber) {
        this.pageNumber = pageNumber;
        isAbleToLoadMovies = false;
        if (pageNumber == 1) {
            swipeRefreshLayout.setRefreshing(true);
        } else {
            adapter.showLoading();
        }
        moviesRemoteSource.getMovies();
    }

    @Override
    public void onGetMoviesSuccess(List<Movie> movies) {
        hideLoading();
        isAbleToLoadMovies = movies != null && movies.size() == MOVIES_PER_PAGE;
        addMovies(movies);
        updateViewsVisibility();
    }

    @Override
    public void onGetMoviesError(Throwable error) {
        hideLoading();
        isAbleToLoadMovies = true;
        Toast.makeText(DataActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    private void hideLoading() {
        if (pageNumber == 1) {
            swipeRefreshLayout.setRefreshing(false);
        } else {
            adapter.dismissLoading();
        }
    }

    private void addMovies(List<Movie> movies) {
        List<MovieElement> list = new ArrayList<>();
        list.addAll(movies);
        if (pageNumber == 1) {
            adapter.setMovies(list);
        } else {
            adapter.addMovies(list);
        }
    }

    private void updateViewsVisibility() {
        if (adapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }
}
