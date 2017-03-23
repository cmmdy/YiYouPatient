package com.zwt.yiyoupatient.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Transformation;

import net.qiujuer.genius.blur.StackBlur;

/**
 * Created by 夏夜晚凤 on 2017/3/7.
 */

public class MyBitmap implements Transformation {

    Activity activity;

    public MyBitmap(Activity activity) {
        this.activity = activity;
    }

    public Drawable vague(int id, int math){
        Bitmap bmp= BitmapFactory.decodeResource(activity.getResources(), id);
        Bitmap newBitmap = StackBlur.blurNatively(transform(bmp), math, false);
        Drawable drawable =new BitmapDrawable(newBitmap);
        return drawable;
    }



    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override
    public String key() {
        return null;
    }
}
