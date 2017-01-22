package com.github.kenobasedow.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MovieGridActivity extends AppCompatActivity {

    private static final int NUM_ROWS = 2;
    private static final int NUM_ITEMS = 10;

    private RecyclerView mMovieGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);

        mMovieGrid = (RecyclerView) findViewById(R.id.rv_movies);
        mMovieGrid.setHasFixedSize(true);
        mMovieGrid.setLayoutManager(new GridLayoutManager(this, NUM_ROWS));
        mMovieGrid.setAdapter(new MovieAdapter(NUM_ITEMS));
    }
}
