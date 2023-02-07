package com.self.project.dao;

import com.self.project.entity.User;

import java.io.IOException;
import java.util.List;

public interface IUserDao {
    public List<User> findAll(User user) throws IOException;

    public void add(User user) throws IOException;

    public void remove(User user) throws IOException;
}
