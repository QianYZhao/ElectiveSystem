package es.service;

public interface ManagerService {
    boolean addStudents();
    boolean addTeachers();
    boolean addSections();
    boolean deleteSection(String section_id);
}
