package es.service.impl;

import es.entity.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {
    StudentServiceImpl studentService= new StudentServiceImpl();
    @Test
    void take() {
        String stud_id ="S100";
        String section_id= "16";
        boolean bool = studentService.take(section_id,stud_id);
        System.out.println(bool);
    }
}