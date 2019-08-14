package com.project.mobile.movie_db_training.detail;

import com.project.mobile.movie_db_training.data.model.Movie;
import com.project.mobile.movie_db_training.data.model.Review;
import com.project.mobile.movie_db_training.data.model.Video;

import java.util.List;

public interface MovieDetailContract {
    interface View {
        void showDetail(Movie movie);

        void showTrailers(List<Video> videos);

        void showReviews(List<Review> reviews);

        void showFavorite();

        void showUnFavorite();

        void loadingStart();

        void loadingFail(String error);
    }

    interface Presenter {

        void fetchLatestMovie();

        void fetchVideos(String movieId);

        void fetchReviews(String movieId);

        void loadMoreReviews(String movieId);

        void onFabFavoriteClick(Movie movie);

        void setView(View view);

        void destroy();
    }
}
