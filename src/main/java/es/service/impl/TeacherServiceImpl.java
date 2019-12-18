package es.service.impl;

import es.constant.DAO;
import es.entity.Student;
import es.entity.User;
import es.service.TeacherService;
import es.service.UserService;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class TeacherServiceImpl implements TeacherService, UserService {

    @Override
    public boolean login(User user) {
        List<Map<String,Object>> list= DAO.userDao.get_user_by_id(user.getId());
        if (list.size()>0){
            String password = (String) list.get(0).get("password");
            if(password.equals(user.getPassword()))
                return true;
        }
        return false;//不存在或者是密码错误
    }

    @Override
    public boolean quit() {
        //这里可以销毁到session_id
        return true;
    }

    @Override
    public List<Map<String, Object>> getStudentRoster(String section_id){
       return DAO.teacherDao.getStudentRoster(section_id);
    }

    @Override
    public List<Map<String, Object>> getSectionApplication(String section_id) {
       return DAO.teacherDao.getSectionApplication(section_id);
    }

    @Override
    public boolean importGrades(String section_id, String student_id, String grade) {
        return DAO.teacherDao.importGrades(student_id,section_id,grade);
    }
}
