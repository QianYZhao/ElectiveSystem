package es.dao.impl;

import es.dao.StudentDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoImplTest {
    StudentDao studentDao;

    @BeforeEach
    void setUp() {
        studentDao= new StudentDaoImpl();
    }

    @AfterEach
    void tearDown() {
        studentDao=null;
    }

    @Test
    void take() {
        String stud_id ="S100";
        String id= "99";
        boolean bool = studentDao.take(id,stud_id);
        System.out.println(bool);
    }

    @Test
    void drop() {
        String stud_id ="S100";
        String id= "99";
        boolean bool = studentDao.drop(id,stud_id);
        System.out.println(bool);
    }

    @Test
    void searchSections() {
        String key ="Image";
        List<Map<String,Object>> list=studentDao.searchSections(key);
        System.out.println(list.size());
        for (Map map :list){

        }
    }

    @Test
    void applySection() {
        String stud_id ="S100";
        String id= "99";
        String apply= "申请加入共产党";
        boolean bool = studentDao.applySection(id,stud_id,apply);
        System.out.println(bool);
    }

    @Test
    void completedSections() {
    }

    @Test
    void viewExamInfo() {
    }

    @Test
    void getSectionTime_slot() {
    }

    @Test
    void getSelectedSections() {
    }

    @Test
    void getSectionInfo() {
    }

    @Test
    void getSectionExam() {
    }
}