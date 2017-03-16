package com.github.kenobasedow.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.kenobasedow.popularmovies.domain.Movie;

public class MovieDetailActivity extends AppCompatActivity
//        implements AsyncTaskCompleteListener<Video[]>
{

    private RecyclerView mRecyclerView;
    private MovieTrailersReviewsAdapter mAdapter;
//    private TextView mTrailersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mAdapter = new MovieTrailersReviewsAdapter();

//        ActivityMovieDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.movie_detail_header);
//
//        mTrailersTextView = (TextView) findViewById(R.id.tv_trailers);
//
        if (getIntent().hasExtra(Movie.TAG)) {
            Movie movie = (Movie) getIntent().getParcelableExtra(Movie.TAG);
            mAdapter.setMovie(movie);

//            new FetchVideosTask(this).execute(getString(R.string.api_key), movie.id);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_trailers_reviews);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

//    @Override
//    public void onTaskComplete(Video[] result) {
//        mTrailersTextView.setText("");
//
//        if (result == null)
//            return;
//
//        for (Video video : result) {
//            mTrailersTextView.append(video.name + "\n");
//        }
//    }
}
