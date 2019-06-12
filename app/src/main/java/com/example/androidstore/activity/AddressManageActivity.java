package com.example.androidstore.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.androidstore.R;
import com.example.androidstore.adapter.AddressAdapter;
import com.example.androidstore.bean.Address;
import com.example.androidstore.contants.HttpContants;
import com.example.androidstore.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.Call;

public class AddressManageActivity extends AppCompatActivity {
    private FloatingActionButton addFab;
    private ListView addressList;
    private AddressAdapter adapter;
    private String customerId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        initView();
        SharedPreferences preferences = getSharedPreferences("Id", MODE_PRIVATE);
        customerId = preferences.getString("_Id", "");
        loadAddressList();
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressManageActivity.this, AddAddressActivity.class));
            }
        });
    }

    private void initView() {
        addFab = findViewById(R.id.fab_recycler_view);
        addressList = findViewById(R.id.address_list_view);
        adapter = new AddressAdapter(this);
    }

    private void loadAddressList() {
        if (customerId != null) {
            OkHttpUtils.get().url(HttpContants.ADDRESS_BY_CUSTOMER_URL)
                    .addParams("customerId", customerId)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.d(LoggerInterceptor.TAG, "onResponse: " + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            adapter.setBeans(GsonUtils.gsonToList(response, Address[].class));
                            addressList.setAdapter(adapter);
                        }
                    });
        }
    }

}
