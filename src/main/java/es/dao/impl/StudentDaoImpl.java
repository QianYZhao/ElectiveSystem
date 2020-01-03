package es.dao.impl;

import es.constant.DAO;
import es.dao.StudentDao;

import java.util.List;
import java.util.Map;


public class StudentDaoImpl implements StudentDao {
    @Override
    public boolean take(String section_id, String student_id) {
        //选课
        String sql = "insert into takes (student_id,section_id,state) " +
                "values('" + student_id + "','" + section_id + "','taken')";
        boolean bool = DAO.baseDao.execute(sql);
        return bool;
        //已经测试成功
    }

    @Override
    public boolean drop(String section_id, String student_id) {

        String sql = "update takes " +
                "set state ='dropped' " +
                "where student_id= '" + student_id +
                "' and section_id='" + section_id + "'";
        return DAO.baseDao.execute(sql);
        //通过测试
    }

    @Override
    public List<Map<String, Object>> searchSections(String keyword) {

        String sql = "select section.section_id,course.course_id,\n" +
                "course.course_name,course.credit,instructor.inst_name,\n" +
                "classroom.classroom_id,sec_time_slot.time_slot_id,examination.date,examination.exam_start,\n" +
                "examination.exam_end\n" +
                " from section ,sec_course  ,  course, teaches, \n" +
                " instructor,classroom,sec_classroom, sec_time_slot, examination, sec_exam\n" +
                "                 where \n" +
                "                 section.section_id= sec_course.section_id and\n" +
                "                 sec_course.course_id= course.course_id  and \n" +
                "teaches.section_id= section.section_id and \n" +
                "                 teaches.instructor_id= instructor.instructor_id and\n" +
                "                 sec_exam.exam_id =examination.exam_id and\n" +
                "                 sec_exam.section_id =section.section_id and\n" +
                "                 sec_classroom.section_id= section.section_id and\n" +
                "                 sec_classroom.classroom_id= classroom.classroom_id and\n" +
                "                 sec_time_slot.section_id =section.section_id and\n" +
                "                course.course_name like '%" + keyword + "%'\n" +
                "                ";
        List<Map<String, Object>> list = DAO.baseDao.search(sql);
        return list;
        //测试成功
    }

    @Override
    public boolean applySection(String section_id, String student_id, String application) {
        String sql = "update takes " +
                "set state ='applying' ," +
                "  application = '" + application + "'" +
                " where student_id= '" + student_id +
                "' and section_id= '" + section_id + "'";
        return DAO.baseDao.execute(sql);
        //测试成功
    }

    @Override
    public List<Map<String, Object>> completedSections(String student_id) {
        String sql = "select * from takes  join  section " +
                " on takes.section_id = section.section_id " +
                "and student_id= '" + student_id + "' and state='completed'\n";
        List<Map<String, Object>> list = DAO.baseDao.search(sql);
        return list;
        //测试成功
    }


    @Override
    public List<Map<String, Object>> viewExamInfo(String student_id) {
        String sql = "select  * " +
                "from takes natural join sec_exam " +
                "natural join examination  " +
                "where state = 'taken' and student_id='" + student_id + "'";
        List<Map<String, Object>> list = DAO.baseDao.search(sql);
        return list;
        //测试通过
    }


    @Override
    public List<Map<String, Object>> getSectionTime_slot(String section_id) {
        String sql = "  select start_time,end_time " +
                "from time_slot t , sec_time_slot s " +
                "where s.time_slot_id =t.time_slot_id and section_id =" + section_id;
        List<Map<String, Object>> list = DAO.baseDao.search(sql);
        return list;
        //测试成功
    }

    @Override
    public List<Map<String, Object>> getSelectedSections(String student_id) {
        String sql = "select  * from takes T, section S ,course C,sec_course M\n" +
                "where T.section_id= S.section_id\n" +
                "and S.section_id= M.section_id\n" +
                "and C.course_id=M.course_id\n" +
                "and T.student_id= '" + student_id + "' and state ='taken'";
        List<Map<String, Object>> list = DAO.baseDao.search(sql);
        return list;
        //测试成功
    }

    @Override
    public List<Map<String, Object>> getSectionInfo(String section_id) {
        String sql ="select * " +
                "from section natural join sec_course natural join course natural join sec_classroom " +
                "natural join classroom where section_id ='"+section_id+"'";
        List<Map<String, Object>> list= DAO.baseDao.search(sql);
        return list;
        //可用
    }

    @Override
    public List<Map<String, Object>> getSectionExam(String section_id) {
        String sql = "select * " +
                "from sec_exam natural join examination" +
                " where section_id ='"+section_id+"'";
        List<Map<String, Object>> list = DAO.baseDao.search(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getAllSections() {
        String sql = "select * "+"from section natural join sec_course natural join course";
        List<Map<String,Object>> list = DAO.baseDao.search(sql);
        return list;
    }

}
