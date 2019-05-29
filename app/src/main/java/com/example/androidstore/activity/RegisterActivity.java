package com.example.androidstore.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.androidstore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private RegisterTask registerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.send_identify, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_identify:
                break;
            case R.id.register:
                break;
            default:
        }
    }


    private class RegisterTask extends AsyncTask<Void, Void, Boolean> {
        public RegisterTask() {
            super();
        }

        @Override
        protected void onPostExecute(Boolean success) {
            registerTask = null;
            if (success) {
                finish();
            } else {
                inputPassword.setError(getString(R.string.error_incorrect_password));
            }
        }

        @Override
        protected void onCancelled() {
            registerTask = null;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return true;
        }
    }
}
