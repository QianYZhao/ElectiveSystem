package es.service.impl;

import es.constant.DAO;
import es.constant.Limits;
import es.entity.User;
import es.service.StudentService;
import es.service.UserService;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService, UserService {
    @Override
    public boolean take(String section_id,String student_id) {
        int currentTime= (int) (System.currentTimeMillis() / 1000);
        if(currentTime>Limits.takeEndTime ||currentTime<Limits.takeStartTime)
            return false;//在选课时间段才可以选课


        // 时间冲突检测，考试冲突，选课性质检查，教室最大人数限制，学生的学分不能超过最大上限
        List<Map<String, Object>> selectedSections= DAO.studentDao.getSelectedSections(student_id);
        int selectedCredit=0;
        for(int i=0;i<selectedSections.size();i++){
            selectedCredit= selectedCredit+ (int)selectedSections.get(i).get("credit");
        }

        List<Map<String, Object>> sectionInfo= DAO.studentDao.getSectionInfo(section_id);
        //只能得到开课的教室和开课的信息
        int sectionCredit = (int) sectionInfo.get(0).get("credit");
        int max_students = (int) sectionInfo.get(0).get("max_students");

        if(selectedCredit +sectionCredit>Limits.max_credits){
            System.out.println("您选课分数已达"+Limits.max_credits+"学分上限!" );
            return false;
        }

        List<Map<String, Object>> students =DAO.teacherDao.getStudentRoster(section_id);
        int studentNumbers =students.size();//有多少人已经选课
        if(studentNumbers==max_students)
            return false;//已达课程的最大选课人数

        if(isConflict(student_id,section_id))
            return false;

        return DAO.studentDao.take(section_id,student_id);


    }

    @Override
    public boolean drop(String section_id, String student_id) {
        int currentTime= (int) (System.currentTimeMillis() / 1000);
        if(currentTime>Limits.dropEndTime||currentTime<Limits.dropStartTime)
            return false;//我觉得这个应该在前端做判断
        return DAO.studentDao.drop(section_id,student_id);

    }


    @Override
    public List<Map<String, Object>> searchSections(String keyword) {
        List<Map<String, Object>> list=DAO.studentDao.searchSections(keyword);
        return list;
    }

    @Override
    public boolean applySection(String section_id, String student_id, String application) {
        //这里应该也要加入时间的限制
        if(isConflict(student_id,section_id))
            return false;
        return DAO.studentDao.applySection(section_id,student_id,application);

    }

    @Override
    public List<Map<String, Object>> completedSections(String student_id) {
        return DAO.studentDao.completedSections(student_id);
    }

    @Override
    public List<Map<String, Object>> viewExamInfo(String student_id) {
       return DAO.studentDao.viewExamInfo(student_id);
    }


    @Override
    public boolean login(User user) {
        List<Map<String,Object>> list= DAO.userDao.get_user_by_id(user.getId());
        if(list.size()==0){
            return false;
            //告诉用户不存在
        }

        String password = (String) list.get(0).get("password");
        if(password.equals(user.getPassword()))
            return true;
        return false;
    }

    @Override
    public boolean quit() {
        return true;// 假装推出
    }



    private boolean isConflict(String student_id,String section_id){
        // 时间冲突检测，考试冲突，教室最大人数限制
        List<Map<String, Object>> selectedSections= DAO.studentDao.getSelectedSections(student_id);
        List<Map<String, Object>> sectionInfo= DAO.studentDao.getSectionInfo(section_id);
        //只能得到开课的教室和开课的信息
        int  classroomCapacity =(int)sectionInfo.get(0).get(" capacity");

        List<Map<String, Object>> students =DAO.teacherDao.getStudentRoster(section_id);
        int studentNumbers =students.size();//有多少人已经选课
        if(studentNumbers==classroomCapacity)
            return true;//教室已经满了


        //判断课程的时间是否冲突,判断考试时间是不是冲突
        //上课时间
        List<Map<String, Object>> sectionTime_slot= DAO.studentDao.getSectionTime_slot(section_id);
        List<String> Time_slots= new ArrayList<>();
        for(Map map: sectionTime_slot){
            String time_slot_id= (String)map.get("time_slot_id");
            Time_slots.add(time_slot_id);
        }

        //课程的考试时间
        List<Map<String, Object>> exam= DAO.studentDao.getSectionExam(section_id);
        Date exam_date = null;
        Time exam_start=null;
        Time exam_end=null;
        if(exam.size()>0){
            exam_date= (Date)exam.get(0).get("date");
            exam_start =(Time)exam.get(0).get("exam_start");
            exam_end =(Time) exam.get(0).get("exam_end");
        }


        for (int i=0;i<selectedSections.size();i++){
            String selectedSection_id= (String) selectedSections.get(i).get("section_id");
            List<Map<String, Object>> sec_time_slot= DAO.studentDao.getSectionTime_slot(selectedSection_id);
            for(Map map:sec_time_slot){
                String time_slot_id= (String)map.get("time_slot_id");
                if(Time_slots.contains(time_slot_id))
                    return true;
            }
            if(exam.size()>0){//确保要选的这一节课是有考试的
                List<Map<String, Object>> selectedExam= DAO.studentDao.getSectionExam(selectedSection_id);
                for(Map map: selectedExam){
                    Date examDate= (Date) map.get("date");
                    Time startTime= (Time) map.get("exam_start");
                    Time endTime =(Time)map.get("exam_end");
                    if(exam_date==examDate){//考试在同一天，再判断是不是有重叠时段
                        if(startTime.before(exam_end)&&exam_start.before(endTime))
                            return true;//有时间重叠
                    }
                }
            }

        }
        return false;
    }
}
