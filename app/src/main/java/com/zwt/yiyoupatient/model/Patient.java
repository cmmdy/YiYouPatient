package com.zwt.yiyoupatient.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 夏夜晚凤 on 2017/3/2.
 */

public class Patient implements Parcelable {
    private int imageId;

    private String name;

    private String orderTime;

    private String age;

    private String sex;

    private String situation;

    public Patient() {
    }

    public Patient(int imageId, String name, String orderTime, String age, String sex, String situation) {
        this.imageId = imageId;
        this.name = name;
        this.orderTime = orderTime;
        this.age = age;
        this.sex = sex;
        this.situation = situation;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getSituation() {
        return situation;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    protected Patient(Parcel in) {
        imageId = in.readInt();
        name = in.readString();
        orderTime = in.readString();
        age = in.readString();
        sex = in.readString();
        situation = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(name);
        dest.writeString(orderTime);
        dest.writeString(age);
        dest.writeString(sex);
        dest.writeString(situation);
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

}
