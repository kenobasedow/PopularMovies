package com.github.kenobasedow.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private String[] mMoviePictures = null;

    public MovieAdapter() {
    }

    @Override
    public int getItemCount() {
        if (mMoviePictures == null)
            return 0;
        return mMoviePictures.length;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_grid_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        if (mMoviePictures.length <= 0)
            return;
        if (position < 0)
            return;
        if (position >= mMoviePictures.length)
            return;
        Picasso.with(holder.itemView.getContext())
                .load("http://image.tmdb.org/t/p/w185/" + mMoviePictures[position])
                .into(holder.mMovieImageView);
    }

    public void setMoviePictures(String[] moviePictures) {
        mMoviePictures = moviePictures;
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mMovieImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mMovieImageView = (ImageView) itemView.findViewById(R.id.iv_movie_thumpnail);
        }
    }
}
