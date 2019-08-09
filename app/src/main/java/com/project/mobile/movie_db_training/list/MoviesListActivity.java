package com.project.mobile.movie_db_training.list;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.utils.Constants;
import com.project.mobile.movie_db_training.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    private String mListType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mListType = intent.getStringExtra(Constants.LIST_TYPE);
        initToolbar();
        initFragment();
    }

    private void initToolbar() {
        setSupportActionBar(mToolBar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(Utils.getTitleFromListType(mListType));
        }
    }

    private void initFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.LIST_TYPE, mListType);
        FragmentManager fragmentManager = getSupportFragmentManager();
        MoviesListFragment moviesListFragment = new MoviesListFragment();
        moviesListFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_movies_list, moviesListFragment).commit();
    }

}
