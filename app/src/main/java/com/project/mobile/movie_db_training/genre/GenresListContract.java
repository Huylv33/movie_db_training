package com.project.mobile.movie_db_training.genre;

import com.project.mobile.movie_db_training.data.model.Genre;

import java.util.List;


public interface GenresListContract {
    interface View {
        void showGenres(List<Genre> genresList);

        void showLoading(String message);
    }

    interface Presenter {
        void fetchGenres();

        void setView(View view);

        void destroy();
    }
}
