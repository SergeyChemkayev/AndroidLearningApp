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
import com.example.androidlearningapp.movieitems.MovieElement;
import com.example.androidlearningapp.movieitems.Progress;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<BindViewHolder> {
    private List<MovieElement> moviesList;
    private static final int VIEW_MOVIE = 1;

    MoviesAdapter() {
        moviesList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return moviesList.get(position).getType();
    }

    public void setMovies(List<MovieElement> list) {
        if(list==null){
            throw new NullPointerException("list must be not null");
        }
            dispatchUpdates(list);
    }

    public void addMovies(List<MovieElement> list) {
        if (list==null){
            throw new NullPointerException("list must be not null");
        }
            List<MovieElement> tmp = new ArrayList<>(moviesList);
            tmp.addAll(list);
            dispatchUpdates(tmp);
    }

    @NonNull
    @Override
    public BindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == VIEW_MOVIE) {
            return new MovieViewHolder(inflater.inflate(R.layout.movie_item, parent, false));
        } else {
            return new ProgressViewHolder(inflater.inflate(R.layout.loading_item, parent, false));
        }
    }

    public void showLoading() {
        if (moviesList != null) {
            List<MovieElement> list = new ArrayList<>(moviesList);
            list.add(new Progress());
            dispatchUpdates(list);
        }
    }

    public void dismissLoading() {
        if (moviesList != null && moviesList.size() > 0) {
            List<MovieElement> list = new ArrayList<>(moviesList);
            list.remove(moviesList.size() - 1);
            dispatchUpdates(list);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BindViewHolder holder, int position) {
        holder.bind(moviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesList == null ? 0 : moviesList.size();
    }

    private void dispatchUpdates(List<MovieElement> newList) {
        MoviesDiffUtilCallback moviesDiffUtilCallback = new MoviesDiffUtilCallback(moviesList, newList);
        DiffUtil.DiffResult itemsDiffResult = DiffUtil.calculateDiff(moviesDiffUtilCallback);
        moviesList = newList;
        itemsDiffResult.dispatchUpdatesTo(this);
    }

    public static class MovieViewHolder extends BindViewHolder {
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

        public void bind(MovieElement movieElement) {
            Movie movie = (Movie) movieElement;
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

    public static class ProgressViewHolder extends BindViewHolder {
        private ProgressBar progressBar;

        ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) itemView.findViewById(R.id.item_load_progress_ber);
        }

        @Override
        public void bind(MovieElement movieElement) {
            // nothing to do :(
        }
    }
}
