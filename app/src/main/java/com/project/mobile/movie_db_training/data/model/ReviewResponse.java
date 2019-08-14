package com.project.mobile.movie_db_training.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    @SerializedName("results")
    private List<Review> mReviews;
    @SerializedName("total_pages")
    private int totalPages;
    public List<Review> getReviews() {
        return mReviews;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
