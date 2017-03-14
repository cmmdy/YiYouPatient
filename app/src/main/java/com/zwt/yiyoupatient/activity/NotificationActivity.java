package com.zwt.yiyoupatient.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.adapter.NotificationAdapter;
import com.zwt.yiyoupatient.model.MyNotifaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NotificationActivity extends BaseActivity {

    private static List<MyNotifaction> myNotifactions = new ArrayList<>();

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.listview)
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.notification_activity;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        initNotification();
        NotificationAdapter notificationAdapter = new NotificationAdapter(this, R.layout.notification_item, myNotifactions);
        listview.setAdapter(notificationAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initToolbar() {
        backtv.setText("首页");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick(R.id.backll)
    public void onClick() {
        finish();
    }

    private void initNotification(){
        for(int i=0; i<5; i++){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DATE);
            String ymd = year + "/" + month + "/" + day;
            MyNotifaction myNotifaction = new MyNotifaction("有新的门诊预约", "您有新的门诊信息，请注意处理。", ymd);
            myNotifactions.add(myNotifaction);
        }
    }
}
