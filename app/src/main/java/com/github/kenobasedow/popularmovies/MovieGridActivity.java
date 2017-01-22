package com.github.kenobasedow.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.kenobasedow.popularmovies.utilities.NetworkUtils;
import com.github.kenobasedow.popularmovies.utilities.TheMovieDbJsonUtils;

import java.io.IOException;
import java.net.URL;

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

        loadMovies();
    }

    private void loadMovies() {
        new FetchMoviesTask().execute();
    }

    public class FetchMoviesTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            URL moviesRequestUrl = NetworkUtils.buildUrl();
            if (moviesRequestUrl == null)
                return null;
            try {
                String moviesJson = NetworkUtils.getResponseFromHttpUrl(moviesRequestUrl);
                if (moviesJson == null)
                    return null;
                String[] moviesPictures = TheMovieDbJsonUtils.getMoviePicturesFromJson(moviesJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
