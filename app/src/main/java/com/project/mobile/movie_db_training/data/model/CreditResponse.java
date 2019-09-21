package com.project.mobile.movie_db_training.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditResponse {
    @SerializedName("cast")
    private List<Credit> mCredits;

    public List<Credit> getCredits() {
        return mCredits;
    }
}
