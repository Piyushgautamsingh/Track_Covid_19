package com.example.track_covid_19.ui.india;

import android.os.Parcel;
import android.os.Parcelable;

public class india_state implements Parcelable {
    String mindia_state, mCases, mDeaths, mRecovered;
    public india_state(String mindia_state, String mCases,String mDeaths, String mRecovered) {
        this.mindia_state = mindia_state;
        this.mCases = mCases;
        this.mDeaths = mDeaths;
        this.mRecovered = mRecovered;
     }

    public String getmindia_state() {
        return mindia_state;
    }

    public String getmCases() {
        return mCases;
    }

    public String getmDeaths() {
        return mDeaths;
    }

    public String getmRecovered() {
        return mRecovered;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mindia_state);
        dest.writeString(this.mCases);
        dest.writeString(this.mDeaths);
        dest.writeString(this.mRecovered);
    }

    protected india_state(Parcel in) {
        this.mindia_state = in.readString();
        this.mCases = in.readString();
        this.mDeaths = in.readString();
        this.mRecovered = in.readString();
    }

    public static final Creator<com.example.track_covid_19.ui.india.india_state> CREATOR = new Creator<com.example.track_covid_19.ui.india.india_state>() {
        @Override
        public com.example.track_covid_19.ui.india.india_state createFromParcel(Parcel source) {
            return new com.example.track_covid_19.ui.india.india_state(source);
        }

        @Override
        public com.example.track_covid_19.ui.india.india_state[] newArray(int size) {
            return new com.example.track_covid_19.ui.india.india_state[size];
        }
    };
}