package com.github.kenobasedow.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private int mNumberItems;

    public MovieAdapter(int numberItems) {
        mNumberItems = numberItems;
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_grid_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(position);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView mMovieImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mMovieImageView = (ImageView) itemView.findViewById(R.id.iv_movie_thumpnail);
        }

        public void bind(int index) {
            Picasso.with(itemView.getContext())
                    .load("http://image.tmdb.org/t/p/w185/WLQN5aiQG8wc9SeKwixW7pAR8K.jpg")
                    .into(mMovieImageView);
        }
    }
}
