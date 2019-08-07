package com.project.mobile.movie_db_training.list;

public interface MoviesListContract {
    interface View {

        void loadingStart();

        void loadingFail(String error);
    }

    interface Presenter {

        void setView(View view);

        void destroy();
    }
}
