package com.example.androidlearningapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> moviesList;

    public void setMoviesList(List<Movie> moviesList) {
        MoviesDiffUtilCallback moviesDiffUtilCallback = new MoviesDiffUtilCallback(this.moviesList, moviesList);
        DiffUtil.DiffResult itemsDiffResult = DiffUtil.calculateDiff(moviesDiffUtilCallback);
        this.moviesList = moviesList;
        itemsDiffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieItemView = inflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(movieItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(moviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesList == null ? 0 : moviesList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;
        private TextView nameEngView;
        private TextView descriptionView;
        private TextView premiereDateView;
        private ImageView movieCoverView;

        public MovieViewHolder(View v) {
            super(v);
            nameView = (TextView) itemView.findViewById(R.id.movie_name_text_view);
            nameEngView = (TextView) itemView.findViewById(R.id.movie_name_eng_text_view);
            descriptionView = (TextView) itemView.findViewById(R.id.description_text_view);
            premiereDateView = (TextView) itemView.findViewById(R.id.premiere_date_text_view);
            movieCoverView = (ImageView) itemView.findViewById(R.id.movie_cover_image_view);
        }

        public void bind(Movie movie) {
            nameView.setText(movie.getName());
            nameEngView.setText(movie.getNameEng());
            descriptionView.setText(movie.getDescription());
            premiereDateView.setText(movie.getPremiere());
            Glide.with(itemView.getContext())
                    .load(movie.getImage())
                    .apply(new RequestOptions().centerCrop())
                    .into(movieCoverView);
        }
    }
}
