package com.zwt.yiyoupatient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;

import butterknife.BindView;
import butterknife.OnClick;



public class SignFirstActivity extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.sign_button)
    Button signButton;

    private boolean forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.sign_first_activity;
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
            textView11.setText("");
            signButton.setText("确定");
        }
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.backll, R.id.sign_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.sign_button:
                if (!forget) {
                    openActivity(SignSecondActivity.class);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("forget", true);
                    openActivity(SignSecondActivity.class, bundle);
                }
                break;
        }
    }
}
