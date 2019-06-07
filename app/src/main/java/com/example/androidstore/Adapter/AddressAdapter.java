package com.example.androidstore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidstore.R;
import com.example.androidstore.View.SmartImageView;
import com.example.androidstore.bean.Address;
import com.example.androidstore.bean.Goods;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends BaseAdapter {
    private Context context;
    public int mPosition;
    private List<Address> datas=new ArrayList<>();

    public AddressAdapter(Context c) {
        context=c;
    }

    public void setBeans(List<Address> goods) {
        datas=goods;
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

    class ViewHolder{
        SmartImageView smIv;
        TextView nameTv;
        TextView priceTv;
        TextView commentrateTv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        GoodsAdapter.ViewHolder holder=null;
//        GoodsAdapter.ViewHolder holer=null;
//        if (convertView==null) {
//            convertView= LayoutInflater.from(mContext).inflate(R.layout.product_lv_item, null);
//            holer=new AddressAdapter.ViewHolder();
//            holer.smIv=convertView.findViewById(R.id.product_iv);
//            holer.nameTv=convertView.findViewById(R.id.name_tv);
//            holer.priceTv= convertView.findViewById(R.id.price_tv);
//            holer.commentrateTv=convertView.findViewById(R.id.commrate_tv);
//            convertView.setTag(holer);
//        }else {
//            holer=(GoodsAdapter.ViewHolder) convertView.getTag();
//        }
//        Goods bean = mDatas.get(position);
//
//        holer.smIv.setImageUrl(bean.getImage());
//        holer.nameTv.setText(bean.getName());
////        holer.priceTv.setText("¥ "+bean.getPrice());
        return convertView;
    }
}
