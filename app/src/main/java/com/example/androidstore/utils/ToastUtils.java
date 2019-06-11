package com.example.androidstore.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;


public class ToastUtils {

    public static void showToast(final Context context, final String text) {
        Toast toast = Toast.makeText(context, text, LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }
}
