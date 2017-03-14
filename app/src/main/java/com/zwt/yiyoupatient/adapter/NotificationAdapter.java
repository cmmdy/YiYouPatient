package com.zwt.yiyoupatient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.activity.HomeActivity;
import com.zwt.yiyoupatient.model.MyNotifaction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 夏夜晚凤 on 2017/3/2.
 */

public class NotificationAdapter extends ArrayAdapter<MyNotifaction> {

    private Context mContext;

    private int resourceId;

    public NotificationAdapter(Context context, int FFTResourceId, List<MyNotifaction> list) {
        super(context, FFTResourceId, list);
        resourceId = FFTResourceId;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyNotifaction myNotifaction = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                    false);
            //动态修改高度
            RelativeLayout rlListView = (RelativeLayout) view.findViewById(R.id.rlListView);
            rlListView.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 6);
            viewHolder = new ViewHolder(view);
            TextPaint tp = viewHolder.title.getPaint();
            tp.setFakeBoldText(true);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(myNotifaction.getTitle());
        viewHolder.text.setText(myNotifaction.getText());
        viewHolder.time.setText(myNotifaction.getTime());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
