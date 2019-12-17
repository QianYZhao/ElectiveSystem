package es.dao.impl;

import es.dao.ManagerDao;
import es.entity.Student;
import es.entity.Teacher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerDaoImplTest {
    private ManagerDao managerDao= new ManagerDaoImpl();

    @Test
    void addStudents() {
        Student student= new Student("S8562", "特朗普" ,"2017", "Psychology");
        managerDao.addStudents(student);
    }

    @Test
    void addTeachers() {
        Teacher teacher= new Teacher("T100058","马克龙","Psychology",1205469.0);
        managerDao.addTeachers(teacher);
    }

    @Test
    void addSections() {
    }

    @Test
    void deleteSection() {
    }

    @Test
    void deleteCourse() {
    }

    @Test
    void addCourse() {
    }

    @Test
    void getSameSemesterSections() {
    }
}