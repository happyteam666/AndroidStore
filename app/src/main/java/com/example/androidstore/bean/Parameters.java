package com.example.androidstore.bean;

/**
 * @author mascot
 * @date 2019/6/1 17:02
 */

public class Parameters {

    private long id;

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

    private String name;

    private long categoryId;

}
