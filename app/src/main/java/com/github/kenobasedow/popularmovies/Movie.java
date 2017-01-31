package com.github.kenobasedow.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    public static final String TAG = Movie.class.getName();

    public String title;
    public String picturePath;
    public String plot;
    public double rating;
    public String releaseDate;

    public Movie() {
    }

    public Movie(Parcel parcel) {
        title = parcel.readString();
        picturePath = parcel.readString();
        plot = parcel.readString();
        rating = parcel.readDouble();
        releaseDate = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeString(picturePath);
        parcel.writeString(plot);
        parcel.writeDouble(rating);
        parcel.writeString(releaseDate);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
