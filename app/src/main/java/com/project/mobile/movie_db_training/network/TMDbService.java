package com.project.mobile.movie_db_training.network;

import com.project.mobile.movie_db_training.data.model.GenresResponse;
import com.project.mobile.movie_db_training.data.model.Movie;
import com.project.mobile.movie_db_training.data.model.MovieResponse;
import com.project.mobile.movie_db_training.data.model.ReviewResponse;
import com.project.mobile.movie_db_training.data.model.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDbService {
    @GET("genre/movie/list?language=en-US")
    Call<GenresResponse> getGenreList(@Query("api_key") String key);

    @GET("movie/{list_type}?language=en-US")
    Call<MovieResponse> getMovieList(@Path("list_type") String listType,
                                     @Query("api_key") String key,
                                     @Query("page") int page);

    @GET("movie/{movie_id}/reviews")
    Call<ReviewResponse> getReviews(@Path("movie_id") String id,
                                    @Query("api_key") String key,
                                    @Query("page") int page);

    @GET("movie/{movie_id}/videos?language=en-US")
    Call<VideoResponse> getVideos(@Path("movie_id") String id, @Query("api_key") String key);

    @GET("movie/latest?language=en-US")
    Call<Movie> getLatestMovie(@Query("api_key") String key);

    @GET("discover/movie")
    Call<MovieResponse> getMoviesByGenre(@Query("api_key") String key,
                                         @Query("with_genres") String genreId,
                                         @Query("page") int page);
}
