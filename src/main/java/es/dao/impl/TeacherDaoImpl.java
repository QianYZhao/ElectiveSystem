package es.dao.impl;

import es.constant.DAO;
import es.dao.TeacherDao;

import java.util.List;
import java.util.Map;

public class TeacherDaoImpl implements TeacherDao {
    //全部测试成功

    @Override
    public List<Map<String, Object>> getStudentRoster(String section_id){
        String sql= "select student_id ,name ,dept_name " +
                "from takes join student " +
                "where takes.student_id =student.sutdent_id\n" +
                "and section_id="+section_id;
        List<Map<String, Object>> list = DAO.baseDao.search(sql);
        return list;

    }

    @Override
    public List<Map<String, Object>> getSectionApplication(String section_id) {
        String sql="select student_id ,name ,dept_name,application" +
                " from takes join student " +
                "where takes.student_id =student.sutdent_id " +
                "and state ='applying' and section_id="+section_id;
        List<Map<String, Object>> list =DAO.baseDao.search(sql);
        return list;

    }

    @Override
    public boolean importGrades(String student_id, String section_id,String grade) {
        String sql = "update takes " +
                "set grade = " +grade +
                "where student_id= "+student_id+
                " and section_id="+section_id;
        return DAO.baseDao.execute(sql);
    }

    @Override
    public List<Map<String, Object>> getTeachingCourse(String instructor_id) {
        String sql = "";
//        TODO:
        List<Map<String, Object>> list = DAO.baseDao.search(sql);
        return list;
    }
}
