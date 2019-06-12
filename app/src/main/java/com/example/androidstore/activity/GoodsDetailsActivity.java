package com.example.androidstore.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.androidstore.R;
import com.example.androidstore.adapter.SpecificationAdapter;
import com.example.androidstore.bean.Goods;
import com.example.androidstore.bean.Specifications;
import com.example.androidstore.contants.HttpContants;
import com.example.androidstore.utils.GsonUtils;
import com.example.androidstore.utils.ToastUtils;
import com.example.androidstore.view.SmartImageView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.ArrayList;

import okhttp3.Call;
public class GoodsDetailsActivity extends AppCompatActivity {
    private TextView priceTv;
    private TextView nameTv;
    private SmartImageView goodsIv;
    private String goodsId;
    private Goods goods;
    private String goodsPrice;
    private GridView specificationGv;
    private SpecificationAdapter adapter;
    private TextView addShopCar;
    private Specifications specifications;
    private String customerId;
    private String specificationId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        initView();
        initData();
        Intent intent=getIntent();
        goodsId=intent.getStringExtra("goodsId");
        loadGoods();
        specificationGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.mPosition=i;
                adapter.notifyDataSetChanged();
                specifications= (Specifications) adapter.getItem(i);
                goodsPrice=specifications.getPrice()+"";
                specificationId=specifications.getId()+"";
                priceTv.setText("¥ "+goodsPrice);
            }
        });
        addShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCartItem(specificationId);
            }
        });
    }
    private void addCartItem(String specificationId){
        OkHttpUtils.post().url(HttpContants.CARTITEM_ADD_URL)
                .addParams("goodsId",goodsId)
                .addParams("customerId",customerId)
                .addParams("name",goods.getName())
                .addParams("image",goods.getImage())
                .addParams("specificationsId",specificationId)
                .addParams("quantity","1")
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
        ToastUtils.showToast(this,"成功添加到购物车");
    }

    private void initView(){
        goodsIv=findViewById(R.id.goods_image);
        nameTv=findViewById(R.id.goods_name);
        priceTv=findViewById(R.id.goods_price);
        specificationGv=findViewById(R.id.specification_gv);
        addShopCar=findViewById(R.id.addshopcar);

    }
    private void initData(){
      adapter=new SpecificationAdapter(this);
        SharedPreferences preferences = getSharedPreferences("Id", MODE_PRIVATE);
        customerId= preferences.getString("_Id","");

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
                        goods= GsonUtils.gsonToBean(response,Goods.class);
                        goodsIv.setImageUrl(goods.getImage());
                        nameTv.setText(goods.getName());
                        priceTv.setText("¥ "+goods.getSpecificationsList().get(0).getPrice()+"");
                        adapter.setDatas((ArrayList<Specifications>) goods.getSpecificationsList());
                        specificationGv.setAdapter(adapter);
                        specificationGv.performItemClick(null,0,0);
                    }
                });
    }

}
