package es.service;

import es.entity.*;

public interface ManagerService {
    boolean addStudents(Student student);
    boolean addTeachers(Teacher teacher);
    boolean addSections(Section section, Classroom classroom,Examination exam);
    boolean deleteSection(String section_id);
    //删除课程的时候是不是要删除已经选课的学生的记录

    boolean deleteCourse(String course_id);
    boolean addCourse(Course course);
}
