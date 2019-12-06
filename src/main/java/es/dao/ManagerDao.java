package es.dao;

import es.entity.Section;
import es.entity.Student;
import es.entity.Teacher;

public interface ManagerDao {
    boolean addStudents(Student student);
    boolean addTeachers(Teacher teacher);
    boolean addSections(Section section);
    boolean deleteSection(String section_id);

}
