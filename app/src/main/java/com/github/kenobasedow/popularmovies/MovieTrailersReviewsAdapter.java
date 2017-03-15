package com.github.kenobasedow.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.kenobasedow.popularmovies.databinding.MovieDetailHeaderBinding;

public class MovieTrailersReviewsAdapter extends RecyclerView.Adapter<MovieTrailersReviewsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MovieDetailHeaderBinding mBinding;

        ViewHolder(MovieDetailHeaderBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieDetailHeaderBinding binding = MovieDetailHeaderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
