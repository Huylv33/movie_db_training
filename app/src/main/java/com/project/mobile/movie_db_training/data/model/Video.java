package com.project.mobile.movie_db_training.data.model;

import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String mName;
    @SerializedName("site")
    private String mSite;
    @SerializedName("key")
    private String mKey;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return mKey;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getSite() {
        return mSite;
    }

    public void setSite(String site) {
        mSite = site;
    }
}
