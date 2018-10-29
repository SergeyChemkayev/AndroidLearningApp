package com.example.androidlearningapp.movies.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidlearningapp.R;
import com.example.androidlearningapp.movies.entity.Movie;

import java.util.Objects;

public class MovieActivity extends AppCompatActivity {
    private ImageView movieCoverView;
    private TextView movieDescriptionView;
    private Toolbar movieNameToolbar;

    private Movie movie;


    public static void open(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtra("movie", movie);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        movieCoverView = findViewById(R.id.movie_cover_toolbar_image_view);
        movieDescriptionView = findViewById(R.id.movie_description_text_view);
        movieNameToolbar = findViewById(R.id.movie_name_toolbar);
        movie = getIntent().getParcelableExtra("movie");
        movieNameToolbar.setTitle(movie.getName());
        setSupportActionBar(movieNameToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Glide.with(this)
                .load(movie.getImage())
                .apply(new RequestOptions().centerCrop())
                .into(movieCoverView);
        movieDescriptionView.setText(movie.getDescription());
    }
}
