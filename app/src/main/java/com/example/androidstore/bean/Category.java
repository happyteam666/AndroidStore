package com.example.androidstore.bean;

import java.io.Serializable;


/**
 * @author mascot
 * @date 2019/6/1 15:57
 */

public class Category implements Serializable {

    private long id;


    private String name;


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
}
