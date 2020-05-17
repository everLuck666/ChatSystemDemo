package com.exchat.chatsystem.server;

import com.exchat.chatsystem.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    int insert(User user);

    int deleteById(Integer id);

    int update(User user);

    int getByName(String name);
    User getByNameOne(String name);
    List allUser();
}
