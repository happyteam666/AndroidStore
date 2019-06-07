package com.example.androidstore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.androidstore.R;
import com.example.androidstore.Util.GsonUtils;
import com.example.androidstore.bean.Customer;
import com.example.androidstore.contants.HttpContants;
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
    //手机号
    String phoneuser;
    //密码
    String pwd;



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
        if(!phone.getText().toString().equals("") && !password.getText().toString().equals("") ) {
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
                            if(!response.equals("")) {
                                phoneuser = GsonUtils.GsonToBean(response, Customer.class).getPhone();
                                pwd = GsonUtils.GsonToBean(response, Customer.class).getPassword();
                                if (!isvalid(phoneuser, pwd, phone.getText().toString(), password.getText().toString())) {
                                    if (Looper.myLooper() == null) {
                                        Looper.prepare();
                                    }
                                    ToastUtils.showToast(LoginActivity.this, "密码或者帐号错误");
                                    Looper.loop();
                                } else {
                                    if (Looper.myLooper() == null) {
                                        Looper.prepare();
                                    }
                                    ToastUtils.showToast(LoginActivity.this, "登录成功");
                                    finish();
                                    Looper.loop();
                                }
                            }else{
                                if (Looper.myLooper() == null) {
                                    Looper.prepare();
                                }
                                ToastUtils.showToast(LoginActivity.this, "帐号不存在");
                                Looper.loop();
                            }
                        }
                    });
        }else{
            ToastUtils.showToast(this,"请输入用户名与密码");
        }
    }

    public static boolean isvalid(String phonevalid,String pwdvalid,String i,String j){
        if(i.equals(phonevalid) && j.equals(pwdvalid)){
            return true;
        }else
            return false;
    }

}
