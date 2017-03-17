package com.zwt.yiyoupatient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.model.Doctor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 夏夜晚凤 on 2017/3/15.
 */

public class OrderAdapter extends ArrayAdapter<Doctor> {

    private int mResource;
    private Context mContext;

    public OrderAdapter(Context context, int resource, List<Doctor> doctors) {
        super(context, resource, doctors);
        this.mResource = resource;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Doctor doctor = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(mResource,
                    parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext).load(doctor.getImageId()).into(viewHolder.image);
        viewHolder.doctor.setText(doctor.getDoctor());
        viewHolder.name.setText(doctor.getName());
        viewHolder.score.setText(doctor.getScore());
        return view;

    }

    static class ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.doctor)
        TextView doctor;
        @BindView(R.id.score)
        TextView score;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
