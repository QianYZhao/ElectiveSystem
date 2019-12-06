package es.dao.impl;

import es.constant.DAO;
import es.dao.UserDao;
import es.entity.User;

import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    @Override
    public List<Map<String, Object>> get_user_by_id(String user_id) {
        String sql= "select *" +
                "from users " +
                "where user_id = '"+user_id+"'";
        return DAO.baseDao.search(sql);
    }

    @Override
    public boolean userRegister(String userId, String password) {
        String sql = "insert into users " +
                "(user_id,password) values (" + userId+", "+ password+")";
        return DAO.baseDao.execute(sql);
    }
}
