package com.github.kenobasedow.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.kenobasedow.popularmovies.databinding.MovieDetailHeaderBinding;
import com.github.kenobasedow.popularmovies.domain.Movie;
import com.github.kenobasedow.popularmovies.domain.Video;

public class MovieTrailersReviewsAdapter extends RecyclerView.Adapter<MovieTrailersReviewsAdapter.MovieViewHolder> {

    private static final int VIEW_TYPE_MOVIE = 0;
    private static final int VIEW_TYPE_VIDEO = 1;

    private Movie mMovie = null;
    private Video[] mVideos = new Video[0];

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final MovieDetailHeaderBinding mBinding;

        public MovieViewHolder(MovieDetailHeaderBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Movie movie) {
            mBinding.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        private final TextView mVideoTextView;

        public VideoViewHolder(View view) {
            super(view);
            mVideoTextView = (TextView) view.findViewById(R.id.tv_trailer)
        }

        public void bind(Video video) {
            mVideoTextView.setText(video.name);
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VIEW_TYPE_MOVIE:
                MovieDetailHeaderBinding binding = MovieDetailHeaderBinding.inflate(inflater, parent, false);
                return new MovieViewHolder(binding);
            case VIEW_TYPE_VIDEO:
                View view = inflater.inflate(R.layout.movie_trailer_item, parent, false);
                return new VideoViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(mMovie);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_MOVIE;
        } else {
            return VIEW_TYPE_VIDEO;
        }
    }

    @Override
    public int getItemCount() {
        if (mMovie == null)
            return 0;

        return mVideos.length + 1;
    }

    public void setMovie(Movie movie) {
        mMovie = movie;
        notifyDataSetChanged();
    }

    public void setVideos(Video[] videos) {
        mVideos = videos;
        notifyDataSetChanged();
    }
}
