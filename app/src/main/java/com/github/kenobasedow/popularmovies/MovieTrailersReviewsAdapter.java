package com.github.kenobasedow.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.kenobasedow.popularmovies.databinding.MovieDetailHeaderBinding;
import com.github.kenobasedow.popularmovies.domain.Movie;
import com.github.kenobasedow.popularmovies.domain.Video;

public class MovieTrailersReviewsAdapter extends RecyclerView.Adapter<MovieTrailersReviewsAdapter.ViewHolder> {

    private static final int VIEW_TYPE_MOVIE = 0;
    private static final int VIEW_TYPE_VIDEO = 1;

    private Movie mMovie = null;
    private Video[] mVideos = new Video[0];

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MovieDetailHeaderBinding mBinding;
        private final TextView mVideoTextView;

        public ViewHolder(MovieDetailHeaderBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mVideoTextView = null;
        }

        public ViewHolder(View view) {
            super(view);
            mBinding = null;
            mVideoTextView = (TextView) view.findViewById(R.id.tv_trailer);
        }

        public void bind(Movie movie) {
            mBinding.setMovie(movie);
            mBinding.executePendingBindings();
        }

        public void bind(Video video) {
            mVideoTextView.setText(video.name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VIEW_TYPE_MOVIE:
                MovieDetailHeaderBinding binding = MovieDetailHeaderBinding.inflate(inflater, parent, false);
                return new ViewHolder(binding);
            case VIEW_TYPE_VIDEO:
                View view = inflater.inflate(R.layout.movie_trailer_item, parent, false);
                return new ViewHolder(view);
            default:
                throw new IllegalArgumentException("Unknown view type");
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0) {
            holder.bind(mMovie);
        } else {
            holder.bind(mVideos[position-1]);
        }
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
