package com.example.androidstore.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import com.example.androidstore.R;
import com.example.androidstore.adapter.RecordingAdapter;
import com.example.androidstore.bean.Recording;
import com.example.androidstore.contants.HttpContants;
import com.example.androidstore.utils.GsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class RecordingActivity extends AppCompatActivity {



    @BindView(R.id.recording_recycler_view)
    RecyclerView recordingRecyclerView;
    @BindView(R.id.toolbar_recycler_view)
    Toolbar toolbar;

    RecordingAdapter adapter;
    List<Recording> data;

    String customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        SharedPreferences sp = this.getSharedPreferences("Id", 0);
        customerId =  sp.getString("_Id","");
        if (!"".equals(customerId)) {
            loadData();
        }
    }

    private void loadData() {
        OkHttpUtils.get().url(HttpContants.RECORE_URL)
                .addParams("id", customerId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        data = GsonUtils.GsonToList(response, Recording[].class);
                        adapter.setRecordingItems(data);
                    }
                });
    }

    private void initView() {
        toolbar.setTitle("浏览记录");
        if (getScreenWidthDp() >= 1200) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recordingRecyclerView.setLayoutManager(gridLayoutManager);
        } else if (getScreenWidthDp() >= 800) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            recordingRecyclerView.setLayoutManager(gridLayoutManager);
        } else {
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recordingRecyclerView.setLayoutManager(linearLayoutManager);
        }

        adapter = new RecordingAdapter(this);
        recordingRecyclerView.setAdapter(adapter);

    }

    private int getScreenWidthDp() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (displayMetrics.widthPixels / displayMetrics.density);
    }
}
