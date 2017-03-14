package com.zwt.yiyoupatient.model;

/**
 * Created by 夏夜晚凤 on 2017/3/12.
 */

public class MyNotifaction {
    String title;
    String text;
    String time;

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public MyNotifaction(String title, String text, String time) {

        this.title = title;
        this.text = text;
        this.time = time;
    }
}
