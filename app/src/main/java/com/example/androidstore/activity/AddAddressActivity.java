package com.example.androidstore.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.androidstore.R;
import com.example.androidstore.bean.JsonBean;
import com.example.androidstore.contants.HttpContants;
import com.example.androidstore.utils.GetJsonDataUtil;
import com.example.androidstore.utils.ToastUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import okhttp3.Call;

public class AddAddressActivity extends AppCompatActivity {
    private String id=null;
    private String customerId;
    private EditText addressPersonName;
    private EditText addressPersonPhone;
    private EditText addressPersonDetail;
    private Button saveAddress;
    private TextView addressTv;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_add);
        SharedPreferences preferences = getSharedPreferences("Id", MODE_PRIVATE);
        customerId= preferences.getString("_Id","");
        initJsonData();
        initView();
        editAddress();
        ButterKnife.bind(this);
        addressTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPickerView();
            }
        });
        saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String name = addressPersonName.getText().toString();
                    String phone = addressPersonPhone.getText().toString();
                    String address = addressTv.getText().toString();
                    String addressDetail = addressPersonDetail.getText().toString();
                    if (isEmpty(name, phone, address, addressDetail)) {
                        if (verificationPhone(phone)) {
                            String address1 = address + addressDetail;
                            if (id==null){
                                saveAddress(name, address1, phone);
                                startActivity(new Intent(AddAddressActivity.this, AddressManageActivity.class));

                            }else {
                                updateAddress(name, address1, phone);
                                startActivity(new Intent(AddAddressActivity.this, AddressManageActivity.class));

                            }

                        }
                    }
            }
        });
    }
    private void editAddress(){
        Intent intent=getIntent();
        String name=intent.getStringExtra("addressee");
        String phone=intent.getStringExtra("phone");
        String bigAddress=intent.getStringExtra("bigAddress");
        String smallAddress=intent.getStringExtra("smallAddress");
        id=intent.getStringExtra("id");
        addressPersonPhone.setText(phone);
        addressPersonName.setText(name);
        addressTv.setText(bigAddress);
        addressPersonDetail.setText(smallAddress);
    }
    private boolean verificationPhone(String p){
        String regExp = "13\\d{9}|14[579]\\d{8}|15[0123456789]\\d{8}|17[01235678]\\d{8" +
                "}|18\\d{9}";
        Pattern pattern=Pattern.compile(regExp);
        Matcher matcher=pattern.matcher(p);
        if (matcher.find()){
            return true;
        }else {
            ToastUtils.showToast(this,"请输入有效的号码");
        }
            return false;
    }
    private Boolean isEmpty(String name,String phone,String address,String addressDetail){
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(address)||TextUtils.isEmpty(addressDetail)){
            ToastUtils.showToast(this,"请完整信息");
            return false;
        }else {
            return true;
        }
    }
    private void updateAddress(String name,String address,String phone){
        OkHttpUtils.post()
                .url(HttpContants.ADDRESS_UPDATE)
                .addParams("id",id)
                .addParams("customerId",customerId)
                .addParams("receivingAddress",address)
                .addParams("addressee",name)
                .addParams("phone",phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG",  e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        finish();
                    }
                });

    }
    private void saveAddress(String name,String address,String phone){
        OkHttpUtils.post().
                url(HttpContants.ADDADDRESS_URL)
                .addParams("customerId",customerId)
                .addParams("receivingAddress",address)
                .addParams("addressee",name)
                .addParams("phone",phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG",  e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        finish();
                    }
                });

    }
    private void initView(){
        addressTv=findViewById(R.id.txt_address);
        addressPersonDetail =findViewById(R.id.address_person_detail);
        addressPersonName =findViewById(R.id.address_person_name);
        addressPersonPhone =findViewById(R.id.address_person_phone);
        saveAddress =findViewById(R.id.save_address);
    }
    private void showPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                addressTv.setText(options1Items.get(options1).getPickerViewText() + "  "
                        + options2Items.get(options1).get(options2) + "  "
                        + options3Items.get(options1).get(options2).get(options3));

            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);
        pvOptions.show();
    }


    private void initJsonData() {
        String jsondata = new GetJsonDataUtil().getJson(this, "province.json");

        ArrayList<JsonBean> jsonBean = parseData(jsondata);
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {
            ArrayList<String> citylist = new ArrayList<>();
            ArrayList<ArrayList<String>> provinceArealist = new ArrayList<>();

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {
                String cityname = jsonBean.get(i).getCityList().get(c).getName();
                citylist.add(cityname);
                ArrayList<String> cityArealist = new ArrayList<>();

                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    cityArealist.add("");
                } else {
                    cityArealist.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                provinceArealist.add(cityArealist);
            }

            options2Items.add(citylist);

            options3Items.add(provinceArealist);
        }
    }

    public ArrayList<JsonBean> parseData(String result) {
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

}
