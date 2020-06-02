package com.exchat.chatsystem.server;

import com.exchat.chatsystem.entity.AloneMessage;
import com.exchat.chatsystem.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    int insert(User user);

    int deleteById(Integer id);

    int update(User user);

    String getByName(String name);
    User getByNameOne(String name);
    List allUser();
    String searchUser(String name);
    int insertPeopleChat(String message);
    List readAllPeopleChat();
    int insertAloneMessage(AloneMessage aloneMessage);

    List aloneMessage(String userName,String userName2);
}
