package com.example.androidstore.contants;


public abstract class HttpContants {

    public static final String BASE_URL = "http://47.101.176.1:8088/";       //url的基类

    public static final String LOGIN_URL=BASE_URL+"customer/login";

    public static final String REGISTER_URL =BASE_URL+"customer/register";

    public static final String GOODS_URL=BASE_URL+"goods";

    public static final String GOODS_BY_CATEGORY_URL=BASE_URL+"goods/category";
    public static final String GOODS_BY_BRAND_URL=BASE_URL+"goods/brand";


    public static final String CATEGORY_URL=BASE_URL+"category/pid";

    public static final String BRAND_URL=BASE_URL+"brand";
    public static final String CARTITEM_URL=BASE_URL+"cart/customer";



    public static final String ADDADDRESS_URL=BASE_URL+"address/add";
    public static final String ADDRESS_BY_CUSTOMER_URL=BASE_URL+"address/customer";
    public static final String ADDRESS_UPDATE=BASE_URL+"address/update";
    public static final String ADDRESS_DELETE=BASE_URL+"address/delete";

    public static final String ADDORDER_URL=BASE_URL+"order/add";

}
