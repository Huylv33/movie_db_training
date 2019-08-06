package com.project.mobile.movie_db_training.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenresResponse {
    @SerializedName("genres")
    private List<Genre> mGenres;

    public GenresResponse(List<Genre> genres) {
        this.mGenres = genres;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }
}
