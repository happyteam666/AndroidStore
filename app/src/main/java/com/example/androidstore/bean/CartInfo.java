package com.example.androidstore.bean;

import java.util.List;


public class CartInfo {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String shopId;
        private String shopName;
        private List<CartItem> items;
        private boolean ischeck = false;

        public boolean ischeck() {
            return ischeck;
        }

        public void setIscheck(boolean ischeck) {
            this.ischeck = ischeck;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public List<CartItem> getItems() {
            return items;
        }

        public void setItems(List<CartItem> items) {
            this.items = items;
        }


    }
}
