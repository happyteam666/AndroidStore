package com.example.androidstore.activity;

import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.androidstore.R;
import com.example.androidstore.contants.HttpContants;
import com.example.androidstore.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.phone)
    TextInputEditText phone;
    @BindView(R.id.input_phone)
    TextInputLayout inputPhone;
    @BindView(R.id.password)
    TextInputEditText password;
    @BindView(R.id.input_password)
    TextInputLayout inputPassword;
    @BindView(R.id.identify)
    TextInputEditText identify;
    @BindView(R.id.input_identify)
    TextInputLayout inputIdentify;
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initOkHttpClient();
    }

    private void initOkHttpClient() {

    }

    @OnClick({R.id.send_identify, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_identify:
                attemptSendIdentify();
                break;
            case R.id.register:
                attemptRegister();
                break;
            default:
        }
    }

    private void attemptRegister() {
        if(isEmpty(phone.getText().toString(),password.getText().toString(),identify.getText().toString()))
         {
             if(isMobileNum(phone.getText().toString())) {
                 if(isPwdStrong(password.getText().toString())) {
                     if(identify.getText().toString().equals(string)) {
                         OkHttpUtils.post().
                                 url(HttpContants.REGISTER_URL)
                                 .addParams("phone", Objects.requireNonNull(phone.getText()).toString())
                                 .addParams("password", Objects.requireNonNull(password.getText()).toString())
                                 .build()
                                 .execute(new StringCallback() {
                                     @Override
                                     public void onError(Call call, Exception e, int id) {
                                         Log.e("TAG", "首页请求失败==" + e.getMessage());
                                         if (Looper.myLooper() == null) {
                                             Looper.prepare();
                                         }
                                         ToastUtils.showToast(RegisterActivity.this, "用户已存在");
                                         Looper.loop();
                                     }

                                     @Override
                                     public void onResponse(String response, int id) {
                                         Log.d("TAG", "首页请求成功==" + response);
                                         if (Looper.myLooper() == null) {
                                             Looper.prepare();
                                         }
                                         ToastUtils.showToast(RegisterActivity.this, "注册成功");
                                         finish();
                                         Looper.loop();
                                     }
                                 });
                     }else{
                            ToastUtils.showToast(this,"验证码输入，请重新验证");
                     }
                 }else{
                     ToastUtils.showToast(this,"密码太短，请重新设置");
                 }
             }else{
                 ToastUtils.showToast(this,"请核对手机号码");
             }
        }else{
            ToastUtils.showToast(this,"请输入用户名/密码/验证码");
        }
    }


    private void attemptSendIdentify() {

        int radom = (int)((Math.random()*9+1)*1000);
        string = Integer.toString(radom);
        Log.d("TAG", "attemptSendIdentify: "+string);
        ToastUtils.showToast(this,"验证码为："+string);

    }

    public static boolean isMobileNum(String mobiles) {
        String regExp = "13\\d{9}|14[579]\\d{8}|15[0123456789]\\d{8}|17[01235678]\\d{8" +
                "}|18\\d{9}";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isPwdStrong(String pwd) {
        if (pwd == null || pwd.length() < 6) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isEmpty(String phone,String passwd,String identify){
        if(!phone.equals("")&&!passwd.equals("")&&!identify.equals(""))
            return true;
        else
            return false;
    }

}
