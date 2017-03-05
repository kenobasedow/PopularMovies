package com.github.kenobasedow.popularmovies;

import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.github.kenobasedow.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

public class Movie implements Parcelable {

    private static final String FORMAT_RATING = "%.1f/10";

    public static final String TAG = Movie.class.getName();

    public String title;
    public String picturePath;
    public String plot;
    public double rating;
    public String releaseDate;

    public Movie() {
    }

    public String getRatingString() {
        return String.format(FORMAT_RATING, rating);
    }

    public String getPictureUrl() {
        return NetworkUtils.MOVIE_PICTURE_BASE_URL + picturePath;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
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
