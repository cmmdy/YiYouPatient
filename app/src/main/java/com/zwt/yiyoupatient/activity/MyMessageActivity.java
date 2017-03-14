package com.zwt.yiyoupatient.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class MyMessageActivity extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.civ1)
    CircleImageView civ1;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.sex)
    EditText sex;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.imageLayout)
    RelativeLayout imageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_message_activity;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        imageLayout.setBackground(HomeActivity.getDrawable());
        Glide.with(this).load(R.drawable.viewpager1).into(civ1);

        name.setText("X患者");
        sex.setText("男");
        age.setText("30");
        phone.setText("99999999");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initToolbar() {
        title.setText("个人信息");
        backtv.setText("我的");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.civ1, R.id.imageLayout, R.id.backll, R.id.revise})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.civ1:
                break;
            case R.id.imageLayout:
                break;
            case R.id.backll:
                finish();
                break;
            case R.id.revise:
                break;
        }
    }

}
