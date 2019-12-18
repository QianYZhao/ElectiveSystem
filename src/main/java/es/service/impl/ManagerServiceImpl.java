package es.service.impl;

import es.constant.DAO;
import es.entity.*;
import es.service.ManagerService;
import es.service.UserService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javax.rmi.CORBA.Tie;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

public class ManagerServiceImpl implements ManagerService, UserService {

    @Override
    public boolean addStudents(Student student) {
        return DAO.managerDao.addStudents(student);
    }

    @Override
    public boolean addTeachers(Teacher teacher) {
        return DAO.managerDao.addTeachers(teacher);
    }

    @Override
    public boolean addSections(Section section) {
        // 先找到与这门课程同一年上的全部课程，再查看这里面的课程是否有上课教室冲突，考试教室冲突,上课时间冲突
        //再安排一门上课的时候需要安排一门
        List<Map<String,Object>> addedSections= DAO.managerDao.getSameSemesterSections(section);
        List<String> time_slots= section.getTime_slot_ids();
        Time exam_start= section.getExamination().getExam_starTime();
        Time exam_end =section.getExamination().getExam_endTime();
        Date date= section.getExamination().getDate();
        if (addedSections.size()>0){
            for(Map map:addedSections){
                String section_id= (String)map.get("section_id");
                List<Map<String,Object>> sectionInfo= DAO.studentDao.getSectionInfo(section_id);
                for(Map map1:sectionInfo){
                    //教室冲突，上课时间
                    String classroom_id= (String)map1.get("room_number");
                    String time_slot= (String)map1.get("time_slot_id");
                    //教室相同，后判断时间是不是相同,默认课程不同的课都是在同一教室
                    if(classroom_id.equals(section.getClassroom_id())){
                       if(time_slots.contains(time_slot))
                           return false;
                    }
                    //考试冲突
                    Date date1= (Date)map1.get("Date");
                    Time exam_start1=(Time)map1.get("exam_start");
                    Time exam_end1 =(Time)map1.get("exam_end");

                    if(date1.equals(date)){
                        if(section.getClassroom_id().equals((String)map1.get("classroom_id"))){
                            //日期相同，教室相同，再看是不是时间相同
                            if(exam_start.before(exam_end1)&&exam_start1.before(exam_end))
                                return false;
                        }
                    }
                }
            }
            return DAO.managerDao.addSections(section);
        }
        else {
            return DAO.managerDao.addSections(section);
        }


    }

    @Override
    public boolean deleteSection(String section_id) {

        //直接删掉，如果是在选课之后应该就不能删除了
        return DAO.managerDao.deleteSection(section_id);
    }

    @Override
    public boolean importing_sections(String filename) {
        try {
            InputStream is = new FileInputStream(new File(filename));
            Workbook excel = WorkbookFactory.create(is);
            is.close();

            for(int numSheet = 0;numSheet<excel.getNumberOfSheets();numSheet++){
                Sheet sheet = excel.getSheetAt(numSheet);
                if(sheet == null)continue;

                for(int rowNum =1;rowNum<sheet.getLastRowNum();rowNum++){
                    Row row = sheet.getRow(rowNum);
                    if (row == null)continue;

                    String section_id = row.getCell(0).getStringCellValue();
                    int student_num =(int) row.getCell(1).getNumericCellValue();
                    String exam_mode = row.getCell(2).getStringCellValue();
                    int class_times =(int) row.getCell(3).getNumericCellValue();
                    String year = row.getCell(4).getStringCellValue();
                    String semester = row.getCell(5).getStringCellValue();

                    Section section = new Section(section_id,student_num,exam_mode,class_times,year,semester);

                    DAO.managerDao.addSections(section);

                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean importing_students(String filename) {
        try {
            InputStream is = new FileInputStream(new File(filename));
            Workbook excel = WorkbookFactory.create(is);
            is.close();

            for(int numSheet = 0;numSheet<excel.getNumberOfSheets();numSheet++){
                Sheet sheet = excel.getSheetAt(numSheet);
                if(sheet == null)continue;

                for(int rowNum =1;rowNum<sheet.getLastRowNum();rowNum++){
                    Row row = sheet.getRow(rowNum);
                    if (row == null)continue;

                    String student_id = row.getCell(0).getStringCellValue();
                    String name = row.getCell(1).getStringCellValue();
                    String enter_year = row.getCell(2).getStringCellValue();
                    String dept_name = row.getCell(3).getStringCellValue();

                    Student student = new Student(student_id,name,enter_year,dept_name);
                    DAO.managerDao.addStudents(student);
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean importing_teachers(String filename) {
        try {
            InputStream is = new FileInputStream(new File(filename));
            Workbook excel = WorkbookFactory.create(is);
            is.close();

            for(int numSheet = 0;numSheet<excel.getNumberOfSheets();numSheet++){
                Sheet sheet = excel.getSheetAt(numSheet);
                if(sheet == null)continue;

                for(int rowNum =1;rowNum<sheet.getLastRowNum();rowNum++){
                    Row row = sheet.getRow(rowNum);
                    if (row == null)continue;

                    String teacher_id = row.getCell(0).getStringCellValue();
                    String name = row.getCell(1).getStringCellValue();
                    String dept_name = row.getCell(2).getStringCellValue();
                    Double salary = row.getCell(3).getNumericCellValue();

                    Teacher teacher = new Teacher(teacher_id,name,dept_name,salary);
                    DAO.managerDao.addTeachers(teacher);
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean importing_course(String filename) {
        try{
            InputStream is = new FileInputStream(new File(filename));
            Workbook excel = WorkbookFactory.create(is);
            is.close();

            for(int numSheet = 0;numSheet<excel.getNumberOfSheets();numSheet++) {
                Sheet sheet = excel.getSheetAt(numSheet);
                if (sheet == null) continue;

                for (int rowNum = 1; rowNum < sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row == null) continue;

                    String course_id = row.getCell(0).getStringCellValue();
                    String course_name = row.getCell(1).getStringCellValue();
                    String type = row.getCell(2).getStringCellValue();
                    int credit = (int)row.getCell(3).getNumericCellValue();
                    String dept_name = row.getCell(4).getStringCellValue();

                    Course course = new Course(course_id,course_name,type,credit,dept_name);

                    DAO.managerDao.addCourse(course);

                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteCourse(String course_id) {
        //如果不能删掉的话，说明这门课有人选课
        return DAO.managerDao.deleteCourse(course_id);
    }

    @Override
    public boolean addCourse(Course course) {
        return DAO.managerDao.addCourse(course);
    }

    @Override
    public boolean login(User user) {
        if(user.getId().equals("root") && user.getPassword().equals("root"))
            return true;
        return false;
    }

    @Override
    public boolean quit() {
        return false;
    }
}
