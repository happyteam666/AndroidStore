package com.example.androidstore.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidstore.R;
import com.example.androidstore.activity.AddressManageActivity;
import com.example.androidstore.activity.LoginActivity;
import com.example.androidstore.activity.MainActivity;
import com.example.androidstore.activity.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.androidstore.R.id.login_and_register;


public class MeFragment extends Fragment {

    private Unbinder bind;

    @BindView(R.id.login_and_register)
            TextView username;

    String message;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        bind = ButterKnife.bind(this, view);
        Visable();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @OnClick({R.id.button_setting, R.id.image_profile, login_and_register,
            R.id.text_order_unconfirmed, R.id.text_wait_pay, R.id.text_wait_ship,
            R.id.text_shipped, R.id.text_history, R.id.text_manage_address, R.id.text_help,R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.image_profile:
                break;
            case login_and_register:
                startActivity(new Intent(getActivity(), LoginActivity.class));
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
                break;
            case R.id.text_manage_address:
                startActivity(new Intent(getActivity(), AddressManageActivity.class));
                break;
            case R.id.text_help:
                break;
            case R.id.exit: {
                SharedPreferences sp = getActivity().getSharedPreferences("Id", 0);
                if(!sp.getString("_Id","").equals("")) {
                    Exit();
                    startActivity(new Intent(getActivity(), MainActivity.class));

                }else{}
                break;
            }
            default:

        }
    }

    public void Visable(){
        if(isValid()){
            username.setText(message);
        }
    }

    public boolean isValid(){

        SharedPreferences sp = getActivity().getSharedPreferences("Id", 0);
        message = sp.getString("_Name", "");
        if(message.equals("")){
            return false;
        }else{
            return true;
        }
    }

    public void Exit(){

        SharedPreferences sp = getActivity().getSharedPreferences("Id",0);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();

    }
}
