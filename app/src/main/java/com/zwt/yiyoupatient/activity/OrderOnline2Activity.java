package com.zwt.yiyoupatient.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class OrderOnline2Activity extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.headimage)
    ImageView headimage;
    @BindView(R.id.imageleft)
    ImageView imageleft;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.doctor)
    TextView doctor;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.messagephone_tv)
    TextView messagephoneTv;
    @BindView(R.id.adress_tv)
    TextView adressTv;
    @BindView(R.id.civ)
    CircleImageView civ;
    @BindView(R.id.messagedoctor_tv2)
    TextView messagedoctorTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.order_online2_activity;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        Glide.with(this).load(R.drawable.viewpager1).into(headimage);
        Glide.with(this).load(R.drawable.viewpager2).into(imageleft);
        name.setText("中医世家");
        doctor.setText("负责医师：王医师");
        score.setText("评分：5.0");
        messagephoneTv.setText("88888888");
        adressTv.setText("下沙街道");
        Glide.with(this).load(R.drawable.viewpager3).into(civ);
        messagedoctorTv2.setText("王医师");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initToolbar() {
        backtv.setText("在线预约");
        title.setText("");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.backll, R.id.phonell, R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.phonell:
                break;
            case R.id.button:
                openActivity(OrderOnline3Activity.class);
                break;
        }
    }
}
