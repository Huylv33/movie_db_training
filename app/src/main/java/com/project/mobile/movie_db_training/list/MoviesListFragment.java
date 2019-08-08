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
import com.project.mobile.movie_db_training.utils.Constants;

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
    RecyclerView mMoviesListRv;
    private static final String TAG = MoviesListFragment.class.getSimpleName();
    private MoviesListAdapter mAdapter;
    private Unbinder mUnbinder;
    private List<Movie> mMovies = new ArrayList<>();
    private MoviesListPresenterImpl mPresenter;
    private String mListType = "";

    public MoviesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        initLayout();
        if (getArguments() != null) {
            mListType = getArguments().getString(Constants.LIST_TYPE);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new MoviesListPresenterImpl();
        mPresenter.setView(this);
        mPresenter.fetchMovies(mListType);
    }

    private void initLayout() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mMoviesListRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (totalItemCount - 1 == lastVisibleItem) {
                    mPresenter.loadMore(mListType);
                }
            }
        });
        mMoviesListRv.setLayoutManager(layoutManager);
        mAdapter = new MoviesListAdapter(mMovies, getContext());
        mMoviesListRv.setAdapter(mAdapter);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        this.mMovies.addAll(movies);
        mMoviesListRv.setVisibility(View.VISIBLE);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadingStart() {
        Snackbar.make(mMoviesListRv, "Loading movies", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadingFail(String error) {
        Snackbar.make(mMoviesListRv, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder = null;
        mPresenter.destroy();
    }

}