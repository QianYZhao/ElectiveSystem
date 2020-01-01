package es.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface StudentDao {
    boolean take(String section_id,String student_id);
    boolean drop(String section_id,String student_id);
    List<Map<String,Object>> searchSections(String keyword);
    boolean applySection(String section_id,String student_id,String application);
    List<Map<String,Object>> completedSections(String student_id);//已修课程
    List<Map<String,Object>> viewExamInfo(String student_id);
    List<Map<String,Object>> getSectionTime_slot(String section_id);
    List<Map<String,Object>> getSelectedSections(String student_id);
    List<Map<String,Object>> getSectionInfo(String section_id);
    List<Map<String,Object>> getSectionExam(String section_id);
    List<Map<String,Object>> getAllSections();
}
