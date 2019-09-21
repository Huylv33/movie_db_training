package com.project.mobile.movie_db_training.search;

import com.project.mobile.movie_db_training.data.model.Movie;

import java.util.List;

public interface SearchContract {
    interface View {
        void showMovies(List<Movie> movies);

        void showLoading(String message);
    }

    interface Presenter {

        void searchMovies(String query);

        void loadMore();

        void setView(SearchContract.View view);

        void destroy();
    }
}
