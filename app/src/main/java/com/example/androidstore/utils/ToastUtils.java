package com.example.androidstore.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.example.androidstore.activity.RegisterActivity;

import static android.widget.Toast.LENGTH_SHORT;


public class ToastUtils {

    private static Toast mToast;

    public static void showSafeToast(final Activity context, final String text) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text, LENGTH_SHORT).show();
            }
        });

    }


    //不安全
    public static void showToast(final Context context, final String text) {
        Toast toast = Toast.makeText(context, text, LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }
}
