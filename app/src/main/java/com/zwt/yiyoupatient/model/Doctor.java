package com.zwt.yiyoupatient.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 夏夜晚凤 on 2017/3/15.
 */
public class Doctor {
    int imageId;
    String name;
    String doctor;
    String score;

    public Doctor(int imageId, String name, String doctor, String score) {
        this.imageId = imageId;
        this.name = name;
        this.doctor = doctor;
        this.score = score;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getScore() {
        return score;
    }
}
