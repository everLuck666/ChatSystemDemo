package com.exchat.chatsystem.dao;

import com.exchat.chatsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(User user) {
        String sql = "insert into login(name,password) values(?,?)";
        return this.jdbcTemplate.update(
                sql,
                user.getUsername(),
                user.getPassword()
        );

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
    public int  getByName(String name) {
        System.out.println("woshi" + name);
        String sql = "select * from login where name = ?";

        String userAccountSql="select name,password from login where name=?";
        Map userAccountMap=(Map)jdbcTemplate.queryForMap(userAccountSql,name);
        int password= (int)userAccountMap.get("password");    //取出数据库中char类型的数据转换为String
       return password;


    }

    @Override
    public List allUser() {
        List list = jdbcTemplate.queryForList("select * from login");

        //System.out.println(list.get(0));
        return list;
    }
}
