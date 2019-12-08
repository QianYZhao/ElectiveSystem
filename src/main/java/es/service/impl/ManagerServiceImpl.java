package es.service.impl;

import es.constant.DAO;
import es.entity.*;
import es.service.ManagerService;

import java.util.List;
import java.util.Map;

public class ManagerServiceImpl implements ManagerService {

    @Override
    public boolean addStudents(Student student) {
      return DAO.managerDao.addStudents(student);
    }

    @Override
    public boolean addTeachers(Teacher teacher) {

        return DAO.managerDao.addTeachers(teacher);
    }

    @Override
    public boolean addSections(Section section, Classroom classroom, Examination exam) {
        // 先找到与这么课程同一年上的全部课程，再查看这里面的课程是否有上课教室冲突，考试教室冲突
        //再安排一门上课的时候需要安排一门

        //冲突检测
        List<Map<String,Object>> addedSections= DAO.managerDao.getSameSemesterSections(section);
        if (addedSections.size()>0){
            for(Map map:addedSections){
                String section_id= (String)map.get("section_id");
                //先看上课时间和地点。
                List<Map<String,Object>> sectionInfo= DAO.studentDao.getSectionInfo(section_id);
                for(Map map1:sectionInfo){
                    String classroom_id= (String)map1.get("room_number");

                }


            }

            return false;
        }else {
            return DAO.managerDao.addSections(section);
        }


    }

    @Override
    public boolean deleteSection(String section_id) {

        //直接删掉，如果是在选课之后应该就不能删除了
        return DAO.managerDao.deleteSection(section_id);
    }

    @Override
    public boolean deleteCourse(String course_id) {
        return DAO.managerDao.deleteCourse(course_id);
    }

    @Override
    public boolean addCourse(Course course) {
        return DAO.managerDao.addCourse(course);
    }
}
