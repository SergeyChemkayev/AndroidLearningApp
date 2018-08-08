package com.example.androidlearningapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidlearningapp.movieitems.Movie;
import com.example.androidlearningapp.movieitems.MovieListElement;
import com.example.androidlearningapp.movieitems.Progress;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MovieListElement> moviesList;
    private static final int VIEW_MOVIE = 1;
    private static final int VIEW_PROGRESS = 0;
    private boolean isNoMoreLoading;

    MoviesAdapter() {
        moviesList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return moviesList.get(position).getType().equals("MOVIE") ? VIEW_MOVIE : VIEW_PROGRESS;
    }

    public void addMovies(List<MovieListElement> list) {
        if (list != null) {
            List<MovieListElement> tmp = new ArrayList<>(moviesList);
            tmp.addAll(list);
            dispatchUpdates(tmp);
        }
        isNoMoreLoading = false;
    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (viewType == VIEW_MOVIE) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View movieItemView = inflater.inflate(R.layout.movie_item, parent, false);
            return new MovieViewHolder(movieItemView);
        } else {
            return new ProgressViewHolder(LayoutInflater.from(context).inflate(R.layout.loading_item, parent, false));
        }
    }

    public void showLoading() {
        if (!isNoMoreLoading && moviesList != null) {
            isNoMoreLoading = true;
            List<MovieListElement> list = new ArrayList<>(moviesList);
            list.add(new Progress());
            dispatchUpdates(list);
        }
    }

    public void stopLoadMovies(boolean isStop) {
        isNoMoreLoading = isStop;
    }

    public void dismissLoading() {
        if (moviesList != null && moviesList.size() > 0) {
            List<MovieListElement> list = new ArrayList<>(moviesList);
            list.remove(moviesList.size() - 1);
            dispatchUpdates(list);
        }
    }

    public void clear() {
        dispatchUpdates(new ArrayList<MovieListElement>());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AbstractViewHolder movieViewHolder = (AbstractViewHolder) holder;
        movieViewHolder.bind(moviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesList == null ? 0 : moviesList.size();
    }

    private void dispatchUpdates(List<MovieListElement> newList) {
        MoviesDiffUtilCallback moviesDiffUtilCallback = new MoviesDiffUtilCallback(moviesList, newList);
        DiffUtil.DiffResult itemsDiffResult = DiffUtil.calculateDiff(moviesDiffUtilCallback);
        moviesList = newList;
        itemsDiffResult.dispatchUpdatesTo(this);
    }

    public static class MovieViewHolder extends AbstractViewHolder {
        private TextView nameView;
        private TextView nameEngView;
        private TextView descriptionView;
        private TextView premiereDateView;
        private ImageView movieCoverView;

        public MovieViewHolder(View v) {
            super(v);
            nameView = (TextView) itemView.findViewById(R.id.movie_name_text_view);
            nameEngView = (TextView) itemView.findViewById(R.id.movie_name_eng_text_view);
            descriptionView = (TextView) itemView.findViewById(R.id.movie_description_text_view);
            premiereDateView = (TextView) itemView.findViewById(R.id.movie_premiere_date_text_view);
            movieCoverView = (ImageView) itemView.findViewById(R.id.movie_cover_image_view);
        }

        public void bind(MovieListElement movieListElement) {
            Movie movie = (Movie) movieListElement;
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

    public static class ProgressViewHolder extends AbstractViewHolder {
        private ProgressBar progressBar;

        ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) itemView.findViewById(R.id.item_load_progress_ber);
        }

        @Override
        public void bind(MovieListElement movieListElement) {
            // nothing to do :(
        }
    }
}
