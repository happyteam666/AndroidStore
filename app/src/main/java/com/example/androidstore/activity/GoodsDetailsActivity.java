package com.example.androidstore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.androidstore.R;
import com.example.androidstore.Util.GsonUtils;
import com.example.androidstore.bean.Goods;
import com.example.androidstore.contants.HttpContants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.Call;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;
public class GoodsDetailsActivity extends AppCompatActivity {
    private String goodsId;
    private Goods goods;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        Intent intent=getIntent();
        goodsId=intent.getStringExtra("goodsId");
        Log.d(TAG, "onCreate: "+goodsId);
    }
    private void loadGoods(){
        OkHttpUtils.get().url(HttpContants.GOODS_URL)
                .addParams("id",goodsId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(LoggerInterceptor.TAG, "onResponse: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        goods=GsonUtils.GsonToList(response, Goods[].class).get(0);
                        Log.d(TAG, "onResponse: "+goods.getName());
                    }
                });
    }
}
