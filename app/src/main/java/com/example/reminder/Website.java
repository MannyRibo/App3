package com.example.reminder;

import android.os.Parcel;
import android.os.Parcelable;

public class Website implements Parcelable {

    private String URL;
    private String mWebsiteName;

    public Website(String mURL, String mWebsiteName) {
        this.URL = mURL;
        this.mWebsiteName = mWebsiteName;
    }

    public String getmWebsiteName() {
        return mWebsiteName;
    }

    public void setmWebsiteName(String mWebsiteName) {
        this.mWebsiteName = mWebsiteName;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(URL);
        dest.writeString(mWebsiteName);
    }

    protected Website(Parcel in) {
        URL = in.readString();
        mWebsiteName = in.readString();
    }

    public static final Parcelable.Creator<Website> CREATOR = new Parcelable.Creator<Website>() {
        @Override
        public Website createFromParcel(Parcel in) {
            return new Website(in);
        }

        @Override
        public Website[] newArray(int size) {
            return new Website[size];
        }
    };

    @Override
    public String toString() {
        return mWebsiteName;
    }
}
