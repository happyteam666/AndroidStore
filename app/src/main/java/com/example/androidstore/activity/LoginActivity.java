package com.example.androidstore.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.androidstore.R;
import com.example.androidstore.bean.Customer;
import com.example.androidstore.contants.HttpContants;
import com.example.androidstore.utils.GsonUtils;
import com.example.androidstore.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.phone)
    TextInputEditText phone;
    @BindView(R.id.password)
    TextInputEditText password;
    @BindView(R.id.input_phone)
    TextInputLayout inputPhone;
    @BindView(R.id.input_password)
    TextInputLayout inputPassword;
    //id
    long id1;
    //手机号
    String phoneuser;
    //密码
    String pwd;
    //用户名
    String Name1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login, R.id.register_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                attemptLogin();
                break;
            case R.id.register_now:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            default:
        }
    }

    private void attemptLogin() {
        if (!phone.getText().toString().equals("") && !password.getText().toString().equals("")) {
            OkHttpUtils.post().
                    url(HttpContants.LOGIN_URL)
                    .addParams("phone", Objects.requireNonNull(phone.getText()).toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("TAG", "首页请求失败==" + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.d("TAG", "首页请求成功==" + response);
                            if (!response.equals("")) {
                                Name1 = GsonUtils.GsonToBean(response, Customer.class).getUsername();
                                id1 = GsonUtils.GsonToBean(response, Customer.class).getId();
                                phoneuser = GsonUtils.GsonToBean(response, Customer.class).getPhone();
                                pwd = GsonUtils.GsonToBean(response, Customer.class).getPassword();
                                if (!isvalid(phoneuser, pwd, phone.getText().toString(), password.getText().toString())) {
                                    if (Looper.myLooper() == null) {
                                        Looper.prepare();
                                    }
                                    ToastUtils.showToast(LoginActivity.this, "帐号或密码错误");
                                    Looper.loop();
                                } else {
                                    if (Looper.myLooper() == null) {
                                        Looper.prepare();
                                    }
                                    ToastUtils.showToast(LoginActivity.this, "登录成功");
                                    SharedPreferences sharedPreferences = getSharedPreferences("Id", 0); //私有数据

                                    SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                                    editor.putString("_Id", String.valueOf(id1));
                                    editor.putString("_Name", "@" + Name1);
                                    editor.commit();//提交修改
//                                    finish();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                                    Looper.loop();
                                }
                            } else {
                                if (Looper.myLooper() == null) {
                                    Looper.prepare();
                                }
                                ToastUtils.showToast(LoginActivity.this, "帐号不存在");
                                Looper.loop();
                            }
                        }
                    });
        } else {
            ToastUtils.showToast(this, "请输入用户名与密码");
        }
    }

    public static boolean isvalid(String phonevalid, String pwdvalid, String i, String j) {
        if (i.equals(phonevalid) && j.equals(pwdvalid)) {
            return true;
        } else
            return false;
    }

}
