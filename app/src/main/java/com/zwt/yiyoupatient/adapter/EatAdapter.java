package com.zwt.yiyoupatient.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.model.Eat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by 夏夜晚凤 on 2017/3/20.
 */

public class EatAdapter extends ArrayAdapter<Eat> {


    private Context mContext;

    private int resourceId;

    public EatAdapter(Context context, int resource, List<Eat> list) {
        super(context, resource, list);
        resourceId = resource;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Eat eat = getItem(position);
        View view;
        final ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                    false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.time.setText(eat.getTime());
        viewHolder.content.setText(eat.getContent());
        viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.checkbox.isChecked()){
                    Toast.makeText(mContext, "你已设置该提醒", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "你已取消该提醒", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


    static class ViewHolder{
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.checkbox)
        CheckBox checkbox;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
