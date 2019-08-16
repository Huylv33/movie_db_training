package com.project.mobile.movie_db_training.detail;

import com.project.mobile.movie_db_training.BuildConfig;
import com.project.mobile.movie_db_training.data.model.Video;
import com.project.mobile.movie_db_training.data.model.VideoResponse;
import com.project.mobile.movie_db_training.network.NetworkModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailPresenterImpl implements MovieDetailContract.Presenter {
    private MovieDetailContract.View mView;

    @Override
    public void fetchVideos(String movieId) {
        NetworkModule.getTMDbService().getVideos(movieId, BuildConfig.TMDB_API_KEY)
                .enqueue(new Callback<VideoResponse>() {
                    @Override
                    public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            onFetchVideosSuccess(response.body().getVideos());
                        }
                    }

                    @Override
                    public void onFailure(Call<VideoResponse> call, Throwable t) {
                        onFetchVideoFail(t.getMessage());
                    }
                });
    }

    private void onFetchVideosSuccess(List<Video> videos) {
        if (videos.size() > 0) mView.showVideos(videos);

    }
    private void onFetchVideoFail(String message) {
        mView.loadingFail(message);
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
