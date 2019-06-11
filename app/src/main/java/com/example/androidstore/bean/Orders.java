package com.example.androidstore.bean;

import java.io.Serializable;

/**
 * @author mascot
 * @date 2019/6/1 16:09
 */

public class Orders implements Serializable {

    private long id;

    private int status;

    // todo
    private long customerId;

    private String totalPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }


}
