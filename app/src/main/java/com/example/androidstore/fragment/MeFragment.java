package com.example.androidstore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidstore.R;
import com.example.androidstore.activity.AddressManageActivity;
import com.example.androidstore.activity.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MeFragment extends Fragment {

    private Unbinder bind;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        bind = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @OnClick({R.id.button_setting, R.id.image_profile, R.id.login_and_register,
            R.id.text_order_unconfirmed, R.id.text_wait_pay, R.id.text_wait_ship,
            R.id.text_shipped, R.id.text_history, R.id.text_manage_address, R.id.text_help})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_setting:
                break;
            case R.id.image_profile:
                break;
            case R.id.login_and_register:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.text_order_unconfirmed:
                break;
            case R.id.text_wait_pay:
                break;
            case R.id.text_wait_ship:
                break;
            case R.id.text_shipped:
                break;
            case R.id.text_history:
                break;
            case R.id.text_manage_address:
                startActivity(new Intent(getActivity(), AddressManageActivity.class));
                break;
            case R.id.text_help:
                break;
            default:

        }
    }
}
