package com.example.androidstore.bean;

/**
 * @author mascot
 * @date 2019/6/5 11:02
 */

public class CategoryBrand {
    private long id;

    private long categoryId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    private long brandId;
}
