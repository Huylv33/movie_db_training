package com.project.mobile.movie_db_training.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResponse {
    @SerializedName("results")
    private List<Video> mVideos;

    public List<Video> getVideos() {
        return mVideos;
    }
}
