package com.project.mobile.movie_db_training;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.project.mobile.movie_db_training.detail.MovieDetailActivity;
import com.project.mobile.movie_db_training.genre.GenresListActivity;
import com.project.mobile.movie_db_training.utils.Constants;
import com.project.mobile.movie_db_training.utils.IntentUtils;
import com.project.mobile.movie_db_training.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.card_genre)
    CardView mCardGenre;
    @BindView(R.id.card_now_playing)
    CardView mCardNowPlaying;
    @BindView(R.id.card_popular)
    CardView mCardPopular;
    @BindView(R.id.card_top_rated)
    CardView mCardTopRated;
    @BindView(R.id.card_upcoming)
    CardView mCardUpComing;
    @BindView(R.id.card_latest)
    CardView mCardLatest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ButterKnife.bind(this);
        setListener();
    }

    private void setListener() {
        mCardLatest.setOnClickListener(view -> {
            Intent intent = new Intent(this, MovieDetailActivity.class);
            startActivity(intent);
        });
        mCardGenre.setOnClickListener(view -> {
            Intent intent = new Intent(this, GenresListActivity.class);
            startActivity(intent);
        });
        mCardUpComing.setOnClickListener(view -> {
            IntentUtils.startMoviesListActivity(this,Constants.UPCOMING);
        });
        mCardTopRated.setOnClickListener(view -> {
            IntentUtils.startMoviesListActivity(this, Constants.TOP_RATED);
        });
        mCardNowPlaying.setOnClickListener(view -> {
            IntentUtils.startMoviesListActivity(this, Constants.NOW_PLAYING);
        });
        mCardPopular.setOnClickListener(view -> {
            IntentUtils.startMoviesListActivity(this, Constants.POPULAR);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    private void initView() {
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
}
