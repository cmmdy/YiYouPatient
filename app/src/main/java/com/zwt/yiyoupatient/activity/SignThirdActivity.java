package com.zwt.yiyoupatient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;

import butterknife.BindView;
import butterknife.OnClick;


public class SignThirdActivity extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.editText2)
    EditText editText2;

    private boolean forget;

    private boolean show = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.sign_third_activity;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        try {
            Bundle bundle = intent.getExtras();
            forget = bundle.getBoolean("forget");
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initToolbar() {
        if (!forget) {
            title.setText("");
            backtv.setText("");
        } else {
            title.setText("忘记密码");
            backtv.setText("");
            editText2.setHint("请重新设置登录密码");
        }
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.backll, R.id.showpassword, R.id.sign3_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.showpassword:
                if (show) {
                    editText2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    show = true;
                } else {
                    editText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    show = false;
                }
                break;
            case R.id.sign3_button:
                openActivity(HomeActivity.class);
                finishSome(4);
                break;
        }
    }
}
