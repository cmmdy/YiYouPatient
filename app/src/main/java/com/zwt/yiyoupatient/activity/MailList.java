package com.zwt.yiyoupatient.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.adapter.ContactAdapter;
import com.zwt.yiyoupatient.model.Contact;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MailList extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;
    private Context context;
    private List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.maillist_activity;
    }

    @Override
    protected void initUI() {
        context = this;
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem phone = new SwipeMenuItem(context);
                phone.setBackground(new ColorDrawable(Color.YELLOW));
                phone.setTitle("拨号");
                phone.setTitleColor(Color.WHITE);
                phone.setTitleSize(20);
                phone.setWidth(dp2px(90));
                phone.setIcon(android.R.drawable.ic_menu_call);
                menu.addMenuItem(phone);

                SwipeMenuItem deleteItem = new SwipeMenuItem(context);
                deleteItem.setBackground(new ColorDrawable(Color.RED));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setTitle("删除");
                deleteItem.setTitleColor(Color.WHITE);
                deleteItem.setTitleSize(20);
                deleteItem.setIcon(android.R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };

        final SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.maillist);
        listView.setDivider(getResources().getDrawable(android.R.drawable.divider_horizontal_bright));
        listView.setMenuCreator(creator);
        initffts();
        final ContactAdapter adapter = new ContactAdapter(this, R.layout.contact_item, contacts);
        listView.setAdapter(adapter);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //index的值就是在SwipeMenu依次添加SwipeMenuItem顺序值，类似数组的下标。
                //从0开始，依次是：0、1、2、3...
                switch (index) {
                    case 0:
                        Toast.makeText(context, "拨号", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        contacts.remove(position);
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);
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
        backtv.setText("我的");
        title.setText("通讯录");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    //初始化ListView
    private void initffts() {
        for (int i = 0; i < 10; i++) {
            Contact contact = new Contact("周"+i, "18100178511");
            contacts.add(contact);
        }
    }

    @OnClick(R.id.backll)
    public void onClick() {
        finish();
    }
}
