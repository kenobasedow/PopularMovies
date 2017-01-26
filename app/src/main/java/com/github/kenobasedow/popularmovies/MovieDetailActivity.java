package com.github.kenobasedow.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    ImageView mMoviePictureImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mMoviePictureImageView = (ImageView) findViewById(R.id.iv_movie_picture);

        if (getIntent().hasExtra(Movie.TAG)) {
            Movie movie = (Movie) getIntent().getSerializableExtra(Movie.TAG);

            Picasso.with(this)
                    .load("http://image.tmdb.org/t/p/w185" + movie.picturePath)
                    .into(mMoviePictureImageView);
        }
    }
}
