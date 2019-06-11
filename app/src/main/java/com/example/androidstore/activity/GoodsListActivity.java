package com.example.androidstore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



import com.example.androidstore.adapter.GoodsAdapter;
import com.example.androidstore.R;
import com.example.androidstore.utils.GsonUtils;
import com.example.androidstore.view.SubCategoryView;
import com.example.androidstore.bean.Goods;
import com.example.androidstore.contants.HttpContants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.Call;

public class GoodsListActivity extends AppCompatActivity {
    private Long categoryId;
    private ListView goodsList_Lv;
    private GoodsAdapter adapter;
    private Goods goods;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        goodsList_Lv=findViewById(R.id.goods_lv);
        adapter=new GoodsAdapter(this);
        initData();
        loadGoodsList(categoryId.toString());
        goodsList_Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goods=(Goods)adapter.getItem(i);
                Intent intent=new Intent(GoodsListActivity.this,GoodsDetailsActivity.class);
                intent.putExtra("goodsId",goods.getId()+"");
                startActivity(intent);
            }
        });
    }
    private void initData(){
        Intent intent = getIntent();
        categoryId = intent.getLongExtra(SubCategoryView.CATEGORY_ID,0);
    }
    private void loadGoodsList(String categoryId){
        if (categoryId!=null)
        OkHttpUtils.get().url(HttpContants.GOODS_BY_CATEGORY_URL)
                .addParams("categoryId",categoryId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(LoggerInterceptor.TAG, "onResponse: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        adapter.setBeans(GsonUtils.GsonToList(response, Goods[].class));
                        goodsList_Lv.setAdapter(adapter);
                    }
                });
    }
}
