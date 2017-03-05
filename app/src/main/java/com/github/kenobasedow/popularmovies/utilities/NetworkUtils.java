package com.github.kenobasedow.popularmovies.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String API_KEY_PARAM = "api_key";
    private static final String VIDEOS_PATH = "videos";

    public static final String MOVIE_BASE_URL = "http://api.themoviedb.org/3/movie";
    public static final String MOVIE_PICTURE_BASE_URL = "http://image.tmdb.org/t/p/w185";

    public static URL buildQueryUrl(String apiKey, String sortOrder) {
        try {
            String uri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendPath(sortOrder)
                    .appendQueryParameter(API_KEY_PARAM, apiKey)
                    .build().toString();
            Log.v(TAG, "Build query URI: " + uri);
            return new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URL buildVideosUrl(String apiKey, String movieId) {
        try {
            String uri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendPath(movieId)
                    .appendPath(VIDEOS_PATH)
                    .appendQueryParameter(API_KEY_PARAM, apiKey)
                    .build().toString();
            Log.v(TAG, "Build videos URI: " + uri);
            return new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                String response = scanner.next();
                Log.v(TAG, "Got response: " + response);
                return response;
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
