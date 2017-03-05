package com.github.kenobasedow.popularmovies.utilities;

import com.github.kenobasedow.popularmovies.domain.Movie;
import com.github.kenobasedow.popularmovies.domain.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TheMovieDbJsonUtils {

    private static final String JSON_ARRAY_MOVIES = "results";
    private static final String JSON_ATTRIBUTE_ID = "id";
    private static final String JSON_ATTRIBUTE_TITLE = "title";
    private static final String JSON_ATTRIBUTE_PICTURE_PATH = "poster_path";
    private static final String JSON_ATTRIBUTE_PLOT = "overview";
    private static final String JSON_ATTRIBUTE_RATING = "vote_average";
    private static final String JSON_ATTRIBUTE_RELEASE_DATE = "release_date";

    private static final String JSON_ARRAY_VIDEOS = "results";
    private static final String JSON_ATTRIBUTE_NAME = "name";
    private static final String JSON_ATTRIBUTE_KEY = "key";

    public static Movie[] getMoviesFromJson(String moviesJsonString) {
        try {
            JSONObject json = new JSONObject(moviesJsonString);
            JSONArray jsonMovies = json.getJSONArray(JSON_ARRAY_MOVIES);
            Movie[] movies = new Movie[jsonMovies.length()];
            for (int i = 0; i < jsonMovies.length(); i++) {
                JSONObject jsonMovie = jsonMovies.getJSONObject(i);
                Movie movie = new Movie();
                movie.id = jsonMovie.getString(JSON_ATTRIBUTE_ID);
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

    public static Video[] getVideosFromJson(String videosJsonString) {
        try {
            JSONObject json = new JSONObject(videosJsonString);
            JSONArray jsonVideos = json.getJSONArray(JSON_ARRAY_VIDEOS);
            Video[] videos = new Video[jsonVideos.length()];
            for (int i = 0; i < jsonVideos.length(); i++) {
                JSONObject jsonVideo = jsonVideos.getJSONObject(i);
                Video video = new Video();
                video.name = jsonVideo.getString(JSON_ATTRIBUTE_NAME);
                video.key = jsonVideo.getString(JSON_ATTRIBUTE_KEY);
                videos[i] = video;
            }
            return videos;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
