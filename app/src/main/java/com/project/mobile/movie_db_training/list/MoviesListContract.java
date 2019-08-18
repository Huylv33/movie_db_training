package com.project.mobile.movie_db_training.list;

import com.project.mobile.movie_db_training.data.model.Movie;

import java.util.List;

public interface MoviesListContract {
    interface View {
        void showMovies(List<Movie> movies);

        void showLoading(String message);
    }

    interface Presenter {

        void fetchMovies(String option);

        void loadMore(String option);

        void setView(View view);

        void destroy();
    }
}
