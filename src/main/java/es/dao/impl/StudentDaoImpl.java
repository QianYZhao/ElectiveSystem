package es.dao.impl;

import es.constant.DAO;
import es.dao.StudentDao;

import java.util.List;
import java.util.Map;


public class StudentDaoImpl implements StudentDao {
    @Override
    public boolean take(String section_id,String student_id) {
        //选课
        String sql= "insert into takes " +
                "(student_id,section_id) " +
                "values("+student_id+"," +section_id+")";
        boolean bool = DAO.baseDao.execute(sql);
        return bool;
    }

    @Override
    public boolean drop(String section_id,String student_id){
        //退课
        String sql = "update takes " +
                "set state ='dropped' " +
                "where student_id= "+student_id+
                " and section_id="+section_id;
        return DAO.baseDao.execute(sql);
    }

    @Override
    public List<Map<String, Object>> searchSections(String keyword) {
        //查询开课信息
        String sql= "select * " +
                "from section left outer join course" +
                " where course_name like %" +
                keyword +"%";
        List<Map<String, Object>> list= DAO.baseDao.search(sql);
        return list;
    }

    @Override
    public boolean applySection(String section_id, String student_id, String application) {
        String sql = "update takes " +
                "set state ='applying' ," +
                " set application = '"+application+"'"+
                "where student_id= "+student_id+
                " and section_id="+section_id;
        return DAO.baseDao.execute(sql);
    }

    @Override
    public List<Map<String, Object>> completedSections(String student_id) {
        String sql= "select * " +
                "from takes left outer join section" +
                " where student_id=" +student_id;
        List<Map<String, Object>> list= DAO.baseDao.search(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> viewExamInfo(String student_id) {
        String sql= "select * " +
                "from takes T" +
                "left outer join section " +
                "left outer join sec_exam" +
                " left outer join examination" +
                " where T.student_id=" +student_id;
        List<Map<String, Object>> list= DAO.baseDao.search(sql);
        return list;
    }


    @Override
    public List<Map<String, Object>> getSectionTime_slot(String section_id) {
        String sql = "select * " +
                "from sec_time_slot" +
                "left outer join time_slot" +
                " where section_id=" + section_id;
        List<Map<String, Object>> list=DAO.baseDao.search(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getSelectedSections(String student_id) {
        String sql = "select * " +
                "from takes left outer join section left outer join course" +
                " where student_id=" +student_id+
                "and state = taken";
        List<Map<String, Object>> list= DAO.baseDao.search(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getSectionInfo(String section_id) {
        //开课加上教室
        String sql = "select *" +
                "from section natural join sec_classroom natural join classroom" +
                "where section_id ="+ section_id;
        List<Map<String, Object>> list= DAO.baseDao.search(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getSectionExam(String section_id) {
        String sql="select *" +
                "from sec_exam natural join examination" +
                "where section_id ="+section_id;
        List<Map<String, Object>> list= DAO.baseDao.search(sql);
        return list;
    }

}
