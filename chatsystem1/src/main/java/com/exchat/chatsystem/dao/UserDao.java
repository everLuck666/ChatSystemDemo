package com.exchat.chatsystem.dao;

import com.exchat.chatsystem.entity.AloneMessage;
import com.exchat.chatsystem.entity.User;

import java.util.List;

public interface UserDao {
    int insert(User user);
    int insertPeopleChat(String message);

    int deleteById(Integer id);

    int update(User user);
    String getByName(String name);
    List allUser();
    String searchUser(String name);

    List readAllPeopleChat();

    int insertAloneMessage(AloneMessage aloneMessage);

    List aloneMessage(String userName,String userName2);

    int insertOnline(String name);
    List readOnline();
    int deleteOnline(String name);
    int searchOnline(String name);

}
