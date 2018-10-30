package com.example.androidlearningapp.movies.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

    private Movie movie;

    public static void open(Activity activity, Movie movie, int requestCode) {
        Intent intent = new Intent(activity, MovieActivity.class);
        intent.putExtra("movie", movie);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        movieCoverView = findViewById(R.id.movie_cover_toolbar_image_view);
        movieDescriptionView = findViewById(R.id.movie_description_text_view);
        movieNameToolbar = findViewById(R.id.movie_name_toolbar);
        movie = getIntent().getParcelableExtra("movie");
        initToolbar();
        initMovie();
    }

    private void initToolbar() {
        movieNameToolbar.setTitle(movie.getName());
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
        Glide.with(this)
                .load(movie.getImage())
                .apply(new RequestOptions().centerCrop())
                .into(movieCoverView);
        movieDescriptionView.setText(movie.getDescription());
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("movie_name", movie.getName());
        setResult(RESULT_OK, intent);
        finish();
    }
}
