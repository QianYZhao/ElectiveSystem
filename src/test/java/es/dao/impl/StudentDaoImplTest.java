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
        String section_id= "101";
        boolean bool = studentDao.take(section_id,stud_id);
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
        String key ="Mechanics";
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
        String id ="S100";
        List<Map<String,Object>> list=studentDao.completedSections(id);
        System.out.println(list.size());

    }

    @Test
    void viewExamInfo() {
        String stud_id ="S100";

        List<Map<String,Object>> list=studentDao.viewExamInfo(stud_id);
        System.out.println(list);
        System.out.println(list.size());


    }

    @Test
    void getSectionTime_slot() {
        String id ="100";
        List<Map<String,Object>> list=studentDao.getSectionTime_slot(id);
        System.out.println(list.size());
       //

    }

    @Test
    void getSelectedSections() {
        String stud_id ="S100";
        String id= "99";
        String apply= "申请加入共产党";
        List<Map<String,Object>> list = studentDao.getSelectedSections(stud_id);
        System.out.println(list.size());
    }

    @Test
    void getSectionInfo() {
        String str= "100";
        List<Map<String,Object>> list = studentDao.getSectionInfo(str);
        System.out.println(list.size());

    }

    @Test
    void getSectionExam() {
    }
}