package com.zwt.yiyoupatient.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;

import butterknife.BindView;
import butterknife.OnClick;

public class Record2Activity extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.record2_activity;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initToolbar() {
        title.setText("预约门诊");
        backtv.setText("返回");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.backll, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.button2:
                finish();
                break;
        }
    }
}
