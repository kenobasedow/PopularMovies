package com.github.kenobasedow.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    TextView mMovieTitleTextView;
    TextView mMovieReleaseDateTextView;
    TextView mMovieRatingTextView;
    TextView mMoviePlotTextView;

    ImageView mMoviePictureImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mMovieTitleTextView = (TextView) findViewById(R.id.tv_movie_title);
        mMovieReleaseDateTextView = (TextView) findViewById(R.id.tv_movie_release_date);
        mMovieRatingTextView = (TextView) findViewById(R.id.tv_movie_rating);
        mMoviePlotTextView = (TextView) findViewById(R.id.tv_movie_plot);

        mMoviePictureImageView = (ImageView) findViewById(R.id.iv_movie_picture);

        if (getIntent().hasExtra(Movie.TAG)) {
            Movie movie = (Movie) getIntent().getSerializableExtra(Movie.TAG);

            mMovieTitleTextView.setText(movie.title);
            mMovieReleaseDateTextView.setText(movie.releaseDate);
            mMovieRatingTextView.setText(String.format("%.1f/10", movie.rating));
            mMoviePlotTextView.setText(movie.plot);

            Picasso.with(this)
                    .load("http://image.tmdb.org/t/p/w185" + movie.picturePath)
                    .into(mMoviePictureImageView);
        }
    }
}
