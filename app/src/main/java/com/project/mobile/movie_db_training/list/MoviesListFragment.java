package com.project.mobile.movie_db_training.list;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesListFragment extends Fragment implements MoviesListContract.View {
    @BindView(R.id.rv_movies_list)
    RecyclerView moviesListRv;
    private MoviesListAdapter mAdapter;
    private Unbinder mUnbinder;
    private List<Movie> mMovieList = new ArrayList<>();

    public MoviesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        initLayout();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovieList.add(new Movie("AHihihihi", "4.5", "2009-01-02"));
    }

    private void initLayout() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        moviesListRv.setLayoutManager(layoutManager);
        mAdapter = new MoviesListAdapter(mMovieList, this, getContext());
        moviesListRv.setAdapter(mAdapter);
    }

    @Override
    public void showMovies() {

    }

    @Override
    public void loadingStart() {
        Snackbar.make(moviesListRv, "Loading movies", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadingFail(String error) {
        Snackbar.make(moviesListRv, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder = null;
    }
}
