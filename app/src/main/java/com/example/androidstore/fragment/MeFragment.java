package com.example.androidstore.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidstore.R;
import com.example.androidstore.activity.AddressManageActivity;
import com.example.androidstore.activity.LoginActivity;
import com.example.androidstore.activity.MainActivity;
import com.example.androidstore.activity.RecordingActivity;
import com.example.androidstore.activity.SettingActivity;
import com.example.androidstore.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;
import static com.example.androidstore.R.id.content;
import static com.example.androidstore.R.id.login_and_register;


public class MeFragment extends Fragment {

    private Unbinder bind;
    //验证是否登录
    String validID;

    @BindView(R.id.login_and_register)
    TextView username;

    String message;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        bind = ButterKnife.bind(this, view);
        visable();
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
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.image_profile:
                break;
            case login_and_register:
                SharedPreferences sp = getActivity().getSharedPreferences("Id",0);
                validID = sp.getString("_Id","");
                if(validID.equals(""))
                startActivity(new Intent(getActivity(), LoginActivity.class));
                else
                    ToastUtils.showToast(getActivity(),"用户已登录！");
                break;
            case R.id.text_order_unconfirmed:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case R.id.text_wait_pay:
                break;
            case R.id.text_wait_ship:
                break;
            case R.id.text_shipped:
                break;
            case R.id.text_history:
                startActivity(new Intent(getActivity(), RecordingActivity.class));
                break;
            case R.id.text_manage_address:
                if(!"".equals(validID)){
                    startActivity(new Intent(getActivity(), AddressManageActivity.class));
                }
                else {
                    ToastUtils.showToast(getActivity(),"你还没登录");
                }
                break;
            case R.id.text_help:
                break;

            default:

        }
    }

    public void visable() {
        if (isValid()) {
            username.setText(message);
        }
    }

    public boolean isValid() {

        SharedPreferences sp = getActivity().getSharedPreferences("Id", 0);
        message = sp.getString("_Name", "");
        if ("".equals(message)) {
            return false;
        } else {
            return true;
        }
    }

}
