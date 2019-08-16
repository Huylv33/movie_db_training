package com.project.mobile.movie_db_training.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    @SerializedName("id")
    private String mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("vote_average")
    private String mVoteAverage;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("overview")
    private String mOverview;

    protected Movie(Parcel in) {
        this.mId = in.readString();
        this.mTitle = in.readString();
        this.mVoteAverage = in.readString();
        this.mBackdropPath = in.readString();
        this.mReleaseDate = in.readString();
        this.mOverview = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }


    public String getVoteAverage() {
        return mVoteAverage;
    }


    public String getBackdropPath() {
        return mBackdropPath;
    }


    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getOverview() {
        return mOverview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mTitle);
        parcel.writeString(mVoteAverage);
        parcel.writeString(mBackdropPath);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mOverview);
    }
}