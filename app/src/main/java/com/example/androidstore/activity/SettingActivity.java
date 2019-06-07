package com.example.androidstore.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.androidstore.R;



public class SettingActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }




    public void clearData() {
        SharedPreferences sp = getSharedPreferences("Id", Context.MODE_PRIVATE);

          SharedPreferences.Editor editor = sp.edit(); editor.clear();

        editor.commit();
    }

}
