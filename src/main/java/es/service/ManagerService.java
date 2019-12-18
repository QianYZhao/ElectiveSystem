package es.service;

import es.entity.*;

public interface ManagerService {
    boolean addStudents(Student student);
    boolean addTeachers(Teacher teacher);
    boolean addSections(Section section);
    boolean deleteSection(String section_id);
    //删除课程的时候是不是要删除已经选课的学生的记录
    boolean importing_sections(String filename);
    boolean importing_students(String filename);
    boolean importing_teachers(String filename);
    boolean importing_course(String filename);
    boolean deleteCourse(String course_id);
    boolean addCourse(Course course);
}
