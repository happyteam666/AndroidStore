package com.example.androidstore.bean;

import java.io.Serializable;
import java.util.List;


/**
 * @author mascot
 * @date 2019/6/1 15:57
 */

public class Category implements Serializable {

    private long id;


    private String name;
    private List<Category> categories;
    private String image;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private long pid;

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

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                ", image='" + image + '\'' +
                ", pid=" + pid +
                '}';
    }
}
