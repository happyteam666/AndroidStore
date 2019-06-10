package com.example.androidstore.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidstore.R;
import com.example.androidstore.View.SmartImageView;
import com.example.androidstore.bean.CartItem;
import com.example.androidstore.bean.Goods;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private List<CartItem> datas=new ArrayList<>();


    public CartAdapter(Context c) {
        context=c;
    }

    public void setBeans(List<CartItem> cartItem) {
        datas=cartItem;
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

        CheckBox checkBox;

        public SmartImageView viewtv;
        public TextView titletv;
        public TextView pricetv;
        public TextView quantitytv;

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holer=null;

        if (convertView==null) {
            convertView=LayoutInflater.from(context).inflate(R.layout.template_cart, null);
            holer=new ViewHolder();
            holer.viewtv=convertView.findViewById(R.id.iv_view);
            holer.titletv=convertView.findViewById(R.id.text_title);
            holer.pricetv= convertView.findViewById(R.id.text_price);
            holer.quantitytv = convertView.findViewById(R.id.text_quantity);
            holer.checkBox=convertView.findViewById(R.id.checkbox);
            convertView.setTag(holer);
        }else {
            holer=(ViewHolder) convertView.getTag();
        }
        CartItem bean = datas.get(position);

        holer.viewtv.setImageUrl(bean.getImage());
        holer.titletv.setText(bean.getName());
        holer.pricetv.setText("¥ "+(double)bean.getPrice()/100.00+"0");
        holer.quantitytv.setText("x"+bean.getQuantity());
        holer.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }
}