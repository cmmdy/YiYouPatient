package com.zwt.yiyoupatient.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by 夏夜晚凤 on 2017/3/6.
 */

public class getHW {
    private double WIDTH;

    private double HEIGHT;

    public getHW(Activity activity) {
        //获得屏幕大小
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        WIDTH = dm.widthPixels;
        HEIGHT = dm.heightPixels;
    }

    public double getWIDTH() {
        return WIDTH;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }
}
