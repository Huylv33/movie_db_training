package com.project.mobile.movie_db_training.list;


import android.content.Context;
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
import com.project.mobile.movie_db_training.data.model.Genre;
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
    private String mListType;
    private Genre mGenre;
    private Callback mCallback;

    public MoviesListFragment() {
        // Required empty public constructor
    }

    public static MoviesListFragment newInstance(String listType) {
        Bundle args = new Bundle();
        args.putString(Constants.LIST_TYPE, listType);
        MoviesListFragment fragment = new MoviesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static MoviesListFragment newInstance(Genre genre) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.GENRE, genre);
        MoviesListFragment fragment = new MoviesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        initLayout();
        if (getArguments() != null) {
            if (getArguments().containsKey(Constants.LIST_TYPE)) {
                mListType = getArguments().getString(Constants.LIST_TYPE);
            }
            else if (getArguments().containsKey(Constants.GENRE)){
                mGenre = getArguments().getParcelable(Constants.GENRE);
            }
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new MoviesListPresenterImpl();
        mPresenter.setView(this);
        if (mListType != null) mPresenter.fetchMovies(mListType);
        else if (mGenre != null) mPresenter.fetchMoviesByGenre(mGenre.getId());
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
                    if (mListType != null) mPresenter.loadMore(mListType);
                    else  if (mGenre != null) mPresenter.loadMoreByGenre(mGenre.getId());
                }
            }
        });
        mMoviesListRv.setLayoutManager(layoutManager);
        mAdapter = new MoviesListAdapter(mMovies, this);
        mMoviesListRv.setAdapter(mAdapter);
    }

    @Override
    public void onMovieClick(Movie movie) {
        mCallback.onMovieClick(movie);
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

    interface Callback {
        void onMovieClick(Movie movie);
    }
}