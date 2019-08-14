package com.project.mobile.movie_db_training.detail;

import android.content.Context;
import android.util.Log;

import com.project.mobile.movie_db_training.BuildConfig;
import com.project.mobile.movie_db_training.data.local.MovieDatabase;
import com.project.mobile.movie_db_training.data.model.Movie;
import com.project.mobile.movie_db_training.data.model.Review;
import com.project.mobile.movie_db_training.data.model.ReviewResponse;
import com.project.mobile.movie_db_training.data.model.Video;
import com.project.mobile.movie_db_training.data.model.VideoResponse;
import com.project.mobile.movie_db_training.network.NetworkModule;
import com.project.mobile.movie_db_training.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailPresenterImpl implements MovieDetailContract.Presenter {
    private MovieDetailContract.View mView;
    private int mReviewPage;
    private boolean mIsLoading;
    private int mTotalReviewPage;
    private MovieDatabase mMovieDatabase;

    public MovieDetailPresenterImpl(Context context) {
        mReviewPage = 1;
        mIsLoading = false;
        mMovieDatabase = MovieDatabase.getInstance(context);
    }

    @Override
    public void fetchLatestMovie() {
        NetworkModule.getTMDbService().getLatestMovie(BuildConfig.TMDB_API_KEY)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            onFetchLatestMovieSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        mView.loadingFail(t.getMessage());
                    }
                });
    }
    private void onFetchLatestMovieSuccess(Movie movie) {
        mView.showDetail(movie);
        fetchVideos(movie.getId());
        fetchReviews(movie.getId());
    }
    @Override
    public void fetchVideos(String id) {
        NetworkModule.getTMDbService().getVideos(id, BuildConfig.TMDB_API_KEY)
                .enqueue(new Callback<VideoResponse>() {
                    @Override
                    public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            onFetchVideosSuccess(response.body().getVideos());
                        }
                    }

                    @Override
                    public void onFailure(Call<VideoResponse> call, Throwable t) {

                    }
                });
    }

    private void onFetchVideosSuccess(List<Video> videos) {
        if (videos != null) {
            if (videos.size() > 0)
                mView.showTrailers(videos);
        }
    }
  
    @Override
    public void fetchReviews(String id) {
        if (!mIsLoading) {
            if (mReviewPage != 1) mView.loadingStart();
            mIsLoading = true;
            NetworkModule.getTMDbService().getReviews(id, BuildConfig.TMDB_API_KEY, mReviewPage)
                    .enqueue(new Callback<ReviewResponse>() {
                        @Override
                        public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                onFetchReviewsSuccess(response.body().getReviews());
                                mTotalReviewPage = response.body().getTotalPages();
                            }
                        }

                        @Override
                        public void onFailure(Call<ReviewResponse> call, Throwable t) {
                            mView.loadingFail(t.getMessage());
                        }
                    });
        }

    }

    private void onFetchReviewsSuccess(List<Review> reviews) {
        if (reviews != null) {
            if (reviews.size() > 0) {
                mView.showReviews(reviews);
            }
        }
    }

    @Override
    public void loadMoreReviews(String movieId) {
        if (!mIsLoading && mReviewPage < mTotalReviewPage) {
            mReviewPage++;
            fetchReviews(movieId);
        }
    }

    @Override
    public void onFabFavoriteClick(Movie movie) {
        Log.d("ahihi","o day ");
        if (movie.getFavorite() == Constants.FAVORITE_NON_ACTIVE) {
            mView.showFavorite();
        } else {
            mView.showUnFavorite();
        }
    }

    @Override
    public void setView(MovieDetailContract.View view) {
        mView = view;
    }

    @Override
    public void destroy() {
        mView = null;
    }
}
