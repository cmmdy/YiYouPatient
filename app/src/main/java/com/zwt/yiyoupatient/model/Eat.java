package com.zwt.yiyoupatient.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 夏夜晚凤 on 2017/3/20.
 */

public class Eat implements Parcelable {
    String time;
    String content;

    public Eat(String time, String content) {
        this.time = time;
        this.content = content;
    }

    public Eat() {
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.time);
        dest.writeString(this.content);
    }

    protected Eat(Parcel in) {
        this.time = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<Eat> CREATOR = new Parcelable.Creator<Eat>() {
        @Override
        public Eat createFromParcel(Parcel source) {
            return new Eat(source);
        }

        @Override
        public Eat[] newArray(int size) {
            return new Eat[size];
        }
    };
}
