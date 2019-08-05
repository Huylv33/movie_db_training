package com.project.mobile.movie_db_training.genre;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.data.model.Genre;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenresListFragment extends Fragment implements GenresListContract.View {
    private static final String TAG = GenresListFragment.class.getSimpleName();
    @BindView(R.id.rv_genres_list)
    RecyclerView genresListRv;
    private RecyclerView.Adapter mAdapter;
    private List<Genre> mGenreList = new ArrayList<>();
    private GenresListContract.Presenter mGenreListPresenter;
    private Unbinder unbinder;

    public GenresListFragment() {
        // Required empty public constructor
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        android.view.View view = inflater.inflate(R.layout.fragment_genres_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initLayout();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGenreListPresenter = new GenresListPresenterImpl();
        mGenreListPresenter.setView(this);
        mGenreListPresenter.fetchGenres();
    }

    private void initLayout() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        genresListRv.setLayoutManager(layoutManager);
        mAdapter = new GenresListAdapter(mGenreList, this);
        genresListRv.setAdapter(mAdapter);
    }

    @Override
    public void showGenres(List<Genre> genresList) {
        this.mGenreList.clear();
        this.mGenreList.addAll(genresList);
        genresListRv.setVisibility(android.view.View.VISIBLE);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadingStart() {
        Snackbar.make(genresListRv, "Loading genres", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadingFail(String error) {
        Snackbar.make(genresListRv, error, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder = null;
        mGenreListPresenter.destroy();
    }
}
