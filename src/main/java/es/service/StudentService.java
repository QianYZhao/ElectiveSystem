package es.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

public interface StudentService {
    boolean take(String section_id);
    boolean drop(String section_id);
    ResultSet searchSections();//查看开课信息
    ArrayList<Map<String,Object>> getMySections(String section_id);
    boolean applySection(String section_id);
    boolean completedSections();
    boolean viewExamInfo();
}
