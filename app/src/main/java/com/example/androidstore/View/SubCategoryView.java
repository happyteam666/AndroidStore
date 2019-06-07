package com.example.androidstore.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.androidstore.Listener.IViewContainer;
import com.example.androidstore.R;
import com.example.androidstore.UI.FlexiScrollView;
import com.example.androidstore.bean.Category;
import java.util.ArrayList;

public class SubCategoryView extends FlexiScrollView
							implements IViewContainer {
	private Category mTopCategory;
	private LinearLayout mChildContainerLl;
	public SubCategoryView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void handleLoadSubCategory(ArrayList<Category> subCategorys) {
		mChildContainerLl.removeAllViews();
		if (subCategorys.size()!=0) {
				int lineNum = subCategorys.size()/3;
				lineNum=subCategorys.size()%3!=0?lineNum+1:lineNum;
				for (int j = 0; j < lineNum; j++) {
					LinearLayout lineLl=new LinearLayout(getContext());
					lineLl.setOrientation(LinearLayout.HORIZONTAL);
					LinearLayout.LayoutParams lineParams=new LinearLayout.LayoutParams(-1, -2);
					lineLl.setLayoutParams(lineParams);
					addColumn(subCategorys, 3*j, lineLl);
					if (3*j+1<subCategorys.size()-1) {
						addColumn(subCategorys, 3*j+1, lineLl);
					}
					if (3*j+2<subCategorys.size()-1) {
						addColumn(subCategorys, 3*j+2, lineLl);
					}
					mChildContainerLl.addView(lineLl);
				}
			}
		}



	private void addColumn(final ArrayList<Category> thirdCategory, final int columnIndex,LinearLayout lineLl) {
		LinearLayout column=new LinearLayout(getContext());
		column.setOrientation(LinearLayout.VERTICAL);
		column.setLayoutParams(new LinearLayout.LayoutParams(getWidth()/2,-2));
		lineLl.addView(column);

		SmartImageView bannerIv=new SmartImageView(getContext());
		bannerIv.setImageUrl(thirdCategory.get(columnIndex).getImage());
		bannerIv.setLayoutParams(new LinearLayout.LayoutParams(-1, getWidth()/2));
		column.addView(bannerIv);
		
		TextView nameTv=new TextView(getContext());
		nameTv.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
		nameTv.setText(thirdCategory.get(columnIndex).getName());
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
		handleLoadSubCategory((ArrayList<Category>) mTopCategory.getCategories());
		}

	}

