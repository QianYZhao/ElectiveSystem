package es.dao.impl;

import es.dao.UserDao;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    UserDao dao= new UserDaoImpl();
    String user_id="S100";

    @Test
    void get_user_by_id() {
        List<Map<String,Object>> list= dao.get_user_by_id(user_id);
        System.out.println(list.size());
    }

    @Test
    void userRegister() {
        user_id ="S80007";
        String password ="mysql@123456";
        System.out.println(dao.userRegister(user_id,password));

    }
}