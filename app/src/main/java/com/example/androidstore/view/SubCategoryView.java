package com.example.androidstore.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidstore.Listener.IViewContainer;
import com.example.androidstore.R;
import com.example.androidstore.activity.GoodsListActivity;
import com.example.androidstore.bean.Category;
import com.example.androidstore.widget.FlexiScrollView;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;


public class SubCategoryView extends FlexiScrollView
							implements IViewContainer {
	private Category topCategory;
	private LinearLayout childContainerLl;
	public static String CATEGORY_ID="CATEGORY_ID";
	public SubCategoryView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void handleLoadSubCategory(ArrayList<Category> subCategorys) {
		childContainerLl.removeAllViews();
		if (subCategorys.size()!=0) {
				int lineNum = subCategorys.size()/2;
				lineNum=subCategorys.size()%2!=0?lineNum+1:lineNum;
				for (int j = 0; j < lineNum; j++) {
					LinearLayout lineLl=new LinearLayout(getContext());
					lineLl.setOrientation(LinearLayout.HORIZONTAL);
					LinearLayout.LayoutParams lineParams=new LinearLayout.LayoutParams(-1, -2);
					lineLl.setLayoutParams(lineParams);
					addColumn(subCategorys, 2*j, lineLl);
					if (2*j+1<=subCategorys.size()-1) {
						addColumn(subCategorys, 2*j+1, lineLl);
					}
					if (2*j+2<=subCategorys.size()-1) {
						addColumn(subCategorys, 2*j+2, lineLl);
					}
					childContainerLl.addView(lineLl);
				}
			}
		}



	private void addColumn(final ArrayList<Category> Categorys, final int columnIndex,LinearLayout lineLl) {
		LinearLayout column=new LinearLayout(getContext());
		column.setOrientation(LinearLayout.VERTICAL);
		column.setLayoutParams(new LinearLayout.LayoutParams(getWidth()/2,-2));
		lineLl.addView(column);

		SmartImageView bannerIv=new SmartImageView(getContext());
		LinearLayout.LayoutParams bannerIvParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				400);

		bannerIv.setImageUrl(Categorys.get(columnIndex).getImage());
		bannerIv.setLayoutParams(bannerIvParams);
		column.addView(bannerIv);
		
		TextView nameTv=new TextView(getContext());
		nameTv.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
		nameTv.setText(Categorys.get(columnIndex).getName());
		nameTv.setGravity(Gravity.CENTER_HORIZONTAL);
		column.addView(nameTv);
		
		column.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getContext(), GoodsListActivity.class);
				intent.putExtra(CATEGORY_ID,Categorys.get(columnIndex).getId());
				getContext().startActivity(intent);
			}
		});
	}


	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		childContainerLl=findViewById(R.id.child_container_ll);

	}
	@Override
	public void show(Object... values) {
		childContainerLl.removeAllViews();
		topCategory=(Category) values[0];
		handleLoadSubCategory((ArrayList<Category>) topCategory.getCategories());
		}

	}

