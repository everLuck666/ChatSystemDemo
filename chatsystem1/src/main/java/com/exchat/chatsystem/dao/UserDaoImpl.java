package com.exchat.chatsystem.dao;

import com.exchat.chatsystem.entity.AloneMessage;
import com.exchat.chatsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public int insertPeopleChat(String message) {
        String sql = "insert into chat(name,message) values(?,?)";
        System.out.println("多人聊天插入信息");
        String[] messages = message.split(":");

        return this.jdbcTemplate.update(
                sql,
               messages[0],
                messages[1]
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
        int password = -1;
        String sql = "select * from login where name = ?";

        String userAccountSql="select name,password from login where name=?";

        try{
            Map userAccountMap=(Map)jdbcTemplate.queryForMap(userAccountSql,name);
            password= Integer.parseInt(userAccountMap.get("password").toString()); //本机不用这种方式也可以，但是服务器不行   //取出数据库中char类型的数据转换为String

        }catch(Exception e){
            System.out.println("用户不存在");
      }

       return password;


    }

    @Override
    public List allUser() {
        Map map = null;
        String name = null;
        List list = jdbcTemplate.queryForList("select name from login");
        List names = new ArrayList();
        for(int i = 0;i<list.size();i++){
           map = (Map) list.get(i);
           name = map.get("name").toString();
           names.add(name);

        }

        //System.out.println(list.get(0));

        return names;
    }

    @Override
    public String searchUser(String name) {
        String userAccountSql="select * from login where name=?";

        try{
            Map userAccountMap=(Map)jdbcTemplate.queryForMap(userAccountSql,name);
           return "用户存在";
        }catch(Exception e){
            System.out.println("用户不存在");
        }

        return "用户不存在";
    }

    @Override
    public List readAllPeopleChat() {
        String sql = "select * from chat";
        List list = jdbcTemplate.queryForList(sql);
        List listData = new ArrayList();
        for(int i = 0;i < list.size();i++){
            try{
                Map map1 = (Map)list.get(i);
                System.out.println(map1);
                String user = map1.get("name").toString();
                String message = map1.get("message").toString();
                listData.add(user+":"+message);
            }catch (Exception e){
                System.out.println("出现强制异常");
            }

        }
        return listData;
    }

    @Override
    public int insertAloneMessage(AloneMessage aloneMessage) {
        String sql = "insert into alone values(?,?,?)";
        return this.jdbcTemplate.update(
                sql,
                aloneMessage.getName(),
                aloneMessage.getMessage(),
                aloneMessage.getName2()
        );
    }

    @Override
    public List aloneMessage(String userName, String userName2) {
        String sql = "select * from alone where name = ? and name2 = ? or name = ? and name2=?";
        List list = jdbcTemplate.queryForList(sql,userName,userName2,userName2,userName);
        return list;
    }
}
