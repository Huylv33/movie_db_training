package com.project.mobile.movie_db_training.detail;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.data.model.Cast;
import com.project.mobile.movie_db_training.data.model.Movie;
import com.project.mobile.movie_db_training.person.PersonActivity;
import com.project.mobile.movie_db_training.utils.Constants;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        Bundle extras = intent.getExtras();
        MovieDetailFragment movieDetailFragment;
        if (extras != null && extras.containsKey(Constants.MOVIE_KEY)) {
            Movie movie = extras.getParcelable(Constants.MOVIE_KEY);
            movieDetailFragment = MovieDetailFragment.newInstance(movie);
        } else {
            //latest movie
            movieDetailFragment = new MovieDetailFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_movie_detail, movieDetailFragment)
                .commit();
    }

    @Override
    public void onCastClick(String id) {
        startPersonDetail(id);
    }
    private void startPersonDetail(String id) {
        Intent intent = new Intent(this, PersonActivity.class);
        intent.putExtra("personId",id);
        startActivity(intent);
    }

}
