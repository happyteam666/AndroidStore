package com.example.androidstore.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import com.example.androidstore.activity.RegisterActivity;

import static android.widget.Toast.LENGTH_SHORT;


public class ToastUtils {

    public static void showToast(final Context context, final String text) {
        Toast toast = Toast.makeText(context, text, LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }
}
