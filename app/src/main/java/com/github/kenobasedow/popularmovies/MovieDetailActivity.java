package com.github.kenobasedow.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.kenobasedow.popularmovies.domain.Movie;
import com.github.kenobasedow.popularmovies.domain.Video;

public class MovieDetailActivity extends AppCompatActivity
        implements AsyncTaskCompleteListener<Video[]>, MovieTrailersReviewsAdapter.VideoClickListener {

    private RecyclerView mRecyclerView;
    private MovieTrailersReviewsAdapter mAdapter;
    private Video[] mVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mAdapter = new MovieTrailersReviewsAdapter(this);

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
        mVideos = result;
        mAdapter.setVideos(mVideos);
    }

    @Override
    public void onVideoClicked(int videoIndex) {
        Uri uri = Uri.parse("https://www.youtube.com/watch").buildUpon().appendQueryParameter("v", mVideos[videoIndex].key).build();
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
