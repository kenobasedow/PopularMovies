package com.github.kenobasedow.popularmovies.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TheMovieDbJsonUtils {

    private static final String JSON_ARRAY_MOVIES = "results";
    private static final String JSON_ATTRIBUTE_PICTURE = "poster_path";

    public static String[] getMoviePicturesFromJson(String moviesJsonString) {
        try {
            JSONObject json = new JSONObject(moviesJsonString);
            JSONArray movies = json.getJSONArray(JSON_ARRAY_MOVIES);
            String[] moviePictures = new String[movies.length()];
            for (int i = 0; i < movies.length(); i++) {
                JSONObject movie = movies.getJSONObject(i);
                moviePictures[i] = movie.getString(JSON_ATTRIBUTE_PICTURE);
            }
            return moviePictures;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
}
