package com.example.androidlearningapp;

import android.content.Context;
import android.os.Handler;
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
import com.example.androidlearningapp.listeners.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Movie> moviesList;
    private static final int VIEW_MOVIE = 1;
    private static final int VIEW_PROGRESS = 0;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isMoreLoading = true;

    MoviesAdapter(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
        moviesList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return moviesList.get(position) != null ? VIEW_MOVIE : VIEW_PROGRESS;
    }

    private void setMoviesList(List<Movie> moviesList) {
        MoviesDiffUtilCallback moviesDiffUtilCallback = new MoviesDiffUtilCallback(this.moviesList, moviesList);
        DiffUtil.DiffResult itemsDiffResult = DiffUtil.calculateDiff(moviesDiffUtilCallback);
        this.moviesList = moviesList;
        itemsDiffResult.dispatchUpdatesTo(this);
    }

    public void addAll(List<Movie> list) {
        List<Movie> tmp = moviesList;
        tmp.addAll(list);
        setMoviesList(tmp);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_MOVIE) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View movieItemView = inflater.inflate(R.layout.movie_item, parent, false);
            return new MovieViewHolder(movieItemView);
        } else {
            return new ProgressViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent, false));
        }
    }

    public void showLoading() {
        if (isMoreLoading && moviesList != null && onLoadMoreListener != null) {
            isMoreLoading = false;
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    moviesList.add(null);
                    notifyItemInserted(moviesList.size() - 1);
                    onLoadMoreListener.onLoadMore();
                }
            });
        }
    }

    public void setMoreLoading(boolean isMore) {
        isMoreLoading = isMore;
    }

    public void dismissLoading() {
        if (moviesList != null && moviesList.size() > 0) {
            moviesList.remove(moviesList.size() - 1);
            notifyItemRemoved(moviesList.size());
        }
    }

    public void clear() {
        moviesList.clear();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder) {
            MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
            movieViewHolder.bind(moviesList.get(position));
        }
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
            descriptionView = (TextView) itemView.findViewById(R.id.movie_description_text_view);
            premiereDateView = (TextView) itemView.findViewById(R.id.movie_premiere_date_text_view);
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

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) itemView.findViewById(R.id.item_load_progress_ber);
        }

    }
}
