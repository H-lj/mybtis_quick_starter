package com.self.project.entity;

public class User {
    private Integer id;
    private String username;

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getusername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }
}

