package com.github.kenobasedow.popularmovies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.kenobasedow.popularmovies.databinding.ActivityMovieDetailBinding;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        if (getIntent().hasExtra(Movie.TAG)) {
            Movie movie = (Movie) getIntent().getParcelableExtra(Movie.TAG);
            binding.setMovie(movie);
        }
    }
}
