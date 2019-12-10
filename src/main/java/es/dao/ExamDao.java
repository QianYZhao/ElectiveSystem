package es.dao;

import java.util.List;
import java.util.Map;

public interface ExamDao {
    List<Map<String,Object>> getExamInfo(String exam_id);
}
