package com.example.androidstore.activity;

import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.example.androidstore.R;

import butterknife.ButterKnife;

public class AddressManage extends AppCompatActivity {
    private TextView addressTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_add);
        ButterKnife.bind(this);
        addressTv=findViewById(R.id.txt_address);
        addressTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }


}
