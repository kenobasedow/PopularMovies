package com.github.kenobasedow.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final String MOVIE_PICTURE_BASE_URL = "http://image.tmdb.org/t/p/w185";

    private Movie[] mMovies = null;
    private MovieClickListener mMovieClickListner;

    public interface MovieClickListener {
        void onClick(int position);
    }

    public MovieAdapter(MovieClickListener movieClickListener) {
        mMovieClickListner = movieClickListener;
    }

    public Movie movie(int position) {
        if (!validMovie(position))
            return null;
        return mMovies[position];
    }

    @Override
    public int getItemCount() {
        if (mMovies == null)
            return 0;
        return mMovies.length;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_grid_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        if (!validMovie(position))
            return;
        Picasso.with(holder.itemView.getContext())
                .load(MOVIE_PICTURE_BASE_URL + mMovies[position].picturePath)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.mMovieImageView);
    }

    public void setMovies(Movie[] movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    private boolean validMovie(int position) {
        if (mMovies == null) return false;
        if (mMovies.length <= 0) return false;
        if (position < 0) return false;
        if (position >= mMovies.length) return false;
        return true;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView mMovieImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mMovieImageView = (ImageView) itemView.findViewById(R.id.iv_movie_thumpnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mMovieClickListner.onClick(getAdapterPosition());
        }
    }
}
