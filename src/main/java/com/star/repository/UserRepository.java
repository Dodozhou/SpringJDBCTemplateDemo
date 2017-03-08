package com.star.repository;

import com.star.domain.User;

/**
 * Created by hp on 2017/3/8.
 */
public interface UserRepository {
    public void addUser(User user);
    public void deleteUserByUserName(String userName);
    public User findUserByUserName(String userName);
    public void updateUser(User user);
}
