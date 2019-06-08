package com.example.androidstore.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.androidstore.Adapter.CartAdapter;
import com.example.androidstore.R;
import com.example.androidstore.Util.GsonUtils;
import com.example.androidstore.bean.CartItem;
import com.example.androidstore.bean.Goods;
import com.example.androidstore.contants.HttpContants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;


public class CartFragment extends Fragment {

    private Unbinder bind;

    private ListView goodsList_Lv;
    private CartAdapter adapter;

    String message;
    @BindView(R.id.rv_bottom)
    RelativeLayout mRvBottom;
    @BindView(R.id.ll_empty)
    LinearLayout mLlEmpty;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart,container, false);
        initView(view);
        bind = ButterKnife.bind(this, view);
        if(getid1())
            attemptListCart();
        else
            initEmptyView();
        return view;
    }

    private void initView(View view) {
        goodsList_Lv = view.findViewById(R.id.goods_lv);
        adapter = new CartAdapter(getActivity());
    }

    private void initEmptyView() {
        mRvBottom.setVisibility(View.GONE);
        mLlEmpty.setVisibility(View.VISIBLE);
    }

    private void attemptListCart() {
            OkHttpUtils.get().
                    url(HttpContants.CARTITEM_URL)
                    .addParams("id", message)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("TAG", "首页请求失败==" + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.d("TAG", "首页请求成功==" + response);
                            if(response.equals("")){
                                initEmptyView();
                            }
                            else{
                                Log.d(LoggerInterceptor.TAG, "onResponse: " + response);
                                if(response.equals("[]")) {
                                    initEmptyView();
                                }else {
                                    adapter.setBeans(GsonUtils.GsonToList(response, CartItem[].class));
                                    goodsList_Lv.setAdapter(adapter);
                                }
                            }

                        }
        });
  }

  public boolean getid1() {
      SharedPreferences sp = getActivity().getSharedPreferences("Id", 0);
      message = sp.getString("_Id", "");
      if (!message.equals(""))
          return true;
       else
          return false;
  }
}