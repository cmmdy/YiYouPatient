package com.zwt.yiyoupatient.expandtab;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zwt.yiyoupatient.R;

import java.util.List;

import butterknife.internal.Utils;

/**
 * Created by 夏夜晚凤 on 2017/3/17.
 */

public class AdapterForPCA extends BaseAdapter {
    private List<String> list = null;
    private Context context;
    private int selectPosition;

    public AdapterForPCA(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setSelectPosition(int position) {
        this.selectPosition = position;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_listview_province_city_area, null);
            viewHolder = new ViewHolder();
            viewHolder.tvPCA = (TextView) view.findViewById(R.id.tvPCA);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvPCA.setText(list.get(i));
        viewHolder.tvPCA.setGravity(Gravity.CENTER);
        //为了让点击的item显示不同的颜色，必须在item点击后 重新 notifyDataSetChange
        if (selectPosition == i) {
            viewHolder.tvPCA.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
            view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
            viewHolder.tvPCA.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            viewHolder.tvPCA.setBackgroundColor(context.getResources().getColor(R.color.white));
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
            viewHolder.tvPCA.setTextColor(context.getResources().getColor(R.color.black));
        }
        return view;
    }

    class ViewHolder {
        TextView tvPCA;
    }
}
