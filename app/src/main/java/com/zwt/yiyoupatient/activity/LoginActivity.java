package com.zwt.yiyoupatient.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zwt.yiyoupatient.App;
import com.zwt.yiyoupatient.Base.BaseActivity;
import com.zwt.yiyoupatient.DemoContext;
import com.zwt.yiyoupatient.R;
import com.zwt.yiyoupatient.utils.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * Created by Bob on 15/8/19.
 * 登录页面
 * 1，token 从自己 server 获取的演示
 * 2，拿到 token 后，做 connect 操作
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.accountEdittext)
    EditText accountEdittext;
    @BindView(R.id.pwdEdittext)
    EditText pwdEdittext;

    /**
     * token 的主要作用是身份授权和安全，因此不能通过客户端直接访问融云服务器获取 token，
     * 您必须通过 Server API 从融云服务器 获取 token 返回给您的 App，并在之后连接时使用
     */
    private String token;
    private String account;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        account = accountEdittext.getText().toString();
        password = pwdEdittext.getText().toString();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initToolbar() {
        title.setText("用户登录");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.login_button, R.id.forget, R.id.sign})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                login();
                break;
            case R.id.forget:
                Bundle bundle = new Bundle();
                bundle.putBoolean("forget", true);
                openActivity(SignFirstActivity.class, bundle);
                break;
            case R.id.sign:
                openActivity(SignFirstActivity.class);
                break;
        }
    }

    /**
     * 用户登录，用户登录成功，获得 cookie，将cookie 保存
     */
    private void login() {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {

                Map<String, String> requestParameter = new HashMap<String, String>();

                requestParameter.put("email", "yang115@qq.com");
                requestParameter.put("password", "123456");

                String result = NetUtils.sendPostRequest("email_login", requestParameter);
                return result;
            }

            @Override
            protected void onPostExecute(String result) {

                getToken();
            }
        }.execute();
    }

    /**
     * 获得token
     */
    private void getToken() {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {

                String result = NetUtils.sendGetRequest("token");
                return result;
            }

            @Override
            protected void onPostExecute(String result) {

                try {
                    if (result != null) {
                        JSONObject object = new JSONObject(result);
                        JSONObject jobj = object.getJSONObject("result");

                        if (object.getInt("code") == 200) {
                            token = jobj.getString("token");
                            connect(token);

                            SharedPreferences.Editor edit = DemoContext.getInstance().getSharedPreferences().edit();
                            edit.putString("DEMO_TOKEN", token);
                            edit.apply();

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(final String token) {

        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect("RlyA9AnnPu14SXM5uRv6bxOtujb/1yuaYDNHcJgn9p5nmoOhpod+VFV3/rXGypguzVFKeG13uSLXwRN0EZsb+Q==", new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {

                    Log.e("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {

                    Log.e("LoginActivity", "--onSuccess" + userid);
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 *                  http://www.rongcloud.cn/docs/android.html#常见错误码
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                    Log.e("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }
}
