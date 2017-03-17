package com.zwt.yiyoupatient.expandtab;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.activity.OrderOnlineActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ViewLeft extends LinearLayout implements ViewBaseAction, AdapterView.OnItemClickListener {

    private ListView lvProvince;
    private ListView lvCity;
    private ListView lvArea;
    private ArrayList<String> listP;
    private ArrayList<String> listC;
    private ArrayList<String> listA;
    private OnSelectListener mOnSelectListener;

    private String showString = "地区";

    private Context mContext;

    JSONArray jsonArrayP;
    JSONArray jsonArrayC;
    JSONObject jsonObjP;
    JSONObject jsonObjC;
    int pro;
    int city;
    int area;
    AdapterForPCA adapterP;
    AdapterForPCA adapterC;
    AdapterForPCA adapterA;


    public ViewLeft(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ViewLeft(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }


    public String getShowText() {
        return showString;
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        public void getValue(String showText);
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    private String readPCA() {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            OrderOnlineActivity orderOnlineActivity = (OrderOnlineActivity) mContext;
            System.out.println(orderOnlineActivity);
            in = orderOnlineActivity.getAssets().open("PCA.json");
            out = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int length = -1;
            while ((length = in.read(b)) != -1) {
                out.write(b, 0, length);
            }
            return new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取省列表
     */
    private void initListP() {
        try {
            listP.clear();
            if (jsonArrayP != null) {
                for (int i = 0; i < jsonArrayP.length(); i++) {
                    JSONObject objP = jsonArrayP.getJSONObject(i);//获取省份对象
                    listP.add(objP.getString("name"));//获取省份名字
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据点击的省份，获取该省的城市列表
     *
     * @param provinceNum
     */
    private void setListC(int provinceNum) {
        listC.clear();
        try {
            jsonObjP = jsonArrayP.getJSONObject(provinceNum);//获取点击的省份对象
            jsonArrayC = jsonObjP.getJSONArray("city");//获取该省份的城市数组
            for (int i = 0; i < jsonArrayC.length(); i++) {
                JSONObject objC = jsonArrayC.getJSONObject(i);//获取城市数组的城市对象
                listC.add(objC.getString("name"));//获取城市名字
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据点击的城市获得地区列表
     *
     * @param cityNum
     */
    private void setListA(int cityNum) {
        listA.clear();
        try {
            jsonObjC = jsonArrayC.getJSONObject(cityNum);//根据点击的城市对象
            JSONArray arrayA = jsonObjC.getJSONArray("area");//获取该城市的地区列表
            for (int i = 0; i < arrayA.length(); i++) {
                listA.add(arrayA.getString(i));//添加地区列表到list
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            jsonArrayP = new JSONArray(readPCA());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listP = new ArrayList<>();
        initListP();
        listC = new ArrayList<>();
        pro = 0;
        setListC(pro);//设置默认展示的省份，根据json数组的系列号
        listA = new ArrayList<>();
        city = 0;
        setListA(city);//设置默认展示哪个城市的地区

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_region, this, true);

        lvProvince = (ListView) findViewById(R.id.listView);
        adapterP = new AdapterForPCA(listP, mContext);
        adapterP.setSelectPosition(pro);//设置默认选中的省份变颜色
        lvProvince.setAdapter(adapterP);
        lvProvince.setOnItemClickListener(this);

        lvCity = (ListView) findViewById(R.id.listView2);
        adapterC = new AdapterForPCA(listC, mContext);
        adapterC.setSelectPosition(city);//设置默认选中的城市变颜色
        lvCity.setAdapter(adapterC);
        lvCity.setOnItemClickListener(this);

        lvArea = (ListView) findViewById(R.id.listView3);
        adapterA = new AdapterForPCA(listA, mContext);
        adapterA.setSelectPosition(area);//设置默认选中的地区变颜色
        lvArea.setAdapter(adapterA);
        lvArea.setOnItemClickListener(this);

    }

    /**
     * 每个list 点击事件
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.listView://点击省列表
                pro = i;//获得选择的省的item
                adapterP.setSelectPosition(pro);//为了让选择的item显示不同的颜色
                //每一次选择省，都需要把 之前选择的市和区的item初始化为0，默认选择第一个
                city = 0;
                area = 0;
                adapterC.setSelectPosition(city);//为了让选择的item显示不同的颜色
                adapterA.setSelectPosition(area);//为了让选择的item显示不同的颜色
                //获得点击的省份对应的 城市列表
                setListC(pro);
                //点击省份，显示城市，由于还没有点击城市，所以默认选择第一个城市，展示第一个城市的区列表
                setListA(city);

                //点击省份，需要三个listview都刷新data ，以便执行 setSelectPosition ，刷新选择的项目的颜色
                adapterP.notifyDataSetChanged();
                adapterC.notifyDataSetChanged();
                adapterA.notifyDataSetChanged();
                break;
            case R.id.listView2:
                city = i;//点击的城市的item
                area = 0;//由于还没有选择区，所以区默认选择第一个
                adapterC.setSelectPosition(city);//为了让选择的item显示不同的颜色
                adapterA.setSelectPosition(area);//为了让选择的item显示不同的颜色
                setListA(i);//获取选择的城市的区列表

                //点击城市，需要刷新 城市 和 区 listview， 以便执行 setSelectPosition ，刷新选择的项目的颜色
                adapterC.notifyDataSetChanged();
                adapterA.notifyDataSetChanged();
                break;
            case R.id.listView3:
                area = i;//点击的区
                adapterA.setSelectPosition(area);////为了让选择的item显示不同的颜色
                //点击城市，需要刷新 区 listview， 以便执行 setSelectPosition ，刷新选择的项目的颜色
                adapterA.notifyDataSetChanged();
                showString = listP.get(pro) + "" + listC.get(city) + "" + listA.get(area);
                mOnSelectListener.getValue(listA.get(area));
                Toast.makeText(mContext, showString, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
