package com.zwt.yiyoupatient.expandtab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.zwt.yiyoupatient.R;

import java.util.List;

public class TextAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> mListData;
    private String[] mArrayData;
    private int selectedPos = -1;
    private String selectedText = "";
    private float textSize;
    private OnClickListener onClickListener;
    private OnItemClickListener mOnItemClickListener;

    public TextAdapter(Context context, List<String> listData) {
        super(context, R.layout.choose_item, listData);
        mContext = context;
        mListData = listData;
        init();
    }

    private void init() {
        onClickListener = new OnClickListener() {

            @Override
            public void onClick(View view) {
                selectedPos = (Integer) view.getTag();
                setSelectedPosition(selectedPos);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, selectedPos);
                }
            }
        };
    }

    public TextAdapter(Context context, String[] arrayData) {
        super(context, R.layout.choose_item, arrayData);
        mContext = context;
        mArrayData = arrayData;
        init();
    }

    /**
     * 设置选中的position,并通知列表刷新
     */
    public void setSelectedPosition(int pos) {
        if (mListData != null && pos < mListData.size()) {
            selectedPos = pos;
            selectedText = mListData.get(pos);
            notifyDataSetChanged();
        } else if (mArrayData != null && pos < mArrayData.length) {
            selectedPos = pos;
            selectedText = mArrayData[pos];
            notifyDataSetChanged();
        }

    }

    /**
     * 设置选中的position,但不通知刷新
     */
    public void setSelectedPositionNoNotify(int pos) {
        selectedPos = pos;
        if (mListData != null && pos < mListData.size()) {
            selectedText = mListData.get(pos);
        } else if (mArrayData != null && pos < mArrayData.length) {
            selectedText = mArrayData[pos];
        }
    }

    /**
     * 获取选中的position
     */
    public int getSelectedPosition() {
        if (mArrayData != null && selectedPos < mArrayData.length) {
            return selectedPos;
        }
        if (mListData != null && selectedPos < mListData.size()) {
            return selectedPos;
        }

        return -1;
    }

    /**
     * 设置列表字体大小
     */
    public void setTextSize(float tSize) {
        textSize = tSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.choose_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setTag(position);
        String mString = "";
        if (mListData != null) {
            if (position < mListData.size()) {
                mString = mListData.get(position);
            }
        } else if (mArrayData != null) {
            if (position < mArrayData.length) {
                mString = mArrayData[position];
            }
        }
        if (mString.contains("不限"))
            viewHolder.tv.setText("不限");
        else
            viewHolder.tv.setText(mString);
        viewHolder.tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        if (selectedText != null && selectedText.equals(mString)) {
            viewHolder.tv.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));//设置选中的背景图片
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
            viewHolder.tv.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            viewHolder.tv.setBackgroundColor(mContext.getResources().getColor(R.color.white));//设置未选中状态背景图片
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            viewHolder.tv.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        viewHolder.tv.setOnClickListener(onClickListener);
        return convertView;
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
    }

    /**
     * 重新定义菜单选项单击接口
     */
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    class ViewHolder {
        TextView tv;
    }

}
