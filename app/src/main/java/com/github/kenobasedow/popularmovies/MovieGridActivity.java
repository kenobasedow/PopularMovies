package com.github.kenobasedow.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MovieGridActivity extends AppCompatActivity
        implements MovieAdapter.MovieClickListener, AsyncTaskCompleteListener<Movie[]> {

    private RecyclerView mMovieGrid;
    private MovieAdapter mAdapter;

    private TextView mLoadingErrorTextView;
    private ProgressBar mLoadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);

        mMovieGrid = (RecyclerView) findViewById(R.id.rv_movies);
        mMovieGrid.setHasFixedSize(true);
        mMovieGrid.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns(this)));
        mAdapter = new MovieAdapter(this);
        mMovieGrid.setAdapter(mAdapter);

        mLoadingErrorTextView = (TextView) findViewById(R.id.tv_load_error);
        mLoadingProgressBar = (ProgressBar) findViewById(R.id.pb_loading_movies);
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
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MovieGridActivity.this);
        String prefSortOrder = sharedPref.getString(getString(R.string.pref_sortOrderKey), getString(R.string.pref_sortOrderDefaultValue));

        if (prefSortOrder.equals(getString(R.string.pref_sortOrderDefaultValue)))
            setTitle(getString(R.string.popular_title));
        else
            setTitle(getString(R.string.top_rated_title));

        mMovieGrid.setVisibility(View.VISIBLE);
        mLoadingErrorTextView.setVisibility(View.INVISIBLE);
        mLoadingProgressBar.setVisibility(View.VISIBLE);

        new FetchMoviesTask(this).execute(getString(R.string.api_key), prefSortOrder);
    }

    @Override
    public void onTaskComplete(Movie[] movies) {
        mLoadingProgressBar.setVisibility(View.INVISIBLE);
        if (movies == null) {
            mMovieGrid.setVisibility(View.INVISIBLE);
            mLoadingErrorTextView.setVisibility(View.VISIBLE);
        }
        mAdapter.setMovies(movies);
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Movie.TAG, mAdapter.movie(position));
        startActivity(intent);
    }

    private static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

}
