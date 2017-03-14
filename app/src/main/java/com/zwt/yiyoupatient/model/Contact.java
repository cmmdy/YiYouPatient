package com.zwt.yiyoupatient.model;

/**
 * Created by 夏夜晚凤 on 2017/3/12.
 */

public class Contact {
    String name;
    String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
