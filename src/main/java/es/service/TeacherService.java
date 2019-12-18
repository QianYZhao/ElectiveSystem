package es.service;

import java.io.File;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface TeacherService {
    List<Map<String,Object>> getStudentRoster(String section_id);
    List<Map<String,Object>> getSectionApplication(String section_id);
    boolean importGrades(String file,String section_id);
}
