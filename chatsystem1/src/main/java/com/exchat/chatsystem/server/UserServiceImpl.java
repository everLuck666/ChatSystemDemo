package com.exchat.chatsystem.server;

import com.exchat.chatsystem.dao.UserDao;
import com.exchat.chatsystem.entity.AloneMessage;
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
    public String getByName(String name) {
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

    @Override
    public String searchUser(String name) {

        return userDao.searchUser(name);
    }

    @Override
    public int insertPeopleChat(String message) {
        return userDao.insertPeopleChat(message);
    }

    @Override
    public List readAllPeopleChat() {
        return userDao.readAllPeopleChat();
    }

    @Override
    public int insertAloneMessage(AloneMessage aloneMessage) {
        return userDao.insertAloneMessage(aloneMessage);
    }

    @Override
    public List aloneMessage(String userName, String userName2) {
        return userDao.aloneMessage(userName,userName2);
    }

    @Override
    public int insertOnline(String name) {
        return userDao.insertOnline(name);
    }

    @Override
    public List readOnline() {
        return userDao.readOnline();
    }

    @Override
    public int deleteOnline(String name) {
        return userDao.deleteOnline(name);
    }

    @Override
    public int searchOnline(String name) {
        return userDao.searchOnline(name);
    }
}
