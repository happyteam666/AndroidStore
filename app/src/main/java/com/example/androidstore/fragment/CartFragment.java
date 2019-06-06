package com.example.androidstore.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.androidstore.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CartFragment extends Fragment {

    private Unbinder bind;

    @BindView(R.id.rv_bottom)
    RelativeLayout mRvBottom;
    @BindView(R.id.ll_empty)
    LinearLayout mLlEmpty;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart,container, false);
        bind = ButterKnife.bind(this, view);
        init();
        return view;
    }

    protected void init() {
        initEmptyView();
    }


    private void initEmptyView() {
        mRvBottom.setVisibility(View.GONE);
        mLlEmpty.setVisibility(View.VISIBLE);
    }
}
