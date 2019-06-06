package com.example.androidstore.bean;

import java.io.Serializable;

/**
 * @author mascot
 * @date 2019/6/1 15:31
 */

public class Customer implements Serializable {
    private long id;

    private String phone;

    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

}
