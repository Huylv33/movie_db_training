package com.project.mobile.movie_db_training.data.model;

import com.google.gson.annotations.SerializedName;

public class Cast {
    @SerializedName("cast_id")
    private String mCastId;
    @SerializedName("name")
    private String mName;
    @SerializedName("profile_path")
    private String mProfilePath;
    @SerializedName("id")
    private String mId;

    public String getCastId() {
        return mCastId;
    }

    public void setCastId(String castId) {
        mCastId = castId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
