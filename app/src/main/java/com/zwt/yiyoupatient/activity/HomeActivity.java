package com.zwt.yiyoupatient.activity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.adapter.ConversationListAdapterEx;
import com.zwt.yiyoupatient.fragment.HomeFragment;
import com.zwt.yiyoupatient.fragment.MyFragment;
import com.zwt.yiyoupatient.utils.MyBitmap;
import com.zwt.yiyoupatient.utils.getHW;

import java.util.ArrayList;

import butterknife.BindView;
import io.rong.imkit.RongContext;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;


public class HomeActivity extends BaseActivity {

    public static getHW gethw;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bottom_bar)
    BottomNavigationBar mBottomNavigationBar;
    @BindView(R.id.im)
    ImageView im;

    private ArrayList<Fragment> fragments;
    private static Drawable drawable;

    private ConversationListFragment mConversationListFragment = null;
    private Fragment fragment1 = new HomeFragment();
    private Fragment fragment2;
    private Fragment fragment3 = new MyFragment();
    private boolean isDebug;
    private Conversation.ConversationType[] mConversationsTypes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gethw = new getHW(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyBitmap bitmap = new MyBitmap(HomeActivity.this);
                drawable = bitmap.vague(R.drawable.viewpager1, 300);
            }
        }).start();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initUI() {
        isDebug = getSharedPreferences("config", MODE_PRIVATE).getBoolean("isDebug", false);
        fragment2 = initConversationList();
        assignViews();
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        setTitle("首页");
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(R.drawable.message);
                        im.setClickable(true);
                        break;
                    case 1:
                        setTitle("联系人");
                        im.setImageResource(android.R.drawable.ic_menu_add);
                        im.setVisibility(View.VISIBLE);
                        im.setClickable(false);
                        break;
                    case 2:
                        setTitle("我的");
                        im.setVisibility(View.INVISIBLE);
                        im.setClickable(false);
                        break;
                }
                smartFragmentReplace(fragments.get(position));
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    protected void initToolbar() {
        title.setText("首页");
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.root;
    }

    public static Drawable getDrawable() {
        return drawable;
    }

    private void assignViews() {
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setInActiveColor(R.color.colorBlack);
        mBottomNavigationBar.setActiveColor(R.color.colorone);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.homepager, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.contact, "联系人"))
                .addItem(new BottomNavigationItem(R.drawable.mymessage, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();//设置默认选项

    }

    private void setDefaultFragment() {
        im.setImageResource(R.drawable.message);
        myClick();
        smartFragmentReplace(fragment1);
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        return fragments;
    }

    private void setTitle(String title1) {
        title.setText(title1);
    }

    private void myClick() {
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(NotificationActivity.class);
            }
        });
    }

    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri;
            if (isDebug) {
                uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM,
                        Conversation.ConversationType.DISCUSSION
                };

            } else {
                uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM
                };
            }
            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }
    }
}


