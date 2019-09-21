package com.project.mobile.movie_db_training.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieCredits {
    @SerializedName("cast")
    private List<Credit> mCredits;

    public List<Credit> getCredits() {
        return mCredits;
    }

    public void setCredits(List<Credit> credits) {
        mCredits = credits;
    }
}
