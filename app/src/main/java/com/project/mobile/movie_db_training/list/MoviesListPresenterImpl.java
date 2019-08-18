package com.project.mobile.movie_db_training.list;

import androidx.annotation.NonNull;

import com.project.mobile.movie_db_training.BuildConfig;
import com.project.mobile.movie_db_training.data.model.MovieResponse;
import com.project.mobile.movie_db_training.network.NetworkModule;
import com.project.mobile.movie_db_training.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesListPresenterImpl implements MoviesListContract.Presenter {
    private static final String TAG = MoviesListPresenterImpl.class.getSimpleName();
    private MoviesListContract.View mView;
    private boolean mIsLoading = false;
    private int mTotalPages;
    private int mPage = 1;

    public int getTotalPages() {
        return mTotalPages;
    }

    public boolean isLoading() {
        return mIsLoading;
    }

    @Override
    public void setView(MoviesListContract.View view) {
        mView = view;
    }

    @Override
    public void destroy() {
        mView = null;
    }

    @Override
    public void fetchMovies(String option) {
        if (option.equals(Constants.NOW_PLAYING) ||
                option.equals(Constants.POPULAR) ||
                option.equals(Constants.TOP_RATED) ||
                option.equals(Constants.UPCOMING)) {
            getMoviesByListType(option);
        } else {
            getMoviesByGenre(option);
        }
    }

    //get movies: now coming, popular, top rated, upcoming
    private void getMoviesByListType(String listType) {
        if (mIsLoading) return;
        mView.showLoading(Constants.LOADING_START);
        mIsLoading = true;
        NetworkModule.getTMDbService()
                .getMovieList(listType, BuildConfig.TMDB_API_KEY, mPage)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            onFetchSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        onFetchFail(t.getMessage());
                    }
                });

    }

    //get movies by genre. Eg: Action, Comedy,...
    private void getMoviesByGenre(String genreId) {
        if (mIsLoading) return;
        mView.showLoading(Constants.LOADING_START);
        mIsLoading = true;
        NetworkModule.getTMDbService()
                .getMoviesByGenre(BuildConfig.TMDB_API_KEY, genreId, mPage)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            onFetchSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        onFetchFail(t.getMessage());
                    }
                });

    }

    private void onFetchSuccess(@NonNull MovieResponse movieResponse) {
        mIsLoading = false;
        mTotalPages = movieResponse.getTotalPages();
        mView.showMovies(movieResponse.getMovies());
    }

    private void onFetchFail(String message) {
        mView.showLoading(message);
    }

    @Override
    public void loadMore(String option) {
        if (isLoading() || mPage > getTotalPages()) return;
        mPage++;
        fetchMovies(option);
    }
}