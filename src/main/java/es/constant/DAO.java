package es.constant;

import es.dao.*;
import es.dao.impl.*;
import es.entity.Student;

public class DAO {
    public static BaseDao baseDao= new BaseDaoImpl();
    public static StudentDao studentDao=new StudentDaoImpl();
    public static TeacherDao teacherDao =new TeacherDaoImpl();
    public static UserDao userDao= new UserDaoImpl();
    public static ManagerDao managerDao= new ManagerDaoImpl();
}
