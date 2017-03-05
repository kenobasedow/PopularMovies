package com.github.kenobasedow.popularmovies;

import android.os.AsyncTask;

import com.github.kenobasedow.popularmovies.domain.Movie;
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

        URL moviesRequestUrl = NetworkUtils.buildQueryUrl(apiKey, prefSortOrder);
        if (moviesRequestUrl == null)
            return null;
        try {
            String moviesJson = NetworkUtils.getResponseFromHttpUrl(moviesRequestUrl);
            if (moviesJson == null)
                return null;
            Movie[] movies = TheMovieDbJsonUtils.getMoviesFromJson(moviesJson);
            for (Movie movie : movies) {
                URL videosUrl = NetworkUtils.buildVideosUrl(apiKey, movie.id);
                if (videosUrl == null) continue;
                String videosJson = NetworkUtils.getResponseFromHttpUrl(videosUrl);
                if (videosJson == null) continue;
                movie.videos = TheMovieDbJsonUtils.getVideosFromJson(videosJson);
            }
            return movies;
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
