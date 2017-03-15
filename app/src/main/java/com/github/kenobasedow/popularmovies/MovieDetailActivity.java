package com.github.kenobasedow.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MovieDetailActivity extends AppCompatActivity
//        implements AsyncTaskCompleteListener<Video[]>
{

    private RecyclerView mMovieTrailersReviews;
//    private TextView mTrailersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mMovieTrailersReviews = (RecyclerView) findViewById(R.id.rv_movie_trailers_reviews);
        mMovieTrailersReviews.setLayoutManager(new LinearLayoutManager(this));
        mMovieTrailersReviews.setAdapter(new MovieTrailersReviewsAdapter());

//        ActivityMovieDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.movie_detail_header);
//
//        mTrailersTextView = (TextView) findViewById(R.id.tv_trailers);
//
//        if (getIntent().hasExtra(Movie.TAG)) {
//            Movie movie = (Movie) getIntent().getParcelableExtra(Movie.TAG);
//            binding.setMovie(movie);
//
//            new FetchVideosTask(this).execute(getString(R.string.api_key), movie.id);
//
//        }
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
