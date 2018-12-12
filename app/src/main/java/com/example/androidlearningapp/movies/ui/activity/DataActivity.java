package com.example.androidlearningapp.movies.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.androidlearningapp.R;
import com.example.androidlearningapp.movies.data.extensions.ToastKt;
import com.example.androidlearningapp.movies.data.listeners.OnMovieClickListener;
import com.example.androidlearningapp.movies.data.viewModels.DataViewModel;
import com.example.androidlearningapp.movies.data.viewModels.DataViewModelInterface;
import com.example.androidlearningapp.movies.entity.Movie;
import com.example.androidlearningapp.movies.entity.MovieElement;
import com.example.androidlearningapp.movies.ui.adapter.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import kotlin.Unit;

public class DataActivity extends AppCompatActivity implements OnMovieClickListener {
    public static final int REQUEST_CODE_MOVIE = 1;
    private RecyclerView recyclerView;
    private View emptyView;
    private MoviesAdapter adapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isAbleToLoadMovies = true;
    private DataViewModelInterface dataViewModel;

    public static void open(Context context) {
        Intent intent = new Intent(context, DataActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        emptyView = findViewById(R.id.data_empty_view);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        adapter = new MoviesAdapter();
        adapter.setOnMovieClickListener(this);
        initRx();
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    @Override
    protected void onStart() {
        super.onStart();
        compositeDisposable.add(dataViewModel.getMoviesSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetMoviesSuccess));
        dataViewModel.getMovies(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataViewModel.disposeMovies();
        compositeDisposable.dispose();
    }

    @Override
    public void onMovieClick(Movie movie, View coverView, View descriptionView) {
        openMovieActivity(movie, coverView, descriptionView);
    }

    public void openMovieActivity(Movie movie, View coverView, View descriptionView) {
        Pair<View, String> movieCoverPair = Pair.create(coverView, getText(R.string.movie_cover_transition_name).toString());
        Pair<View, String> movieDescriptionPair = Pair.create(descriptionView, getText(R.string.movie_description_transition_name).toString());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, movieCoverPair, movieDescriptionPair);
        MovieActivity.open(this, movie, REQUEST_CODE_MOVIE, options);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_MOVIE) {
            if (data != null) {
                Movie movie = data.getParcelableExtra("movie");
                Toast.makeText(this, movie.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.data_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.data_menu_fragments:
                FragmentActivity.open(this);
                return true;
            case R.id.data_menu_settings:
                PreferencesActivity.Companion.open(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.data_movies_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && isAbleToAddMovies()) {
                    dataViewModel.getMovies(false);
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
        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (this.isAbleToLoadMovies) {
                dataViewModel.getMovies(true);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }


    public void onGetMoviesSuccess(List<Movie> movies) {
        List<MovieElement> list = new ArrayList<>();
        list.addAll(movies);
        adapter.setMovies(list);
    }

    private void initRx() {
        compositeDisposable.add(dataViewModel.isAbleToLoadMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setIsAbleToLoadMoviesValue));
        compositeDisposable.add(dataViewModel.isRefreshing()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setRefreshing));
        compositeDisposable.add(dataViewModel.isLoadingMore()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setLoading));
        compositeDisposable.add(dataViewModel.getRecyclerViewVisibility()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setRecyclerViewVisibility));
        compositeDisposable.add(dataViewModel.getEmptyViewVisibility()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setEmptyViewVisibility));
        compositeDisposable.add(dataViewModel.isError()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showError));
    }

    private void setIsAbleToLoadMoviesValue(boolean value) {
        isAbleToLoadMovies = value;
    }

    private void setRefreshing(boolean value) {
        swipeRefreshLayout.setRefreshing(value);
    }

    private void setLoading(boolean value) {
        if (value) {
            adapter.showLoading();
        } else {
            adapter.dismissLoading();
        }
    }

    private void setRecyclerViewVisibility(boolean value) {
        recyclerView.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    private void setEmptyViewVisibility(boolean value) {
        emptyView.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    private void showError(Unit u) {
        ToastKt.toast(this, getText(R.string.error_message).toString());
    }
}
