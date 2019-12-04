package es.service;

import java.sql.ResultSet;

public interface StudentService {
    boolean take(String section_id);
    boolean drop(String section_id);
    ResultSet searchSections();//查看开课信息
    boolean applySection(String section_id);
    boolean completedSections();
    boolean viewExamInfo();
}
