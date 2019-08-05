package com.project.mobile.movie_db_training.network;


import com.project.mobile.movie_db_training.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {
    private static Retrofit retrofit;

    public static TMDbService getTMDbService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.TMDB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(TMDbService.class);
    }
}
