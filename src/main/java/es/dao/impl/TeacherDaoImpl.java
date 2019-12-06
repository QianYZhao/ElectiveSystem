package es.dao.impl;

import es.dao.TeacherDao;

import java.util.List;
import java.util.Map;

public class TeacherDaoImpl implements TeacherDao {

    @Override
    public List<Map<String, Object>> getStudentRoster(String section_id){
        String sql= "select student_id ,name ,dept_name" +
                "from takes join student " +
                "where section= "+section_id+
                "and state = taken";
        List<Map<String, Object>> list = BaseDaoImpl.search(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> handleApplication(String section_id) {
        String sql= "select *" +
                "from takes join student " +
                "where section= "+section_id+
                "and state = applying";
        List<Map<String, Object>> list = BaseDaoImpl.search(sql);
        return list;

    }

    @Override
    public boolean importGrades(String student_id, String section_id,String grade) {
        String sql = "update takes " +
                "set grade = " +grade +
                "where student_id= "+student_id+
                " and section_id="+section_id;
        return BaseDaoImpl.execute(sql);
    }
}
