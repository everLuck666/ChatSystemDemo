package com.exchat.chatsystem.server;

import com.exchat.chatsystem.dao.UserDao;
import com.exchat.chatsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public User getByNameOne(String name) {
        return null;
    }

    @Override
    public List allUser() {
        return userDao.allUser();
    }
}
