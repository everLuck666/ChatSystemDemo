package com.exchat.chatsystem.dao;

import com.exchat.chatsystem.entity.User;

import java.util.List;

public interface UserDao {
    int insert(User user);

    int deleteById(Integer id);

    int update(User user);
    int getByName(String name);
    List allUser();

}
