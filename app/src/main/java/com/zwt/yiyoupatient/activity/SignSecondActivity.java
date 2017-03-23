package com.zwt.yiyoupatient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;

import butterknife.BindView;
import butterknife.OnClick;



public class SignSecondActivity extends BaseActivity {

    @BindView(com.zwt.yiyoupatient.R.id.backtv)
    TextView backtv;
    @BindView(com.zwt.yiyoupatient.R.id.title)
    TextView title;
    @BindView(com.zwt.yiyoupatient.R.id.editText2)
    EditText editText2;

    private boolean forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.sign_second_activity;
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
        }
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.backll, R.id.prove_button, R.id.sign2_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.prove_button:
                Toast.makeText(this, "验证码已发送，请稍等", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sign2_button:
                if(!forget) {
                    openActivity(SignThirdActivity.class);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("forget", true);
                    openActivity(SignThirdActivity.class, bundle);
                }
                break;
        }
    }
}
