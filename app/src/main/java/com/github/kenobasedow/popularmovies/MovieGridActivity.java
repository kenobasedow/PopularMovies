package com.github.kenobasedow.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_grid_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadMovies() {
        new FetchMoviesTask().execute();
    }

    public class FetchMoviesTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MovieGridActivity.this);
            String prefSortOrder = sharedPref.getString(getString(R.string.pref_sortOrderKey), getString(R.string.pref_sortOrderDefaultValue));

            URL moviesRequestUrl = NetworkUtils.buildUrl(getString(R.string.api_key), prefSortOrder);
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
