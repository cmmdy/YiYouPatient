package com.zwt.yiyoupatient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.ui.TimePickerWindow;
import zuo.biao.library.util.TimeUtil;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static zuo.biao.library.util.CommonUtil.showShortToast;
import static zuo.biao.library.util.CommonUtil.toActivity;

public class OrderOnline3Activity extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.order_online3_activity;
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

    @OnClick({R.id.backll, R.id.button, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.button:
                toActivity(this, TimePickerWindow.createIntent(this), 1001, true);
                toActivity(this, DatePickerWindow.createIntent(this, new int[]{1971, 0, 1}
                        , TimeUtil.getDateDetail(System.currentTimeMillis())), 1000, false);
                break;
            case R.id.button2:
                Toast.makeText(this, "预约成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        System.out.println(requestCode);
        switch (requestCode) {
            case 1000:
                if (data != null) {
                    ArrayList<Integer> list = data.getIntegerArrayListExtra(DatePickerWindow.RESULT_DATE_DETAIL_LIST);
                    if (list != null && list.size() >= 3) {

                        date = "你的预约时间为" + (list.get(1) + 1) + "月" + list.get(2) + "日";
                    }
                }
                break;

            case 1001:
                if (data != null) {
                    ArrayList<Integer> list = data.getIntegerArrayListExtra(TimePickerWindow.RESULT_TIME_DETAIL_LIST);
                    if (list != null && list.size() >= 2) {

                        Toast.makeText(this, (date + list.get(0) + "时" + (list.get(1) + 1) + "分"), Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                break;
        }
    }
}
