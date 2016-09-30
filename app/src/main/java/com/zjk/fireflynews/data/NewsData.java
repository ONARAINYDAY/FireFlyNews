package com.zjk.fireflynews.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by FireFly on 2016/9/8.
 */
public class NewsData implements Parcelable {
    private String name;
    private String id;

    public NewsData(){}

    protected NewsData(Parcel in) {
        name = in.readString();
        id = in.readString();
    }

    public static final Creator<NewsData> CREATOR = new Creator<NewsData>() {
        @Override
        public NewsData createFromParcel(Parcel in) {
            return new NewsData(in);
        }

        @Override
        public NewsData[] newArray(int size) {
            return new NewsData[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(id);
    }
}
