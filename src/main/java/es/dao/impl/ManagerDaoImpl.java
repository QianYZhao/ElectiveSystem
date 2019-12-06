package es.dao.impl;


import es.constant.DAO;
import es.dao.ManagerDao;
import es.entity.Course;
import es.entity.Section;
import es.entity.Student;
import es.entity.Teacher;

import java.util.List;
import java.util.Map;

public class ManagerDaoImpl implements ManagerDao {
    @Override
    public boolean addStudents(Student student) {
        //向student插入数据，如果不成功便返回false，如果成功就向user中插入数据，如果向user
        //插入失败，删除插入students操作，如果成果便返回
      String sql ="insert into student values("+
              student.getId()+","+
              student.getName()+","+
              student.getEntrance_year()+","+
              student.getDept_name()+")";
      boolean bool= DAO.baseDao.execute(sql);

      if(bool){
          sql ="insert into users values("+
                  student.getId()+" ,"+
                  "'123456' )";
          bool=DAO.baseDao.execute(sql);
          if (bool==true){
              return true;
          } else {
              sql= "delete * from student" +
                      " where student_id ="+
                      student.getId();
              while (true){
                  if (DAO.baseDao.execute(sql))
                      return false;
              }
          }

      }else {
          return false;
      }



    }

    @Override
    public boolean addTeachers(Teacher teacher) {
        String sql ="insert into instructor values("+
                teacher.getId()+","+
                teacher.getName()+","+
                teacher.getDept_name()+")\n"+

                "insert into users values("+
                    teacher.getId()+" ,"+
                    "'123456' )";
        boolean bool= DAO.baseDao.execute(sql);
        return bool;
    }

    @Override
    public boolean addSections(Section section) {
        String sql= "insert into section values("+
                section.getSection_id()+" ,"+
                section.getCourse_id()+","+
                section.getMax_students()+","+
                section.getClass_times()+","+
                section.getYear()+","+
                section.getEvaluation_mode()+","+
                section.getSemester()+")";
        return DAO.baseDao.execute(sql);
    }


    @Override
    public boolean deleteSection(String section_id) {

        String sql= "delete form section " +
                "where section_id=" +
                section_id;
        return DAO.baseDao.execute(sql);
    }

    @Override
    public boolean deleteCourse(String course_id) {
        String sql= "delete from course " +
                "where course_id ="+
                course_id;
        return DAO.baseDao.execute(sql);

    }

    @Override
    public boolean addCourse(Course course) {
        String sql= "insert into course values("+
                course.getCourse_id()+","+
                course.getCourse_name()+","+
                course.getType()+","+
                course.getCredit()+","+
                course.getDept_name()+" )";
        return DAO.baseDao.execute(sql);
    }

    @Override
    public List<Map<String, Object>> getSameSemesterSections(Section section) {
        String sql = "select *  from  section " +
                "where year ="+section.getYear()+
                "and semester = "+section.getSemester();
        return DAO.baseDao.search(sql);
    }
}
