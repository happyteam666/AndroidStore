package com.example.androidstore.fragment;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidstore.R;
import com.example.androidstore.adapter.CartExpandAdapter;
import com.example.androidstore.bean.CartInfo;
import com.example.androidstore.bean.CartItem;
import com.example.androidstore.callback.OnClickAddCloseListenter;
import com.example.androidstore.contants.HttpContants;
import com.example.androidstore.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

import static org.greenrobot.eventbus.EventBus.TAG;


public class CartFragment extends Fragment {

    private Unbinder bind;

    @BindView(R.id.cart_expandablelistview)
    ExpandableListView cartExpandablelistview;
    @BindView(R.id.cart_num)
    TextView cartNum;
    @BindView(R.id.cart_money)
    TextView cartMoney;
    @BindView(R.id.cart_shopp_moular)
    Button cartShoppMoular;

    volatile CartInfo cartInfo;
    CartExpandAdapter cartExpandAdapter;
    double price;
    int num;
    volatile String message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        bind = ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {
        cartExpandablelistview.setGroupIndicator(null);
        showData();
    }

    private void showData() {
        cartExpandAdapter = new CartExpandAdapter(getActivity(), cartExpandablelistview);
        cartExpandablelistview.setAdapter(cartExpandAdapter);

        SharedPreferences sp = getActivity().getSharedPreferences("Id", 0);
        message = sp.getString("_Id", "");
        if (!"".equals(message)) {
            loadData();
        }


//        if (cartInfo != null && cartInfo.getData().size() > 0) {
//            cartExpandAdapter = null;
//            showExpandData();
//        } else {
//            try {
//                cartExpandAdapter.notifyDataSetChanged();
//            } catch (Exception e) {
//                return;
//            }
//        }
    }

    private void loadData() {

        OkHttpUtils.get().url(HttpContants.CARTITEM_URL)
                .addParams("id", message)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "似乎没有连网，加载失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        cartInfo = GsonUtils.gsonToBean(response, CartInfo.class);
                        cartExpandAdapter.setList(cartInfo.getData());
                        showExpandData();
                    }
                });
    }

    private void showExpandData() {

        int intgroupCount = cartExpandablelistview.getCount();
        for (int i = 0; i < intgroupCount; i++) {
            cartExpandablelistview.expandGroup(i);
        }

        selectionAll();

        singleSelection();

        deleteItem();

        changeQuantitty();

        showCommodityCalculation();
    }

    private void changeQuantitty() {
        cartExpandAdapter.setOnClickAddCloseListenter(new OnClickAddCloseListenter() {
            @Override
            public void onItemClick(View view, int index, int onePosition, int position, int num) {
                if (index == 1) {
                    if (num > 1) {
                        cartInfo.getData().get(onePosition).getItems().get(position).setQuantity((num - 1));
                        cartExpandAdapter.notifyDataSetChanged();
                    }
                } else {
                    cartInfo.getData().get(onePosition).getItems().get(position).setQuantity((num + 1));
                    cartExpandAdapter.notifyDataSetChanged();
                }
                showCommodityCalculation();
            }
        });
    }

    private void deleteItem() {
        cartExpandAdapter.setOnClickDeleteListenter((view, onePosition, position) -> {
            CartItem cartItem = cartInfo.getData().get(onePosition).getItems().get(position);

            OkHttpUtils.post().url(HttpContants.CARTITEM_DELETE_URL)
                    .addParams("id", cartItem.getId() + "")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(getActivity(), "删除失败", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            cartInfo.getData().get(onePosition).getItems().remove(position);
                            Log.d(TAG, "showExpandData: " + view + "  " + onePosition + "  " + position);
                            cartExpandAdapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();
                        }
                    });


        });
    }

    private void singleSelection() {
        cartExpandAdapter.setOnClickListenterModel((isFlang, view, onePosition, position) -> {
            cartInfo.getData().get(onePosition).getItems().get(position).setIscheck(isFlang);
            int length = cartInfo.getData().get(onePosition).getItems().size();
            for (int i = 0; i < length; i++) {
                if (!cartInfo.getData().get(onePosition).getItems().get(i).ischeck()) {
                    if (!isFlang) {
                        cartInfo.getData().get(onePosition).setIscheck(isFlang);
                    }
                    cartExpandAdapter.notifyDataSetChanged();
                    showCommodityCalculation();
                    return;
                } else {
                    if (i == (length - 1)) {
                        cartInfo.getData().get(onePosition).setIscheck(isFlang);
                        cartExpandAdapter.notifyDataSetChanged();
                    }
                }
            }
            showCommodityCalculation();
        });
    }

    private void selectionAll() {
        cartExpandAdapter.setOnItemClickListener((isFlang, view, position) -> {
            cartInfo.getData().get(position).setIscheck(isFlang);
            int length = cartInfo.getData().get(position).getItems().size();
            for (int i = 0; i < length; i++) {
                cartInfo.getData().get(position).getItems().get(i).setIscheck(isFlang);
            }
            cartExpandAdapter.notifyDataSetChanged();
            showCommodityCalculation();
        });
    }

    @SuppressLint("SetTextI18n")
    private void showCommodityCalculation() {
        price = 0;
        num = 0;
        for (int i = 0; i < cartInfo.getData().size(); i++) {
            for (int j = 0; j < cartInfo.getData().get(i).getItems().size(); j++) {
                if (cartInfo.getData().get(i).getItems().get(j).ischeck()) {
                    price += Double.valueOf((cartInfo.getData().get(i).getItems().get(j).getQuantity()
                            * Double.valueOf(cartInfo.getData().get(i).getItems().get(j).getSpecifications().getPrice())));
                    num++;
                }
            }
        }
        if (price == 0.0) {
            cartNum.setText("共0件商品");
            cartMoney.setText("¥ 0.0");
            return;
        }
        try {
            String money = String.valueOf(price);
            cartNum.setText("共" + num + "件商品");
            if (money.substring(money.indexOf("."), money.length()).length() > 2) {
                cartMoney.setText("¥ " + money.substring(0, (money.indexOf(".") + 3)));
                return;
            }
            cartMoney.setText("¥ " + money.substring(0, (money.indexOf(".") + 2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.cart_shopp_moular)
    public void onClick() {
        Toast.makeText(getActivity(), "提交订单:  " + cartMoney.getText().toString() + "元", Toast.LENGTH_LONG).show();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}