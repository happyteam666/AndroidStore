package com.example.androidstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidstore.R;
import com.example.androidstore.bean.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends BaseAdapter {
    private Context context;
    public int mPosition;
    private List<Category> datas=new ArrayList<>();

    public CategoryAdapter(Context c) {
        context=c;
    }

    public void setBeans(List<Category> rBaseCategorys) {
        datas=rBaseCategorys;
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
        public TextView titleTv;
        public View dividerTv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.top_category_item, null);
            holder=new ViewHolder();
            holder.titleTv= convertView.findViewById(R.id.tv);
            holder.dividerTv= convertView.findViewById(R.id.divider);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        if (mPosition==position) {
            holder.titleTv.setBackgroundResource(R.drawable.tongcheng_all_bg01);
            holder.dividerTv.setVisibility(View.INVISIBLE);
        }else {
            holder.titleTv.setBackgroundColor(0xf4f4f4);
            holder.dividerTv.setVisibility(View.VISIBLE);
        }
        holder.titleTv.setText(datas.get(position).getName());

        holder.titleTv.setSelected(mPosition==position);
        return convertView;
    }

}
