package com.example.androidstore.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.androidstore.R;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class SplashActivity extends Activity {

    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        mIv = findViewById(R.id.logo_iv);
        alphaAnim();

        initOkHttpClient();
    }

    private void initOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS) //链接超时
                .readTimeout(10000L, TimeUnit.MILLISECONDS) //读取超时
                .build(); //其他配置

        OkHttpUtils.initClient(okHttpClient);
    }

    private void alphaAnim() {
        Animation anim = new AlphaAnimation(0.2f, 1.0f);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //启动Main
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim.setDuration(2000);
        anim.setFillAfter(true);
        mIv.startAnimation(anim);
    }

}
