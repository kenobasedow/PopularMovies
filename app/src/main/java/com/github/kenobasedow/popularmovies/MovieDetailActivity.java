package com.github.kenobasedow.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.kenobasedow.popularmovies.domain.Movie;
import com.github.kenobasedow.popularmovies.domain.Video;

public class MovieDetailActivity extends AppCompatActivity
        implements AsyncTaskCompleteListener<Video[]> {

    private RecyclerView mRecyclerView;
    private MovieTrailersReviewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mAdapter = new MovieTrailersReviewsAdapter();

        if (getIntent().hasExtra(Movie.TAG)) {
            Movie movie = (Movie) getIntent().getParcelableExtra(Movie.TAG);
            mAdapter.setMovie(movie);

            new FetchVideosTask(this).execute(getString(R.string.api_key), movie.id);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_trailers_reviews);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onTaskComplete(Video[] result) {
        mAdapter.setVideos(result);
    }
}
