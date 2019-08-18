package com.project.mobile.movie_db_training.list;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.data.model.Genre;
import com.project.mobile.movie_db_training.data.model.Movie;
import com.project.mobile.movie_db_training.detail.MovieDetailActivity;
import com.project.mobile.movie_db_training.utils.Constants;
import com.project.mobile.movie_db_training.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListActivity extends AppCompatActivity implements MoviesListFragment.Callback {

    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey(Constants.LIST_TYPE)) {
                String listType = extras.getString(Constants.LIST_TYPE);
                if (listType != null) {
                    initToolbar(Utils.getTitleFromListType(listType));
                    MoviesListFragment moviesListFragment = MoviesListFragment.newInstance(listType);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_movies_list, moviesListFragment)
                            .commit();
                }
            } else if (extras.containsKey(Constants.GENRE)) {
                Genre genre = extras.getParcelable(Constants.GENRE);
                if (genre != null) {
                    initToolbar(genre.getName());
                    MoviesListFragment moviesListFragment = MoviesListFragment.newInstance(genre);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_movies_list, moviesListFragment)
                            .commit();
                }
            }
        }
        else {
            initToolbar("Detail");
            MoviesListFragment moviesListFragment = new MoviesListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_movies_list, moviesListFragment)
                    .commit();
        }
    }

    private void initToolbar(String title) {
        setSupportActionBar(mToolBar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(title);
        }

    }

    @Override
    public void onMovieClick(Movie movie) {
        startMovieDetail(movie);
    }

    public void startMovieDetail(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.MOVIE_KEY, movie);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
