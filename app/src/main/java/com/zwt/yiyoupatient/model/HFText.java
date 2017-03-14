package com.zwt.yiyoupatient.model;

/**
 * Created by 夏夜晚凤 on 2017/3/2.
 */

public class HFText {

    private int imageId;

    private String textTitle;

    private String textIntroduction;

    private String time;

    public HFText(int imageId, String textTitle, String textIntroduction, String time) {
        this.imageId = imageId;
        this.textTitle = textTitle;
        this.textIntroduction = textIntroduction;
        this.time = time;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public String getTextIntroduction() {
        return textIntroduction;
    }

    public String getTime() {
        return time;
    }
}
