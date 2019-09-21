package com.project.mobile.movie_db_training.data.model;

import com.google.gson.annotations.SerializedName;

public class Credit {
    @SerializedName("id")
    private String mId;
    @SerializedName("character")
    private String mCharacter;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("poster_path")
    private String mPosterPath;
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public void setCharacter(String character) {
        mCharacter = character;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }
}
