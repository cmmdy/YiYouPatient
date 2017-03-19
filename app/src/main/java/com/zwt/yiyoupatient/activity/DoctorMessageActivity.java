package com.zwt.yiyoupatient.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorMessageActivity extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.backll)
    LinearLayout backll;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.civ1)
    CircleImageView civ1;
    @BindView(R.id.imageLayout)
    RelativeLayout imagerl;
    @BindView(R.id.name_tv)
    TextView nametv;
    @BindView(R.id.phone_tv)
    TextView phonetv;
    @BindView(R.id.sex_tv)
    TextView sextv;
    @BindView(R.id.age_tv)
    TextView agetv;
    @BindView(R.id.as_tv)
    TextView astv;
    @BindView(R.id.al_tv)
    TextView altv;
    @BindView(R.id.adress_tv)
    TextView matv;
    @BindView(R.id.person_tv)
    TextView persontv;
    @BindView(R.id.civ2)
    CircleImageView civ2;
    @BindView(R.id.messageiv)
    ImageView messageiv;
    @BindView(R.id.messagename_tv)
    TextView mntv;
    @BindView(R.id.messagedoctor_tv1)
    TextView mdtv1;
    @BindView(R.id.messagescore_tv)
    TextView mstv;
    @BindView(R.id.messagephone_tv)
    TextView mptv;
    @BindView(R.id.phonell)
    LinearLayout phonell;
    @BindView(R.id.addressll)
    LinearLayout addressll;
    @BindView(R.id.civ3)
    CircleImageView civ3;
    @BindView(R.id.messagedoctor_tv2)
    TextView mdtv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.order_message_activity;
    }


    @Override
    protected void initUI() {
    }

    @Override
    protected void initData() {

        imagerl.setBackground(HomeActivity.getDrawable());

        Glide.with(this).load(R.drawable.viewpager1).into(civ1);

        nametv.setText("姓名");
        phonetv.setText("电话");
        sextv.setText("性别");
        agetv.setText("年龄");
        astv.setText("主治科目");
        altv.setText("认证等级");

        Glide.with(this).load(R.drawable.viewpager3).into(civ2);
        persontv.setText("王医师");

        Glide.with(this).load(R.drawable.viewpager4).into(messageiv);
        mntv.setText("中医世家");
        mdtv1.setText("负责医师:王医师");
        mstv.setText("评分：5.0");
        mptv.setText("88888888");
        matv.setText("下沙街道");
        Glide.with(this).load(R.drawable.viewpager2).into(civ3);
        mdtv2.setText("王医师");

    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initToolbar() {
        backtv.setText("个人信息");
        title.setText("我的");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.backll, R.id.button_argee, R.id.button_refuse, R.id.phonell})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.button_argee:
                openActivity(OrderOnline2Activity.class);
                break;
            case R.id.button_refuse:
                openActivity(OrderOnline2Activity.class);
                break;
            case R.id.phonell:

        }
    }
}
