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
    int i=0;

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
                if(i==1){
                    ToastUtils.showToast(this,"注册成功");
                    i=0;
                }
                break;
            default:
        }
    }

    private void attemptRegister() {
        if(!phone.getText().toString().equals("") && !password.getText().toString().equals("")) {
            OkHttpUtils.post().
                    url(HttpContants.REGISTER_URL)
                    .addParams("phone", Objects.requireNonNull(phone.getText()).toString())
                    .addParams("password", Objects.requireNonNull(password.getText()).toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("TAG", "首页请求失败==" + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.d("TAG", "首页请求成功==" + response);
//                            ToastUtils.showSafeToast(RegisterActivity.this,"注册成功");
                            i=1;
                            finish();
                        }
                    });
        }else{
            //Toast.makeText(this,"请输入用户名/密码",Toast.LENGTH_SHORT).show();
            ToastUtils.showToast(this,"请输入用户名/密码");
        }
    }


    private void attemptSendIdentify() {

    }

}
