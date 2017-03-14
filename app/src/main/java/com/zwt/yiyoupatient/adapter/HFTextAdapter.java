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
import com.zwt.yiyoupatient.model.HFText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 夏夜晚凤 on 2017/3/2.
 */

public class HFTextAdapter extends ArrayAdapter<HFText> {

    private Context mContext;

    private int resourceId;

    public HFTextAdapter(Context context, int FFTResourceId, List<HFText> list) {
        super(context, FFTResourceId, list);
        resourceId = FFTResourceId;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HFText fft = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                    false);
            //动态修改高度
            RelativeLayout rlListView = (RelativeLayout) view.findViewById(R.id.rlListView);
            rlListView.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 6);
            viewHolder = new ViewHolder(view);
            viewHolder.fftImage.getLayoutParams().width = (int) (HomeActivity.gethw.getHEIGHT() / 6);
            TextPaint tp = viewHolder.fftTitleText.getPaint();
            tp.setFakeBoldText(true);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext).load(fft.getImageId()).into(viewHolder.fftImage);
        viewHolder.fftTitleText.setText(fft.getTextTitle());
        viewHolder.fftIntroductionText.setText(fft.getTextIntroduction());
        viewHolder.fftTime.setText(fft.getTime());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.first_fragment_text_image)
        ImageView fftImage;
        @BindView(R.id.first_fragment_text_title)
        TextView fftTitleText;
        @BindView(R.id.first_fragment_text_introduction)
        TextView fftIntroductionText;
        @BindView(R.id.first_fragment_time)
        TextView fftTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
