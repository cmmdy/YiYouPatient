package com.zwt.yiyoupatient.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.adapter.RecordAdapter;
import com.zwt.yiyoupatient.model.Record;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RecordActivity extends BaseActivity {

    List<Record> recordList = new ArrayList<>();
    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.record)
    ListView record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.record_activity;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        initRecords();
        RecordAdapter adapter = new RecordAdapter(this, R.layout.record_item, recordList);
        record.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        record.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openActivity(Record2Activity.class);
            }
        });
    }

    @Override
    protected void initToolbar() {
        backtv.setText("我的");
        title.setText("门诊记录");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    private void initRecords() {
        for (int i = 0; i < 10; i++) {
            Record record = new Record(R.drawable.viewpager4, "中医门诊", "王医师", "2016-01-01");
            recordList.add(record);
        }
    }

    @OnClick(R.id.backll)
    public void onClick() {
        finish();
    }
}
