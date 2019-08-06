package com.project.mobile.movie_db_training.data.model;

import com.google.gson.annotations.SerializedName;


public class Genre {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;

    public Genre(String id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

}
