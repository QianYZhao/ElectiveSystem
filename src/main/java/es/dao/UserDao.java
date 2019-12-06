package es.dao;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<Map<String,Object>> get_user_by_id(String user_id);
    boolean userRegister(String userId,String password);
}


