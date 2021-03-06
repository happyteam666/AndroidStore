package com.example.androidstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidstore.R;
import com.example.androidstore.activity.AddAddressActivity;
import com.example.androidstore.activity.AddressManageActivity;
import com.example.androidstore.bean.Address;
import com.example.androidstore.contants.HttpContants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class AddressAdapter extends BaseAdapter {
    private Context context;
    private String bigAddress = "";
    private String smallAddress = "";
    private List<Address> datas = new ArrayList<>();

    public AddressAdapter(Context c) {
        context = c;
    }

    public void setBeans(List<Address> goods) {
        datas = goods;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView nameTv;
        TextView phoneTv;
        TextView addressTv;
        TextView addressEditTv;
        TextView addressDeleteTv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AddressAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.template_address, null);
            holder = new AddressAdapter.ViewHolder();
            holder.nameTv = convertView.findViewById(R.id.address_name);
            holder.phoneTv = convertView.findViewById(R.id.address_phone);
            holder.addressTv = convertView.findViewById(R.id.address_address);
            holder.addressEditTv = convertView.findViewById(R.id.address_edit);
            holder.addressDeleteTv = convertView.findViewById(R.id.address_delete);
            convertView.setTag(holder);
        } else {
            holder = (AddressAdapter.ViewHolder) convertView.getTag();
        }
        Address bean = datas.get(position);
        holder.nameTv.setText(bean.getAddressee());
        holder.phoneTv.setText(bean.getPhone());
        holder.addressTv.setText(bean.getReceivingAddress());
        holder.addressEditTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char[] str = bean.getReceivingAddress().toCharArray();
                for (int i = 0; i < 13; i++) {
                    bigAddress = bigAddress + str[i];
                }
                for (int i = 13; i < str.length; i++) {
                    smallAddress = smallAddress + str[i];
                }
                Intent intent = new Intent(context, AddAddressActivity.class);
                intent.putExtra("addressee", bean.getAddressee());
                intent.putExtra("phone", bean.getPhone());
                intent.putExtra("bigAddress", bigAddress);
                intent.putExtra("smallAddress", smallAddress);
                intent.putExtra("id", bean.getId() + "");
                context.startActivity(intent);
                smallAddress = "";
                bigAddress = "";
            }
        });
        holder.addressDeleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAddress(bean.getId().toString());
                Intent intent = new Intent(context, AddressManageActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private void deleteAddress(String id) {
        OkHttpUtils.post()
                .url(HttpContants.ADDRESS_DELETE)
                .addParams("id", id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }
}
