package com.zwt.yiyoupatient.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;

import butterknife.BindView;
import butterknife.OnClick;

public class HealthHelpActiyivty extends BaseActivity {

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
        return R.layout.health_help_actiyivty;
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
        title.setText("健康助手");
        backtv.setText("我的");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.backll, R.id.eat, R.id.heart, R.id.foot, R.id.distance, R.id.climb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.eat:
                openActivity(EatActivity.class);
                break;
            case R.id.heart:
                break;
            case R.id.foot:
                break;
            case R.id.distance:
                break;
            case R.id.climb:
                break;
        }
    }
}
