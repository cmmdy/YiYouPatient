package com.zwt.yiyoupatient.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.adapter.collectionAdapter;
import com.zwt.yiyoupatient.model.CollectionText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CollectionActivity extends BaseActivity {

    private List<CollectionText> collectionTexts = new ArrayList<>();

    @BindView(R.id.backiv)
    ImageView backiv;
    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.backll)
    LinearLayout backll;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.collectionlv)
    ListView collectionlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.collection_activity;
    }

    @Override
    protected void initUI() {
        initText();
        collectionAdapter collectionAdapter = new collectionAdapter(
                this, R.layout.collection_item, collectionTexts);
        collectionlv.setAdapter(collectionAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        collectionlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openActivity(TextActivity.class);
            }
        });
    }

    @Override
    protected void initToolbar() {
        title.setText("我的收藏");
        backtv.setText("");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick(R.id.backll)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
        }
    }

    private void initText(){
        CollectionText collectionText = new CollectionText(R.drawable.viewpager1
                , "多吃纤维食物有益减肥", "多吃纤维食物有益减肥", "2017/3/9");
        collectionTexts.add(collectionText);
        collectionTexts.add(collectionText);
}
}
