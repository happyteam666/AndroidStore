package com.example.androidstore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.androidstore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.phone)
    TextInputEditText phone;
    @BindView(R.id.password)
    TextInputEditText password;
    @BindView(R.id.input_phone)
    TextInputLayout inputPhone;
    @BindView(R.id.input_password)
    TextInputLayout inputPassword;


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

    }

}
