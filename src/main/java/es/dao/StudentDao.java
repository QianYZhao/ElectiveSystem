package es.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface StudentDao {
    boolean take(String section_id,String student_id);
    boolean drop(String section_id,String student_id);
    List<Map<String,Object>> searchSections(String keyword);//查看开课信息
    boolean applySection(String section_id,String student_id,String application);
    List<Map<String,Object>> completedSections(String student_id);
    List<Map<String,Object>> viewExamInfo(String student_id);
    List<Map<String,Object>> getESTime_slot(String student_id);

}
