package com.zwt.yiyoupatient.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;

import butterknife.BindView;
import butterknife.OnClick;

public class TextActivity extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title_toolbar;
    @BindView(R.id.title_text)
    TextView title;
    @BindView(R.id.backll)
    LinearLayout backll;
    @BindView(R.id.imageView)
    ImageView imageView;

    private boolean like = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.contact_activity;
    }


    @Override
    protected void initUI() {
    }

    @Override
    protected void initData() {
        title.setText("多吃纤维食品有益健康");
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initToolbar() {
        backtv.setText("");
        title_toolbar.setText("文章");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.backll, R.id.imageView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.imageView:
                if(!like) {
                    Glide.with(this).load(R.drawable.collect_click).into(imageView);

                    like = true;
                } else {
                    Glide.with(this).load(R.drawable.collect).into(imageView);
                    like = false;
                }
                break;
        }
    }
}
