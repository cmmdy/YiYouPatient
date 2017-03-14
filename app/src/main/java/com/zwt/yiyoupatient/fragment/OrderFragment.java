package com.zwt.yiyoupatient.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zwt.yiyoupatient.Base.BaseFragment;
import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.model.Patient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 夏夜晚凤 on 2017/3/2.
 */

public class OrderFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String title = "预约";

    private List<Patient> patientList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String getTitle() {
        return title;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
//        initPatients();
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        OrderAdapter adapter = new OrderAdapter(patientList, getContext());
//        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.order_fragment;
    }

    private void initPatients() {
        for (int i = 0; i < 4; i++) {
            Patient patient1 = new Patient(R.drawable.viewpager3, "王患者", "预约时间", "20", "男", "情况");
            patientList.add(patient1);
        }
    }


}
