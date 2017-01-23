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

    private RecyclerView mMovieGrid;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);

        mMovieGrid = (RecyclerView) findViewById(R.id.rv_movies);
        mMovieGrid.setHasFixedSize(true);
        mMovieGrid.setLayoutManager(new GridLayoutManager(this, NUM_ROWS));
        mAdapter = new MovieAdapter();
        mMovieGrid.setAdapter(mAdapter);

        loadMovies();
    }

    private void loadMovies() {
        new FetchMoviesTask().execute();
    }

    public class FetchMoviesTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            URL moviesRequestUrl = NetworkUtils.buildUrl(getString(R.string.api_key));
            if (moviesRequestUrl == null)
                return null;
            try {
                String moviesJson = NetworkUtils.getResponseFromHttpUrl(moviesRequestUrl);
                if (moviesJson == null)
                    return null;
                return TheMovieDbJsonUtils.getMoviePicturesFromJson(moviesJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] moviePictures) {
            mAdapter.setMoviePictures(moviePictures);
        }
    }
}
