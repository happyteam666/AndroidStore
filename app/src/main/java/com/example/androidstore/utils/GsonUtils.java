package com.example.androidstore.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GsonUtils {
    private static Gson gson = new Gson();

    public static String GsonString(Object object) {
        return gson.toJson(object);
    }


    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        return gson.fromJson(gsonString, cls);
    }


    public static <T> List<T> GsonToList(String gsonString, Class<T[]> clazz) {
        T[] arr = gson.fromJson(gsonString, clazz);
        return Arrays.asList(arr);
    }


    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
            }.getType());
        }
        return list;
    }

    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}
