package com.project.mobile.movie_db_training.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies", indices = {@Index(value = {"id"}, unique = true)})
public class Movie implements Parcelable {
    @ColumnInfo(name = "id")
    @PrimaryKey
    @SerializedName("id")
    @NonNull
    private String mId;
    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String mTitle;
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private String mVoteAverage;
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String mReleaseDate;
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String mOverview;
    @ColumnInfo(name = "mFavorite")
    private int mFavorite;
    @ColumnInfo(name = "mWatchList")
    private int mWatchList;

    public Movie() {
    }

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

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public int getFavorite() {
        return mFavorite;
    }

    public void setFavorite(int favorite) {
        mFavorite = favorite;
    }

    public int getWatchList() {
        return mWatchList;
    }

    public void setWatchList(int watchList) {
        mWatchList = watchList;
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