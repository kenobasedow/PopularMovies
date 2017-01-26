package com.github.kenobasedow.popularmovies.utilities;

import com.github.kenobasedow.popularmovies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TheMovieDbJsonUtils {

    private static final String JSON_ARRAY_MOVIES = "results";
    private static final String JSON_ATTRIBUTE_PICTURE = "poster_path";

    public static Movie[] getMoviePicturesFromJson(String moviesJsonString) {
        try {
            JSONObject json = new JSONObject(moviesJsonString);
            JSONArray jsonMovies = json.getJSONArray(JSON_ARRAY_MOVIES);
            Movie[] movies = new Movie[jsonMovies.length()];
            for (int i = 0; i < jsonMovies.length(); i++) {
                JSONObject jsonMovie = jsonMovies.getJSONObject(i);
                Movie movie = new Movie();
                movie.picturePath = jsonMovie.getString(JSON_ATTRIBUTE_PICTURE);
                movies[i] = movie;
            }
            return movies;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
