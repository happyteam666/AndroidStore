package com.example.androidstore.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.androidstore.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.androidstore.R.id.text_general;


public class SettingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.text_usersafe, R.id.text_paysetting,
            R.id.text_myfiles, R.id.text_design, R.id.text_general,
            R.id.text_vipp, R.id.text_feedback, R.id.text_about, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_usersafe:
                break;
            case R.id.text_paysetting:
                break;
            case R.id.text_myfiles:
                break;
            case R.id.text_design:
                break;
            case text_general:
                break;
            case R.id.text_vipp:
                break;
            case R.id.text_feedback:
                break;
            case R.id.text_about:
                break;
            case R.id.exit: {
                SharedPreferences sp = getSharedPreferences("Id", 0);
                if (!sp.getString("_Id", "").equals("")) {
                    Exit();
                    startActivity(new Intent(this, LoginActivity.class));

                } else {
                }
                break;
            }
            default:

        }
    }


    public void clearData() {
        SharedPreferences sp = getSharedPreferences("Id", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.clear();

        editor.commit();
    }

    public void Exit() {

        SharedPreferences sp = getSharedPreferences("Id", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();

    }
}
