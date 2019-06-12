package com.example.androidstore.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.androidstore.R;
import com.example.androidstore.adapter.GoodsAdapter;
import com.example.androidstore.bean.Goods;
import com.example.androidstore.contants.HttpContants;
import com.example.androidstore.utils.GsonUtils;
import com.example.androidstore.view.SubCategoryView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.Call;

public class GoodsListActivity extends AppCompatActivity {
    private Long categoryId;
    private ListView goodslistLv;
    private GoodsAdapter adapter;
    private Goods goods;
    private String customerId;
    private String goodsId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        SharedPreferences preferences = getSharedPreferences("Id", MODE_PRIVATE);
        customerId= preferences.getString("_Id","");
        goodslistLv = findViewById(R.id.goods_lv);
        adapter = new GoodsAdapter(this);
        initData();
        loadGoodsList(categoryId.toString());
        goodslistLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goods = (Goods) adapter.getItem(i);
                Intent intent = new Intent(GoodsListActivity.this, GoodsDetailsActivity.class);
                goodsId=goods.getId()+"";
                intent.putExtra("goodsId", goodsId);
                saveRecording();
                startActivity(intent);
            }
        });
    }
    private void saveRecording(){
        if (!"".equals(customerId)) {
            OkHttpUtils.post().url(HttpContants.RECORE_ADD_URL)
                    .addParams("customerId",customerId)
                    .addParams("goodsId",goodsId)
                    .addParams("goodsImage",goods.getImage())
                    .addParams("goodsName",goods.getName())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.d(LoggerInterceptor.TAG, "onResponse: " + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {

                        }
                    });
        }


    }

    private void initData() {
        Intent intent = getIntent();
        categoryId = intent.getLongExtra(SubCategoryView.CATEGORY_ID, 0);
    }

    private void loadGoodsList(String categoryId) {
        if (categoryId != null) {
            OkHttpUtils.get().url(HttpContants.GOODS_BY_CATEGORY_URL)
                    .addParams("categoryId", categoryId)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.d(LoggerInterceptor.TAG, "onResponse: " + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {

                            adapter.setBeans(GsonUtils.gsonToList(response, Goods[].class));
                            goodslistLv.setAdapter(adapter);
                        }
                    });
        }
    }
}
