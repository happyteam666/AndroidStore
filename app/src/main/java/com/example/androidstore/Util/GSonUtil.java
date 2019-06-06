package com.example.androidstore.Util;

import com.example.androidstore.bean.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GSonUtil {
    public static List<Category> getData(String jsonStr){
        Gson gson = new Gson();
        List<Category>list = gson.fromJson(jsonStr,new TypeToken<List<Category>>() {}.getType());
        return list;

    }
}
