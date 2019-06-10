package com.example.androidstore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidstore.R;
import com.example.androidstore.View.SmartImageView;
import com.example.androidstore.bean.Goods;

import java.util.ArrayList;
import java.util.List;

public class GoodsAdapter extends BaseAdapter {
    private Context context;
    private List<Goods> datas=new ArrayList<>();

    public GoodsAdapter(Context c) {
        context=c;
    }

    public void setBeans(List<Goods> goods) {
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
        ViewHolder holer=null;
        if (convertView==null) {
            convertView=LayoutInflater.from(context).inflate(R.layout.product_lv_item, null);
            holer=new ViewHolder();
            holer.smIv=convertView.findViewById(R.id.product_iv);
            holer.nameTv=convertView.findViewById(R.id.name_tv);
            holer.priceTv= convertView.findViewById(R.id.price_tv);
            holer.commentrateTv=convertView.findViewById(R.id.commrate_tv);
            convertView.setTag(holer);
        }else {
            holer=(ViewHolder) convertView.getTag();
        }
        Goods bean = datas.get(position);

        holer.smIv.setImageUrl(bean.getImage());
        holer.nameTv.setText(bean.getName());
        holer.priceTv.setText("¥ "+bean.getSpecificationsList().get(0).getPrice());
        return convertView;
    }
    }
