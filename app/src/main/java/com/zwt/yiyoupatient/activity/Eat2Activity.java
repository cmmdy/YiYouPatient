package com.zwt.yiyoupatient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.model.Eat;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.ui.EditTextInfoWindow;
import zuo.biao.library.ui.TimePickerWindow;

import static android.R.id.list;
import static zuo.biao.library.util.CommonUtil.toActivity;

public class Eat2Activity extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.remarks)
    TextView remarks;

    Eat eat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.eat2_activity;
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

    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.backll, R.id.timell, R.id.remarksll, R.id.save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.timell:
                toActivity(this, TimePickerWindow.createIntent(this), 1000, true);
                break;
            case R.id.remarksll:
                toActivity(this, EditTextInfoWindow.createIntent(this, "备注", ""), 1001, true);
                break;
            case R.id.save:
                setResult();
                finish();
                break;
        }
    }

    private void setResult(){
        eat = new Eat();
        eat.setTime(time.getText().toString());
        eat.setContent(remarks.getText().toString());
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("EAT", eat);
        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);
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
                    ArrayList<Integer> list = data.getIntegerArrayListExtra(TimePickerWindow.RESULT_TIME_DETAIL_LIST);
                    if (list != null && list.size() >= 2) {
                        time.setText((list.get(0) + "：" + list.get(1)));
                    }
                }
                break;

            case 1001:
                if (data != null) {
                    String sdata = data.getStringExtra(EditTextInfoWindow.RESULT_VALUE);
                    remarks.setText(sdata);
                }
            default:
                break;
        }
    }
}
