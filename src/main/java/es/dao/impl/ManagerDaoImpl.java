package es.dao.impl;

import es.dao.ManagerDao;
import es.entity.Section;
import es.entity.Student;
import es.entity.Teacher;

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
      boolean bool= BaseDaoImpl.execute(sql);

      if(bool){
          sql ="insert into users values("+
                  student.getId()+" ,"+
                  "'123456' )";
          bool=BaseDaoImpl.execute(sql);
          if (bool==true){
              return true;
          } else {
              sql= "delete * from student" +
                      " where student_id ="+
                      student.getId();
              while (true){
                  if (BaseDaoImpl.execute(sql))
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
        boolean bool= BaseDaoImpl.execute(sql);
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
        return BaseDaoImpl.execute(sql);
    }


    @Override
    public boolean deleteSection(String section_id) {
        String sql= "delete form section " +
                "where section_id=" +
                section_id;
        return BaseDaoImpl.execute(sql);
    }
}
