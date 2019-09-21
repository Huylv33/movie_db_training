package com.project.mobile.movie_db_training.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.project.mobile.movie_db_training.R;
import com.project.mobile.movie_db_training.data.model.Movie;
import com.project.mobile.movie_db_training.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    public MoviesFragment() {
        // Required empty public constructor
    }

    public static MoviesFragment newInstance(String listType) {
        Bundle args = new Bundle();
        args.putString(Constants.LIST_TYPE, listType);
        MoviesFragment fragment = new MoviesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    public interface Callback {
        void onMovieClick(Movie movie);
    }
}
