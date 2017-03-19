package com.zwt.yiyoupatient.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.adapter.OrderAdapter;
import com.zwt.yiyoupatient.expandtab.ExpandTabView;
import com.zwt.yiyoupatient.expandtab.ViewLeft;
import com.zwt.yiyoupatient.expandtab.ViewMiddle;
import com.zwt.yiyoupatient.expandtab.ViewRight;
import com.zwt.yiyoupatient.model.Doctor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderOnlineActivity extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.orderlv)
    ListView orderlv;

    public ExpandTabView getExpandTabView() {
        return expandTabView;
    }

    private ExpandTabView expandTabView;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private ViewLeft viewLeft;
    private ViewMiddle viewMiddle;
    private ViewRight viewRight;

    private List<Doctor> doctors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.order_online_activity;
    }

    @Override
    protected void initUI() {
        expandTabView = (ExpandTabView) findViewById(R.id.expandtab_view);

        viewLeft = new ViewLeft(this);
        viewMiddle = new ViewMiddle(this);
        viewRight = new ViewRight(this);
    }

    @Override
    protected void initData() {
        initDoctor();
        OrderAdapter adapter = new OrderAdapter(this, R.layout.order_item, doctors);
        orderlv.setAdapter(adapter);

        mViewArray.add(viewLeft);
        mViewArray.add(viewMiddle);
        mViewArray.add(viewRight);
        ArrayList<String> mTextArray = new ArrayList<String>();
        mTextArray.add("地区");
        mTextArray.add("科目");
        mTextArray.add("排序");
        expandTabView.setValue(mTextArray, mViewArray);
        expandTabView.setTitle(viewLeft.getShowText(), 0);
        expandTabView.setTitle(viewMiddle.getShowText(), 1);
        expandTabView.setTitle(viewRight.getShowText(), 2);
    }

    @Override
    protected void initListener() {
        viewLeft.setOnSelectListener(new ViewLeft.OnSelectListener() {
            @Override
            public void getValue(String showText) {
                onRefresh(viewLeft, showText);
            }
        });

        viewMiddle.setOnSelectListener(new ViewMiddle.OnSelectListener() {
            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewMiddle, showText);
            }

        });

        viewRight.setOnSelectListener(new ViewRight.OnSelectListener() {
            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewRight, showText);
            }
        });

        orderlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openActivity(OrderOnline2Activity.class);
            }
        });
    }

    @Override
    protected void initToolbar() {
        backtv.setText("首页");
        title.setText("在线预约");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick(R.id.backll)
    public void onClick() {
        finish();
    }

    private void initDoctor() {
        for (int i = 0; i < 10; i++) {
            Doctor doctor = new Doctor(R.drawable.viewpager3, "中医世家", "负责医师：王医师", "评分：5.0");
            doctors.add(doctor);
        }
    }

    private void onRefresh(View view, String showText) {

        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            expandTabView.setTitle(showText, position);
        }
        Toast.makeText(OrderOnlineActivity.this, showText, Toast.LENGTH_SHORT).show();

    }

    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onBackPressed() {

        if (!expandTabView.onPressBack()) {
            finish();
        }

    }
}
