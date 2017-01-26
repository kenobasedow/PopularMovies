package com.github.kenobasedow.popularmovies.utilities;

import com.github.kenobasedow.popularmovies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TheMovieDbJsonUtils {

    private static final String JSON_ARRAY_MOVIES = "results";
    private static final String JSON_ATTRIBUTE_TITLE = "title";
    private static final String JSON_ATTRIBUTE_PICTURE_PATH = "poster_path";
    private static final String JSON_ATTRIBUTE_PLOT = "overview";
    private static final String JSON_ATTRIBUTE_RATING = "vote_average";
    private static final String JSON_ATTRIBUTE_RELEASE_DATE = "release_date";

    public static Movie[] getMoviesFromJson(String moviesJsonString) {
        try {
            JSONObject json = new JSONObject(moviesJsonString);
            JSONArray jsonMovies = json.getJSONArray(JSON_ARRAY_MOVIES);
            Movie[] movies = new Movie[jsonMovies.length()];
            for (int i = 0; i < jsonMovies.length(); i++) {
                JSONObject jsonMovie = jsonMovies.getJSONObject(i);
                Movie movie = new Movie();
                movie.title = jsonMovie.getString(JSON_ATTRIBUTE_TITLE);
                movie.picturePath = jsonMovie.getString(JSON_ATTRIBUTE_PICTURE_PATH);
                movie.plot = jsonMovie.getString(JSON_ATTRIBUTE_PLOT);
                movie.rating = jsonMovie.getDouble(JSON_ATTRIBUTE_RATING);
                movie.releaseDate = jsonMovie.getString(JSON_ATTRIBUTE_RELEASE_DATE);
                movies[i] = movie;
            }
            return movies;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
