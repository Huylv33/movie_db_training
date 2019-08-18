package com.project.mobile.movie_db_training.data.local;

import androidx.annotation.NonNull;

import com.project.mobile.movie_db_training.data.model.Movie;
import com.project.mobile.movie_db_training.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;

public class MovieLocalDataSource {
    private static volatile MovieLocalDataSource INSTANCE;
    private MovieDAO mMovieDao;
    private AppExecutors mAppExecutors;

    private MovieLocalDataSource(@NonNull MovieDAO movieDao, @NonNull AppExecutors appExecutors) {
        mMovieDao = movieDao;
        mAppExecutors = appExecutors;
    }
    public static MovieLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull MovieDAO movieDAO) {
        if (INSTANCE == null) {
            synchronized (MovieLocalDataSource.class) {
                INSTANCE = new MovieLocalDataSource(movieDAO,appExecutors);
            }
        }
        return INSTANCE;
    }
    public void getFavorites(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                List<Movie> movies = mMovieDao.getFavorites();
            }
        };
        mAppExecutors.mainThread().execute(runnable);
    }
}
