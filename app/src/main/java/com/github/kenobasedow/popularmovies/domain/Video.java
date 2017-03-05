package com.github.kenobasedow.popularmovies.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Video implements Parcelable {

    public String name;
    public String key;
    // https://www.youtube.com/watch?v={key}

    public Video() {
    }

    public Video(Parcel parcel) {
        name = parcel.readString();
        key = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(key);
    }

    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {

        @Override
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
