package es.service;

import java.sql.ResultSet;

public interface TeacherService {
    ResultSet getStudentRoster(String section_id);
    void managerApplication();
    boolean importGrades();
}
