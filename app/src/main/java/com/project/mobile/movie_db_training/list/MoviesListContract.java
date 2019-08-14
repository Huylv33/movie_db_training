package com.project.mobile.movie_db_training.list;

import com.project.mobile.movie_db_training.data.model.Movie;

import java.util.List;

public interface MoviesListContract {
    interface View {
        void onMovieClick(Movie movie);

        void showMovies(List<Movie> movies);

        void loadingStart();

        void loadingFail(String error);
    }

    interface Presenter {
        void fetchMovies(String listType);

        void fetchMoviesByGenre(String genreId);

        void loadMore(String listType);

        void loadMoreByGenre(String genreId);

        void setView(View view);

        void destroy();
    }
}
