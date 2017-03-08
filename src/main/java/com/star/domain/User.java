package com.star.domain;

/**
 * Created by hp on 2017/3/8.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String ROLE_USER;
    private int enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getROLE_USER() {
        return ROLE_USER;
    }

    public void setROLE_USER(String ROLE_USER) {
        this.ROLE_USER = ROLE_USER;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
