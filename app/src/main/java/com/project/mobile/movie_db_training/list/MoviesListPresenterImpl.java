package com.project.mobile.movie_db_training.list;

import com.project.mobile.movie_db_training.BuildConfig;
import com.project.mobile.movie_db_training.data.model.Movie;
import com.project.mobile.movie_db_training.data.model.MovieResponse;
import com.project.mobile.movie_db_training.network.NetworkModule;

import java.util.List;

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
    public void fetchMovies(String listType) {
        if (!mIsLoading) {
            mView.loadingStart();
            mIsLoading = true;
            NetworkModule.getTMDbService()
                    .getMovieList(listType, BuildConfig.TMDB_API_KEY, mPage)
                    .enqueue(new Callback<MovieResponse>() {
                        @Override
                        public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                onFetchSuccess(response.body().getMovies());
                                mTotalPages = response.body().getTotalPages();
                            }
                        }

                        @Override
                        public void onFailure(Call<MovieResponse> call, Throwable t) {
                            mView.loadingFail(t.getMessage());
                        }
                    });
        }
    }

    private void onFetchSuccess(List<Movie> movies) {
        mIsLoading = false;
        mView.showMovies(movies);
    }

    @Override
    public void loadMore(String listType) {
        if (!isLoading() && mPage < getTotalPages()) {
            mPage++;
            fetchMovies(listType);
        }
    }
}