package com.zwt.yiyoupatient.model;

/**
 * Created by 夏夜晚凤 on 2017/3/5.
 */

public class MyMessage {
    private int imageId;

    private String text;

    private int backImageId;

    public MyMessage(int imageId, String text, int backImageId) {
        this.imageId = imageId;
        this.text = text;
        this.backImageId = backImageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getText() {
        return text;
    }

    public int getBackImageId() {
        return backImageId;
    }
}
