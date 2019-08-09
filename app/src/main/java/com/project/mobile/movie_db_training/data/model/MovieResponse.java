package com.project.mobile.movie_db_training.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    private List<Movie> mMovies;
    @SerializedName("total_pages")
    private int totalPages;

    public List<Movie> getMovies() {
        return mMovies;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
