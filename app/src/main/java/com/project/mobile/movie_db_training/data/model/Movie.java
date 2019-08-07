package com.project.mobile.movie_db_training.data.model;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("id")
    private int mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("vote_average")
    private String mVoteAverage;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("release_date")
    private String mReleaseDate;

    public Movie(String title, String voteAverage, String releaseDate) {
        mTitle = title;
        mVoteAverage = voteAverage;
        mReleaseDate = releaseDate;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getPosterPath() {
        return mBackdropPath;
    }

    public void setPosterPath(String posterPath) {
        mBackdropPath = posterPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }
}