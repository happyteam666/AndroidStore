package com.example.androidstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.androidstore.R;
import com.example.androidstore.bean.Specifications;

import java.util.ArrayList;
import java.util.List;

public class SpecificationAdapter extends BaseAdapter {

	private List<Specifications> datas=new ArrayList<Specifications>();
	private Context context;
	public int mPosition=-1;

	public SpecificationAdapter(Context c) {
		this.context = c;
	}
	
	public void setDatas(ArrayList<Specifications> mDatas) {
		this.datas = mDatas;
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
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Button brandNameTv=null;
		if (convertView==null) {
			convertView=LayoutInflater.from(context).inflate(R.layout.goods_specification_item, null);
			brandNameTv=convertView.findViewById(R.id.specification_tv);
			convertView.setTag(brandNameTv);
		}else {
			brandNameTv=(Button) convertView.getTag();
		}
		brandNameTv.setText(datas.get(position).getName());
		brandNameTv.setSelected(mPosition!=-1&&mPosition==position);
		return convertView;
	}

}
