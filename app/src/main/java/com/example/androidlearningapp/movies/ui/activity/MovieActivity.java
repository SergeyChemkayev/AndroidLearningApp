package com.example.androidlearningapp.movies.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.androidlearningapp.R;
import com.example.androidlearningapp.movies.entity.Movie;

public class MovieActivity extends AppCompatActivity {
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
        movie = (Movie) getIntent().getSerializableExtra("movie");
        Toast.makeText(this, movie.getName(), Toast.LENGTH_SHORT).show();
    }
}
