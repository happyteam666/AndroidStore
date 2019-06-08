package com.example.androidstore.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mascot
 * @date 2019/6/1 16:57
 */

public class Specifications implements Serializable {
    private long id;

    private String name;

    private int quantity;

    private long goodsId;

    private Long price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
