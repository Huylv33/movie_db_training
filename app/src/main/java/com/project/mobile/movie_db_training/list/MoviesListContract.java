package com.project.mobile.movie_db_training.list;

public interface MoviesListContract {
    interface View {
        void showMovies();

        void loadingStart();

        void loadingFail(String error);
    }

    interface Presenter {
        void fetchMovies();

        void setView(View view);

        void destroy();
    }

}
