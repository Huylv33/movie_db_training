package com.project.mobile.movie_db_training.network;

import com.project.mobile.movie_db_training.data.model.GenresResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbService {
    @GET("genre/movie/list?language=en-US")
    Call<GenresResponse> getGenreList(@Query("api_key") String key);

}
