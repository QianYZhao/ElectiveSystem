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
        //插入失败，删除插入students操作，如果成果便返
        String sql ="insert into users values('"+
                student.getId()+"' ,"+
                "'123456' )";
         boolean bool= DAO.baseDao.execute(sql);

         if(bool){
          sql ="insert into student values( '"+
                  student.getId()+"','"+
                  student.getName()+"','"+
                  student.getEntrance_year()+"','"+
                  student.getDept_name()+"')";
          bool=DAO.baseDao.execute(sql);
          if (bool==true){
              return true;
          } else {
              sql= "delete * from users" +
                      " where user_id ='"+
                      student.getId()+"'";
              while (true){
                  if (DAO.baseDao.execute(sql))
                      return false;
              }
           }

      }else {
          return false;
      }

// 测试通过

    }

    @Override
    public boolean addTeachers(Teacher teacher) {
        // 测试通过
        String sql = "insert into users values('"+
                    teacher.getId()+" ',"+
                    "'123456' )";
        boolean bool= DAO.baseDao.execute(sql);
        if(bool){
            sql ="insert into instructor values('"+
                    teacher.getId()+"','"+
                    teacher.getName()+"','"+
                    teacher.getDept_name()+"',"+
                    teacher.getSalary()+")";
            bool=DAO.baseDao.execute(sql);
            if (bool==true){
                return true;
            } else {
                sql= "delete from users" +
                        " where user_id = '"+
                        teacher.getId()+"'";
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
    public boolean addSections(Section section) {

        //在addsection的时候，还要加上上课地点，时间，考试
        String sql= "insert into section values('"+
                section.getSection_id()+"' ,'"+
                section.getCourse_id()+"','"+
                section.getMax_students()+"','"+
                section.getClass_times()+"','"+
                section.getYear()+"','"+
                section.getEvaluation_mode()+"','"+
                section.getSemester()+"')";
        DAO.baseDao.execute(sql);
        boolean boo1;
        for (int i=0;i<section.getTime_slot_ids().size();i++){
            String addTime_slot =" insert into sec_time_slot " +
                    "values( '"+ section.getTime_slot_ids().get(i)
                    +"','"+section.getSection_id()+"')";
            boo1= DAO.baseDao.execute(addTime_slot);
            if (!boo1){
                //保持一致性，应该重新删掉前面的东西
                String ss= "delete from section where section_id='"+section.getSection_id()+"'\n"+
                        "delete from sec_time_slot where  section_id='"+section.getSection_id()+"'";
                DAO.baseDao.execute(ss);
                return false;
            }
        }

        String exam ="insert into examination values('" +
                section.getExamination().getExam_id()+"','"+
                section.getExamination().getDate()+"','"+
                section.getExamination().getModality()+"','"+
                section.getExamination().getInstructor_id()+"','"+
                section.getExamination().getExam_starTime()+"','"+
                section.getExamination().getExam_endTime();

        boolean bool2= DAO.baseDao.execute(exam);
        if (!bool2){
            //保持一致性，应该重新删掉前面的东西
            String ss= "delete from section where section_id='"+section.getSection_id()+"'\n"+
                    "delete from sec_time_slot where  section_id='"+section.getSection_id()+"'";
            DAO.baseDao.execute(ss);
        }
        String sec_exam="insert into sec_exam values('"
                +section.getExamination().getExam_id()+"','+" +
                section.getSection_id()+"')";
        boolean bool3= DAO.baseDao.execute(sec_exam);
        if(!bool3){
            //保持一致性，应该重新删掉前面的东西
            String ss= "delete from section where section_id='"+section.getSection_id()+"'\n"+
                    "delete from sec_time_slot where  section_id='"+section.getSection_id()+"'\n"+
                     "delete from examination where exam_id='" +section.getExamination().getExam_id()+"'";
            DAO.baseDao.execute(ss);
        }


        String sec_classroom = "insert into sec_classroom values('"+
                section.getClassroom_id()+"','"+
                section.getSection_id()+"')";
        boolean bool4= DAO.baseDao.execute(sec_classroom);

        if (bool4)
            return true;
        else {

        }






    }



    @Override
    public boolean deleteSection(String section_id) {
        // 同时删除的还有联系集，上课教室，考试
        String sql= "delete form section " +
                "where section_id=" +
                section_id;
        return DAO.baseDao.execute(sql);
    }

    @Override
    public boolean deleteCourse(String course_id) {
        //检查是不是需要删除 开课
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
