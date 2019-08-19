package com.project.mobile.movie_db_training.genre;


import com.project.mobile.movie_db_training.BuildConfig;
import com.project.mobile.movie_db_training.data.model.Genre;
import com.project.mobile.movie_db_training.data.model.GenresResponse;
import com.project.mobile.movie_db_training.network.NetworkModule;
import com.project.mobile.movie_db_training.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenresListPresenterImpl implements GenresListContract.Presenter {
    private GenresListContract.View mGenresListView;

    @Override
    public void setView(GenresListContract.View view) {
        this.mGenresListView = view;
    }

    @Override
    public void destroy() {
        mGenresListView = null;
    }

    @Override
    public void fetchGenres() {
        mGenresListView.showLoading(Constants.LOADING_START);
        NetworkModule.getTMDbService()
                .getGenreList(BuildConfig.TMDB_API_KEY)
                .enqueue(new Callback<GenresResponse>() {
                    @Override
                    public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            onFetchSuccess(response.body().getGenres());
                        } else {
                            onFetchFail(Constants.RESPONSE_ERROR);
                        }
                    }

                    @Override
                    public void onFailure(Call<GenresResponse> call, Throwable t) {
                        onFetchFail(t.getMessage());
                    }
                });
    }

    private void onFetchSuccess(List<Genre> genres) {
        mGenresListView.showGenres(genres);
    }

    private void onFetchFail(String message) {
        mGenresListView.showLoading(message);
    }
}
