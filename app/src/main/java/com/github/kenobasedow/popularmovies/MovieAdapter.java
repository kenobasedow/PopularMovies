package com.github.kenobasedow.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        private TextView mMovieTextView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            mMovieTextView = (TextView) itemView.findViewById(R.id.tv_movie_name);
        }

        public void bind(int index) {
            mMovieTextView.setText("Movie Name " + index);
        }
    }
}
