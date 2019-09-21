package com.project.mobile.movie_db_training.search;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.data.model.Movie;
import com.project.mobile.movie_db_training.list.MoviesListFragment;
import com.project.mobile.movie_db_training.main.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchContract.View,
        MoviesListFragment.Callback {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.search_view)
    SearchView mSearchView;
    @BindView(R.id.movies_rv)
    RecyclerView mMoviesRv;
    private MovieAdapter mMovieAdapter;
    private SearchContract.Presenter mPresenter;
    private List<Movie> mMovies = new ArrayList<>();
    private MoviesListFragment.Callback mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initToolbar("");
        mMovieAdapter = new MovieAdapter(mMovies, this);
        mMoviesRv.setAdapter(mMovieAdapter);
        mMoviesRv.setLayoutManager(new GridLayoutManager(this, 3));
        mPresenter = new SearchPresenterImpl();
        mPresenter.setView(this);
        setupSearchView();
    }

    private void initToolbar(String title) {
        setSupportActionBar(mToolBar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(title);
        }
        mToolBar.setNavigationOnClickListener(view -> {
            finish();
        });
    }

    private void setupSearchView() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.searchMovies(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void showMovies(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        mMovieAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading(String message) {

    }

    @Override
    public void onMovieClick(Movie movie) {

    }
}
