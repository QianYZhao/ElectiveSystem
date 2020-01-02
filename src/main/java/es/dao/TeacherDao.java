package es.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface TeacherDao {
    List<Map<String,Object>> getStudentRoster(String section_id);
    List<Map<String,Object>> getSectionApplication(String section_id);
    boolean importGrades(String  student_id, String section_id,String grade);
    List<Map<String,Object>> getTeachingCourse(String instructor_id);
}
