package com.example.androidlearningapp.movies.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidlearningapp.R;
import com.example.androidlearningapp.movies.entity.Movie;

public class MovieActivity extends AppCompatActivity {
    private ImageView movieCoverView;
    private TextView movieDescriptionView;
    private Toolbar movieNameToolbar;

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

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, getIntent());
        finish();
    }
}
