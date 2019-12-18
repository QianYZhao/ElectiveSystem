package es.dao.impl;

import es.entity.Teacher;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDaoImplTest {
    TeacherDaoImpl dao= new TeacherDaoImpl();


    @Test
    void getStudentRoster() {
        String section_id="99";
        List<Map<String, Object>> list= dao.getStudentRoster(section_id);
        System.out.println(list.size());
    }

    @Test
    void getSectionApplication() {
        String section_id="99";
        List<Map<String, Object>> list= dao.getSectionApplication(section_id);
        System.out.println(list.size());
    }

    @Test
    void importGrades() {
    }
}