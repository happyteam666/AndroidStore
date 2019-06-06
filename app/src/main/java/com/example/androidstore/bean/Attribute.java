package com.example.androidstore.bean;

import java.io.Serializable;

/**
 * @author mascot
 * @date 2019/6/1 16:53
 */

public class Attribute implements Serializable {


    private long id;


    private String name;


    private long categoryId;

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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
