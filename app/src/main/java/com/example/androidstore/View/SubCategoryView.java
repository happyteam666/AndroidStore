package com.example.androidstore.View;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.androidstore.Listener.IViewContainer;
import com.example.androidstore.R;
import com.example.androidstore.UI.FlexiScrollView;
import com.example.androidstore.bean.Category;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class SubCategoryView extends FlexiScrollView
							implements IViewContainer {
	private Category mTopCategory;//该对象是左边列表传递过来的，主要是获取分类id 和右边头部的图片
	private LinearLayout mChildContainerLl;//布局里的容器id
	public SubCategoryView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	private void handleLoadSubCategory(ArrayList<Category> subCategorys) {
		//1.删除原来内部的所有子控件
		mChildContainerLl.removeAllViews();
		if (subCategorys.size()!=0) {
			//2.添加头部图片
			ImageView iv=new ImageView(getContext());
			iv.setLayoutParams(new LinearLayout.LayoutParams(-1,110));
			iv.setScaleType(ScaleType.FIT_XY);
			mChildContainerLl.addView(iv);
//			for (int i = 0; i < subCategorys.size(); i++) {
//				//添加2级分类文本  如裙装 上装 下装等
//				Category subCategory = subCategorys.get(i);
//				TextView titleTv=new TextView(getContext());
//				LinearLayout.LayoutParams titleTvParams = new LinearLayout.LayoutParams(-2, -2);
//				titleTvParams.setMargins(0, 30, 0, 0);
//				titleTv.setLayoutParams(titleTvParams);
//				titleTv.setText(subCategory.getCname()+"");
//				mChildContainerLl.addView(titleTv);
				//添加9宫格
//				ArrayList<Category> thirdCategory = subCategory.getThirdCategory();
//					int lineNum = thirdCategory.size()/3;
//					lineNum=thirdCategory.size()%3!=0?lineNum+1:lineNum;
//					for (int j = 0; j < lineNum; j++) {
//						//根据行号为2级分类创建行
//						LinearLayout lineLl=new LinearLayout(getContext());
//						lineLl.setOrientation(LinearLayout.HORIZONTAL);
//						LinearLayout.LayoutParams lineParams=new LinearLayout.LayoutParams(-1, -2);
//						lineLl.setLayoutParams(lineParams);
//						//添加第 1 列
//						addColumn(thirdCategory, 3*j, lineLl);
//						//添加第2列
//						if (3*j+1<thirdCategory.size()-1) {
//							addColumn(thirdCategory, 3*j+1, lineLl);
//						}
//						//添加第3列
//						if (3*j+2<thirdCategory.size()-1) {
//							addColumn(thirdCategory, 3*j+2, lineLl);
//						}
//						mChildContainerLl.addView(lineLl);
//				}
			}
		}


	private void addColumn(final ArrayList<Category> thirdCategory, final int columnIndex,LinearLayout lineLl) {
		LinearLayout column=new LinearLayout(getContext());
		column.setOrientation(LinearLayout.VERTICAL);
		column.setLayoutParams(new LinearLayout.LayoutParams(getWidth()/3,-2));
		lineLl.addView(column);
		
		ImageView bannerIv=new ImageView(getContext());
		bannerIv.setLayoutParams(new LinearLayout.LayoutParams(-1, getWidth()/3));
		column.addView(bannerIv);
		
		TextView nameTv=new TextView(getContext());
		nameTv.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
		nameTv.setText(thirdCategory.get(columnIndex).getCname());
		nameTv.setGravity(Gravity.CENTER_HORIZONTAL);
		column.addView(nameTv);
		
		column.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View v) {
//				String categoryId = thirdCategory.get(columnIndex).getId();
//				Intent intent=new Intent(getContext(),ProductListActivity.class);
//				intent.putExtra(IntentValues.TO_PRODUCT_LIST_KEY, categoryId);
//				intent.putExtra(IntentValues.TOP_CATEGORY, mTopCategory.getId());
//				getContext().startActivity(intent);
			}
		});
	}


	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		mChildContainerLl=findViewById(R.id.child_container_ll);

	}
	@Override
	public void show(Object... values) {
		mChildContainerLl.removeAllViews();
		mTopCategory=(Category) values[0];
		TextView textView=new TextView(getContext());
		textView.setText(mTopCategory.getCname());
		mChildContainerLl.addView(textView);

	}
}
