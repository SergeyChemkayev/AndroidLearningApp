package com.example.androidlearningapp.movies.ui.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidlearningapp.R;
import com.example.androidlearningapp.movies.entity.Movie;
import com.example.androidlearningapp.movies.services.MovieNameDisplayService;

public class MovieActivity extends AppCompatActivity {
    private ImageView movieCoverView;
    private TextView movieDescriptionView;
    private Toolbar movieNameToolbar;
    private ServiceConnection serviceConnection;

    private boolean bound = false;

    public static void open(Activity activity, Movie movie, int requestCode, ActivityOptionsCompat options) {
        Intent intent = new Intent(activity, MovieActivity.class);
        intent.putExtra("movie", movie);
        activity.startActivityForResult(intent, requestCode, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        initMovie();
        initToolbar();
        bindMovieNameDisplayService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!bound) {
            return;
        }
        unbindService(serviceConnection);
        bound = false;
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, getIntent());
        finish();
    }

    private void initToolbar() {
        setSupportActionBar(movieNameToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        movieNameToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initMovie() {
        movieCoverView = findViewById(R.id.movie_cover_toolbar_image_view);
        movieDescriptionView = findViewById(R.id.movie_description_text_view);
        movieNameToolbar = findViewById(R.id.movie_name_toolbar);
        Movie movie = getIntent().getParcelableExtra("movie");
        Glide.with(this)
                .load(movie.getImage())
                .apply(new RequestOptions().centerCrop())
                .into(movieCoverView);
        movieDescriptionView.setText(movie.getDescription());
        movieNameToolbar.setTitle(movie.getName());
    }

    private void bindMovieNameDisplayService() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d("Service", "Service connected");
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d("Service", "Service disconnected");
                bound = false;
            }
        };
        Intent intent = new Intent(this, MovieNameDisplayService.class);
        intent.putExtra("movie", getIntent().getParcelableExtra("movie"));
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
