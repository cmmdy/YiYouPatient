package com.zwt.yiyoupatient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.activity.HomeActivity;
import com.zwt.yiyoupatient.model.CollectionText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 夏夜晚凤 on 2017/3/9.
 */

public class collectionAdapter extends ArrayAdapter<CollectionText> {
    private Context mContext;

    private int resourceId;

    public collectionAdapter(Context context, int ResourceId, List<CollectionText> list) {
        super(context, ResourceId, list);
        resourceId = ResourceId;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CollectionText ct = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                    false);
            //动态修改高度
            RelativeLayout rlListView = (RelativeLayout) view.findViewById(R.id.rlListView);
            rlListView.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 6);
            viewHolder = new ViewHolder(view);
            viewHolder.iv.getLayoutParams().width = (int) (HomeActivity.gethw.getHEIGHT() / 6);
            TextPaint tp = viewHolder.title.getPaint();
            tp.setFakeBoldText(true);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext).load(ct.getImageId()).into(viewHolder.iv);
        viewHolder.title.setText(ct.getTextTitle());
        viewHolder.introduction.setText(ct.getTextIntroduction());
        viewHolder.time.setText(ct.getTime());
        return view;
    }


    class ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.introduction)
        TextView introduction;
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
