package com.github.kenobasedow.popularmovies;

import android.os.AsyncTask;

import com.github.kenobasedow.popularmovies.utilities.NetworkUtils;
import com.github.kenobasedow.popularmovies.utilities.TheMovieDbJsonUtils;

import java.io.IOException;
import java.net.URL;

public class FetchMoviesTask extends AsyncTask<String, Void, Movie[]> {

    AsyncTaskCompleteListener<Movie[]> mListener;

    public FetchMoviesTask(AsyncTaskCompleteListener<Movie[]> listener) {
        mListener = listener;
    }

    @Override
    protected Movie[] doInBackground(String... arguments) {
        String apiKey = arguments[0];
        String prefSortOrder = arguments[1];

        URL moviesRequestUrl = NetworkUtils.buildUrl(apiKey, prefSortOrder);
        if (moviesRequestUrl == null)
            return null;
        try {
            String moviesJson = NetworkUtils.getResponseFromHttpUrl(moviesRequestUrl);
            if (moviesJson == null)
                return null;
            return TheMovieDbJsonUtils.getMoviesFromJson(moviesJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Movie[] movies) {
        mListener.onTaskComplete(movies);
    }
}
