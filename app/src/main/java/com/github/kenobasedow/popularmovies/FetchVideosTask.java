package com.github.kenobasedow.popularmovies;

import android.os.AsyncTask;

import com.github.kenobasedow.popularmovies.domain.Video;
import com.github.kenobasedow.popularmovies.utilities.NetworkUtils;
import com.github.kenobasedow.popularmovies.utilities.TheMovieDbJsonUtils;

import java.io.IOException;
import java.net.URL;

public class FetchVideosTask extends AsyncTask<String, Void, Video[]> {

    AsyncTaskCompleteListener<Video[]> mListener;

    public FetchVideosTask(AsyncTaskCompleteListener<Video[]> listener) {
        mListener = listener;
    }

    @Override
    protected Video[] doInBackground(String... arguments) {
        String apiKey = arguments[0];
        String movieId = arguments[1];

        try {
            URL videosUrl = NetworkUtils.buildVideosUrl(apiKey, movieId);
            if (videosUrl == null) return null;
            String videosJson = NetworkUtils.getResponseFromHttpUrl(videosUrl);
            if (videosJson == null) return null;
            return TheMovieDbJsonUtils.getVideosFromJson(videosJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Video[] videos) {
        mListener.onTaskComplete(videos);
    }
}
