package com.example.androidstore.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidstore.Adapter.GoodsAdapter;
import com.example.androidstore.Adapter.SpecificationAdapter;
import com.example.androidstore.R;
import com.example.androidstore.Util.GsonUtils;
import com.example.androidstore.View.SmartImage;
import com.example.androidstore.View.SmartImageView;
import com.example.androidstore.View.WebImage;
import com.example.androidstore.bean.Goods;
import com.example.androidstore.bean.Specifications;
import com.example.androidstore.contants.HttpContants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.ArrayList;

import okhttp3.Call;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;
public class GoodsDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView priceTv;
    private TextView nameTv;
    private SmartImageView goodsIv;
    private String goodsId;
    private Goods goods;
    private String goodsPrice;
    private GridView speciaficationGv;
    private SpecificationAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        initView();
        initData();
        Intent intent=getIntent();
        goodsId=intent.getStringExtra("goodsId");
        loadGoods();
        Log.d(TAG, "onCreate: "+goodsId);
    }
    @SuppressLint("WrongViewCast")
    private void initView(){
        goodsIv=findViewById(R.id.goods_image);
        nameTv=findViewById(R.id.goods_name);
        priceTv=findViewById(R.id.goods_price);
        speciaficationGv=findViewById(R.id.specification_gv);

    }
    private void initData(){
      adapter=new SpecificationAdapter(this);

    }
    private void loadGoods(){
        OkHttpUtils.get().url(HttpContants.GOODS_BY_ID)
                .addParams("id",goodsId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(LoggerInterceptor.TAG, "onResponse: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        goods= GsonUtils.GsonToBean(response,Goods.class);
                        goodsIv.setImageUrl(goods.getImage());
                        nameTv.setText(goods.getName());
                        priceTv.setText("¥ "+goods.getSpecificationsList().get(0).getPrice()+"");
                        adapter.setDatas((ArrayList<Specifications>) goods.getSpecificationsList());
                        speciaficationGv.setAdapter(adapter);
                    }
                });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
