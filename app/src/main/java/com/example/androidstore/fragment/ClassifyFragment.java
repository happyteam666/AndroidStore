package com.example.androidstore.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.androidstore.Adapter.CategoryAdapter;
import com.example.androidstore.R;
import com.example.androidstore.Util.GsonUtils;
import com.example.androidstore.View.SubCategoryView;
import com.example.androidstore.bean.Category;
import com.example.androidstore.contants.HttpContants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import okhttp3.Call;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;


public class ClassifyFragment extends Fragment implements View.OnClickListener {
    private SubCategoryView subCategoryView;
    private ListView topCategoryLv;
    private CategoryAdapter adapter;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        initView(view);
        loadCategory();
        return view;

    }

    private void initView(View view) {
        topCategoryLv = view.findViewById(R.id.top_lv);
        subCategoryView = view.findViewById(R.id.subcategory);
        adapter = new CategoryAdapter(getActivity());


    }

    @SuppressLint("WrongViewCast")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        topCategoryLv.setOnItemClickListener((adapterView, view, i, l) -> touchTopCategory(i));
    }

    private void touchTopCategory(int position) {
        adapter.mPosition = position;
        adapter.notifyDataSetChanged();
        Category topCategory = (Category) adapter.getItem(position);
        subCategoryView.show(topCategory);
    }

    @Override
    public void onClick(View view) {

    }

    private void loadCategory() {
        OkHttpUtils.get().url(HttpContants.CATEGORY_URL)
                .addParams("pid","0")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "onResponse: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        adapter.setBeans( GsonUtils.GsonToList(response,Category[].class));
                        topCategoryLv.setAdapter(adapter);
                        topCategoryLv.performItemClick(null,0,0);
                    }
                });
    }
}
