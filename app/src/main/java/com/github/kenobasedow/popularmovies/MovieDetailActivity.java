package com.github.kenobasedow.popularmovies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.kenobasedow.popularmovies.databinding.ActivityMovieDetailBinding;
import com.github.kenobasedow.popularmovies.domain.Movie;
import com.github.kenobasedow.popularmovies.domain.Video;

public class MovieDetailActivity extends AppCompatActivity
        implements AsyncTaskCompleteListener<Video[]> {

    private TextView mTrailersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        mTrailersTextView = (TextView) findViewById(R.id.tv_trailers);

        if (getIntent().hasExtra(Movie.TAG)) {
            Movie movie = (Movie) getIntent().getParcelableExtra(Movie.TAG);
            binding.setMovie(movie);

            new FetchVideosTask(this).execute(getString(R.string.api_key), movie.id);

        }
    }

    @Override
    public void onTaskComplete(Video[] result) {
        mTrailersTextView.setText("");

        if (result == null)
            return;

        for (Video video : result) {
            mTrailersTextView.append(video.name + "\n");
        }
    }
}
