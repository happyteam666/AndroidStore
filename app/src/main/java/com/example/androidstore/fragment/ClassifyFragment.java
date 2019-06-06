package com.example.androidstore.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.androidstore.Adapter.CategoryAdapter;
import com.example.androidstore.R;
import com.example.androidstore.Util.Data;
import com.example.androidstore.Util.GSonUtil;
import com.example.androidstore.View.SubCategoryView;
import com.example.androidstore.bean.Category;


import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class ClassifyFragment extends Fragment implements View.OnClickListener {
    private SubCategoryView subCategoryView;
    private ListView mTopCategoryLv;
    private CategoryAdapter mAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classify, container, false);
    }

    @SuppressLint("WrongViewCast")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTopCategoryLv=getActivity().findViewById(R.id.top_lv);
        subCategoryView=getActivity().findViewById(R.id.subcategory);
        mAdapter=new CategoryAdapter(getActivity());
        mAdapter.setBeans((ArrayList<Category>) GSonUtil.getData(Data.getCategorystr()));
        mTopCategoryLv.setAdapter(mAdapter);
        mTopCategoryLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                touchTopCategory(position);
            }
        });

    }
    private void touchTopCategory(int position) {
        mAdapter.mPosition=position;
        mAdapter.notifyDataSetChanged();
        Category topCategory = (Category) mAdapter.getItem(position);
        subCategoryView.show(topCategory);
    }

    @Override
    public void onClick(View view) {

    }
}
