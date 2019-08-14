package com.project.mobile.movie_db_training.genre;

import com.project.mobile.movie_db_training.data.model.Genre;

import java.util.List;


public interface GenresListContract {
    interface View {
        void onGenreClick(Genre genre);

        void showGenres(List<Genre> genresList);

        void loadingStart();

        void loadingFail(String error);
    }

    interface Presenter {
        void fetchGenres();

        void setView(View view);

        void destroy();
    }
}
