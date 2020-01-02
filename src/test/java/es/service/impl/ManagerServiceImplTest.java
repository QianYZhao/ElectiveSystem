package es.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerServiceImplTest {

    @Test
    void importing_course() {
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        managerService.importing_course("course.xlsx");
    }

    @Test
    void importing_sections() {
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        managerService.importing_course("course.xlsx");
    }

    @Test
    void importing_students() {
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        managerService.importing_students("student.xlsx");
    }

    @Test
    void importing_teachers() {
    }
}