package com.project.mobile.movie_db_training.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Genre implements Parcelable {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;

    protected Genre(Parcel i) {
        this.mId = i.readString();
        this.mName = i.readString();
    }

    public static Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int i) {
            return new Genre[i];
        }
    };

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mName);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
