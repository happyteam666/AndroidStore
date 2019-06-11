package com.example.androidstore.bean;

import java.io.Serializable;

/**
 * @author mascot
 * @date 2019/6/1 15:42
 */

public class Brand implements Serializable {

    private long id;


    private String name;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

}
