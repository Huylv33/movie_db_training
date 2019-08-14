package com.project.mobile.movie_db_training.genre;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.data.model.Genre;
import com.project.mobile.movie_db_training.list.MoviesListActivity;
import com.project.mobile.movie_db_training.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenresListActivity extends AppCompatActivity implements GenresListFragment.Callback{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres_list);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(Constants.GENRES_TITLE_BAR);
        }
        mToolbar.setNavigationOnClickListener(view -> {
            finish();
        });
    }

    @Override
    public void onGenreClick(Genre genre) {
        startMoviesList(genre);
    }
    private void startMoviesList(Genre genre) {
        Intent intent = new Intent(this, MoviesListActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.GENRE, genre);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
