package com.zwt.yiyoupatient.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.adapter.ContactAdapter;
import com.zwt.yiyoupatient.adapter.EatAdapter;
import com.zwt.yiyoupatient.model.Eat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zwt.yiyoupatient.R.id.listView;
import static zuo.biao.library.util.CommonUtil.toActivity;

public class EatActivity extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.eatrl)
    SwipeMenuListView eatrl;

    List<Eat> eats = new ArrayList<>();
    Context context;
    EatAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.eat_activity;
    }

    @Override
    protected void initUI() {
        context = this;
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem deleteItem = new SwipeMenuItem(context);
                deleteItem.setBackground(new ColorDrawable(Color.RED));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setTitle("删除");
                deleteItem.setTitleColor(Color.WHITE);
                deleteItem.setTitleSize(20);
                menu.addMenuItem(deleteItem);
            }
        };

        eatrl.setDivider(getResources().getDrawable(android.R.drawable.divider_horizontal_bright));
        eatrl.setMenuCreator(creator);
        adapter = new EatAdapter(this, R.layout.eat_item, eats);
        eatrl.setAdapter(adapter);

        eatrl.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //index的值就是在SwipeMenu依次添加SwipeMenuItem顺序值，类似数组的下标。
                //从0开始，依次是：0、1、2、3...
                switch (index) {
                    case 0:
                        eats.remove(position);
                        adapter.notifyDataSetChanged();
                        eatrl.setAdapter(adapter);
                        break;
                }

                // false : 当用户触发其他地方的屏幕时候，自动收起菜单。
                // true : 不改变已经打开菜单的样式，保持原样不收起。
                return false;
            }
        });
    }

    public int dp2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initToolbar() {
        backtv.setText("返回");
        title.setText("吃药提醒");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.backll, R.id.add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backll:
                finish();
                break;
            case R.id.add:
                toActivity(this, new Intent(this, Eat2Activity.class), 1000, false);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {

            case 1000:
                if (data != null) {
                    Eat eat = data.getParcelableExtra("EAT");
                    eats.add(eat);
                    adapter.notifyDataSetChanged();
                    eatrl.setAdapter(adapter);
                }
                break;
            default:
                break;
        }
    }


}
