package com.project.mobile.movie_db_training.detail;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.data.model.Movie;
import com.project.mobile.movie_db_training.utils.Constants;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        Movie movie;
        Bundle extras = intent.getExtras();
        if (extras != null && extras.containsKey(Constants.MOVIE_KEY)) {
            movie = extras.getParcelable(Constants.MOVIE_KEY);
            if (movie != null) {
                MovieDetailFragment movieDetailFragment = MovieDetailFragment.getInstance(movie);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_movie_detail, movieDetailFragment)
                        .commit();
            }
        }
        else {
            //latest  movie
            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_movie_detail, movieDetailFragment)
                    .commit();
        }
    }
}
