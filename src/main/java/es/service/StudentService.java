package es.service;
import java.util.List;
import java.util.Map;

public interface StudentService {
    boolean take(String section_id, String student_id);
    boolean drop(String section_id, String student_id);
    List<Map<String, Object>> searchSections(String keyword);
    boolean applySection(String section_id, String student_id, String application);
    List<Map<String, Object>> completedSections(String student_id);
    List<Map<String, Object>> viewExamInfo(String student_id);
    List<Map<String, Object>> getMySections(String studentId);
}