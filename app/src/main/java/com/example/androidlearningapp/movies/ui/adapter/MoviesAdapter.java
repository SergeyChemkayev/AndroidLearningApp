package com.example.androidlearningapp.movies.ui.adapter;

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
import com.example.androidlearningapp.R;
import com.example.androidlearningapp.movies.data.listeners.OnMovieClickListener;
import com.example.androidlearningapp.movies.entity.Movie;
import com.example.androidlearningapp.movies.entity.MovieElement;
import com.example.androidlearningapp.movies.entity.MovieProgress;
import com.example.androidlearningapp.movies.ui.adapter.viewholder.MovieElementViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieElementViewHolder> {
    private List<MovieElement> movies;
    private OnMovieClickListener onMovieClickListener;

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return movies.get(position).getType();
    }

    public void setMovies(List<MovieElement> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        dispatchUpdates(list);
    }

    public void addMovies(List<MovieElement> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        List<MovieElement> tmp = new ArrayList<>(movies);
        tmp.addAll(list);
        dispatchUpdates(tmp);
    }

    @NonNull
    @Override
    public MovieElementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == Movie.TYPE) {
            return new MovieViewHolder(inflater.inflate(R.layout.movie_item, parent, false), onMovieClickListener);
        } else {
            return new ProgressViewHolder(inflater.inflate(R.layout.loading_item, parent, false));
        }
    }

    public void showLoading() {
        if (isLastItemWithType(Movie.TYPE)) {
            List<MovieElement> list = new ArrayList<>(movies);
            list.add(new MovieProgress());
            dispatchUpdates(list);
        }
    }

    public void dismissLoading() {
        if (isLastItemWithType(MovieProgress.TYPE)) {
            List<MovieElement> list = new ArrayList<>(movies);
            list.remove(movies.size() - 1);
            dispatchUpdates(list);
        }
    }

    private boolean isLastItemWithType(int type) {
        if (movies != null) {
            int size = movies.size();
            return size > 0 && movies.get(size - 1).getType() == type;
        } else {
            return false;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MovieElementViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    private void dispatchUpdates(List<MovieElement> newList) {
        MoviesDiffUtilCallback moviesDiffUtilCallback = new MoviesDiffUtilCallback(movies, newList);
        DiffUtil.DiffResult itemsDiffResult = DiffUtil.calculateDiff(moviesDiffUtilCallback);
        movies = newList;
        itemsDiffResult.dispatchUpdatesTo(this);
    }

    public static class MovieViewHolder extends MovieElementViewHolder implements View.OnClickListener {
        private TextView nameView;
        private TextView nameEngView;
        private TextView descriptionView;
        private TextView premiereDateView;
        private ImageView movieCoverView;

        private Movie movie;
        private OnMovieClickListener onMovieClickListener;

        public MovieViewHolder(View v, OnMovieClickListener onMovieClickListener) {
            super(v);
            nameView = itemView.findViewById(R.id.movie_name_text_view);
            nameEngView = itemView.findViewById(R.id.movie_name_eng_text_view);
            descriptionView = itemView.findViewById(R.id.movie_description_text_view);
            premiereDateView = itemView.findViewById(R.id.movie_premiere_date_text_view);
            movieCoverView = itemView.findViewById(R.id.movie_cover_image_view);
            this.onMovieClickListener = onMovieClickListener;
            v.setOnClickListener(this);

        }

        public void bind(MovieElement movieElement) {
            movie = (Movie) movieElement;
            nameView.setText(movie.getId());
            nameEngView.setText(movie.getNameEng());
            descriptionView.setText(movie.getDescription());
            premiereDateView.setText(movie.getPremiere());
            Glide.with(itemView.getContext())
                    .load(movie.getImage())
                    .apply(new RequestOptions().centerCrop())
                    .into(movieCoverView);
        }

        @Override
        public void onClick(View view) {
            if (onMovieClickListener != null) {
                onMovieClickListener.onMovieClick(movie, movieCoverView, descriptionView);
            }
        }
    }

    public static class ProgressViewHolder extends MovieElementViewHolder {

        ProgressViewHolder(View v) {
            super(v);
        }

        @Override
        public void bind(MovieElement movieElement) {
            // nothing to do :(
        }
    }
}
