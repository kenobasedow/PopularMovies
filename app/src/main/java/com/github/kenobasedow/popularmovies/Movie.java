package com.github.kenobasedow.popularmovies;

import java.io.Serializable;

public class Movie implements Serializable {
    public static final String TAG = Movie.class.getName();

    public String title;
    public String picturePath;
    public String plot;
    public double rating;
    public String releaseDate;
}
