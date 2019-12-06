package es.dao;

import es.entity.Course;
import es.entity.Section;
import es.entity.Student;
import es.entity.Teacher;

import java.util.List;
import java.util.Map;

public interface ManagerDao {
    boolean addStudents(Student student);
    boolean addTeachers(Teacher teacher);
    boolean addSections(Section section);
    boolean deleteSection(String section_id);
    boolean deleteCourse(String course_id);
    boolean addCourse(Course course);
    List<Map<String,Object>> getSameSemesterSections(Section section);


}
