package es.controller;

import es.entity.Root;
import es.entity.Student;
import es.entity.Teacher;
import es.service.impl.ManagerServiceImpl;
import es.service.impl.StudentServiceImpl;
import es.service.impl.TeacherServiceImpl;
import org.hibernate.validator.constraints.pl.REGON;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String getHomePage() {
        return "homePage.html";
    }

    /*
     * Login: student teacher root
     * */
    @RequestMapping(value = "/sLogin", method = RequestMethod.GET)
    public String sLogin() {
        return "student/studentLoginPage.html";
    }

    @RequestMapping(value = "/tLogin", method = RequestMethod.GET)
    public String tLogin() {
        return "teacher/teacherLoginPage.html";
    }

    @RequestMapping(value = "/mLogin", method = RequestMethod.GET)
    public String mLogin() {
        return "manager/manager_login_page.html";
    }

    @RequestMapping(value = "/sLogin", method = RequestMethod.POST)
    public String sLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String studentId = request.getParameter("studentId");
        String password = request.getParameter("password");

        Student student = new Student(studentId, password);

        StudentServiceImpl studentService = new StudentServiceImpl();

        if (studentService.login(student)) {
            session.setAttribute("studentId", studentId);
            session.setAttribute("password", password);
            return "student/studentPage.html";
        } else {
            return "student/studentLoginPage.html";
        }

    }

    @RequestMapping(value = "/tLogin", method = RequestMethod.POST)
    public String tLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String teacherId = request.getParameter("teacherId");
        String password = request.getParameter("password");

        Teacher teacher = new Teacher(teacherId, password);

        TeacherServiceImpl teacherService = new TeacherServiceImpl();

        if (teacherService.login(teacher)) {
            session.setAttribute("teacherId", teacherId);
            session.setAttribute("password", password);
            return "teacher/teacherPage.html";
        } else {
            return "teacher/teacherLoginPage.html";
        }
    }

    @RequestMapping(value = "/mLogin", method = RequestMethod.POST)
    public String mLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String rootID = request.getParameter("managerId");
        String password = request.getParameter("password");

        Root root = new Root(rootID, password);
        ManagerServiceImpl managerService = new ManagerServiceImpl();

        if (managerService.login(root)) {
            return "manager/manager_page";
        } else {
            return "manager/manager_login_page.html";
        }
    }


    /*
     * student Service
     *
     *  */
    @RequestMapping(value = "/getMySections", method = RequestMethod.GET)
    public String getMySections(HttpSession session) {
        List<Map<String, Object>> list ;
        StudentServiceImpl studentService = new StudentServiceImpl();
        list = studentService.getMySections((String) session.getAttribute("studentId"));
        session.setAttribute("count_of_course", list.size());
//        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            session.setAttribute("my_section_id" + i, list.get(i).get("section_id"));
            session.setAttribute("my_course_id" + i, list.get(i).get("course_id"));
            session.setAttribute("my_year" + i, list.get(i).get("year"));
            session.setAttribute("my_course_name" + i, list.get(i).get("course_name"));
            session.setAttribute("my_dept_name" + i, list.get(i).get("dept_name"));
            session.setAttribute("my_semester" + i, list.get(i).get("semester"));
            session.setAttribute("my_credit"+i,list.get(i).get("credit"));
        }
        return "student/student_mySection.html";
    }

    @RequestMapping(value = "/getTakePage", method = RequestMethod.GET)
    public String getTakePage() {
        return "student/student_take.html";
    }

    @RequestMapping(value = "/take", method = RequestMethod.POST)
    public String take(HttpServletRequest request, HttpSession session) {
        String section_id = request.getParameter("section");
        String student_id = (String) session.getAttribute("studentId");
        StudentServiceImpl studentService = new StudentServiceImpl();
        if (studentService.take(section_id, student_id)) {
            return "succeed.html";//返回成功的页面
        } else {
            return "failed.html";//返回失败的页面
        }

    }

    @RequestMapping(value = "/getQuitPage", method = RequestMethod.GET)
    public String getQuitPage() {
        return "student/student_quit.html";
    }

    @RequestMapping(value = "/quit", method = RequestMethod.POST)
    public String quit(HttpServletRequest request, HttpSession session) {
        String section_id = request.getParameter("section");
        String student_id = (String) session.getAttribute("studentId");

        StudentServiceImpl studentService = new StudentServiceImpl();
        if (studentService.drop(section_id, student_id)) {
            return "succeed.html";//返回成功的页面
        } else {
            return "failed.html";//返回失败的页面
        }

    }

    @RequestMapping(value = "/getSectionInfo", method = RequestMethod.GET)
    public String getSectionInfo(HttpSession session) {
        String student_id = (String) session.getAttribute("student_id");
        StudentServiceImpl studentService = new StudentServiceImpl();
        List<Map<String, Object>> list = studentService.getSections();
//        System.out.println(list);
        session.setAttribute("count_of_section", list.size());
        for (int i = 0; i < list.size(); i++) {
            session.setAttribute("section_id" + i, list.get(i).get("section_id"));
            session.setAttribute("course_id"+i,list.get(i).get("course_id"));
            session.setAttribute("evaluation_mode" + i, list.get(i).get("entrance_year"));
            session.setAttribute("class_times" + i, list.get(i).get("class_times"));
            session.setAttribute("year" + i, list.get(i).get("year"));
            session.setAttribute("semester" + i, list.get(i).get("semester"));
            session.setAttribute("course_name" +i,list.get(i).get("course_name"));
            session.setAttribute("credit"+i,list.get(i).get("credit"));
        }
        return "student/student_section_info.html";
    }

    @RequestMapping(value = "/getApplyPage",method = RequestMethod.GET)
    public String getApplyPage(){
        return "student/student_apply.html";
    }

    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public String apply(HttpServletRequest request,HttpSession session){
        String student_id = (String)session.getAttribute("student_id");
        String section_id = request.getParameter("apply_section_id");
        String application = request.getParameter("application");
        StudentServiceImpl studentService = new StudentServiceImpl();
        if(studentService.applySection(section_id,student_id,application)){
            return "succeed.html";//返回成功的页面
        } else {
            return "failed.html";//返回失败的页面
        }
    }

    @RequestMapping(value = "/viewExamInfo",method = RequestMethod.GET)
    public String viewExamInfo(HttpSession session){
        String student_id = (String)session.getAttribute("student_id");
        StudentServiceImpl studentService = new StudentServiceImpl();
        List<Map<String,Object>> list = studentService.viewExamInfo(student_id);
        System.out.println(list);
        for(int i=0;i<list.size();i++){
            session.setAttribute("exam_id" + i, list.get(i).get("exam_id"));
            session.setAttribute("date" + i, list.get(i).get("date"));
            session.setAttribute("modality" + i, list.get(i).get("modality"));
            session.setAttribute("exam_start" + i, list.get(i).get("exam_start"));
            session.setAttribute("exam_end" + i, list.get(i).get("exam_end"));
        }
        return "student/student_view_exam_info.html";
    }
    /*
     * manager Service
     *
     * */

    @RequestMapping(value = "/getImportSectionPage", method = RequestMethod.GET)
    public String getImportSectionPage() {
        return "manager/manager_import_sections.html";
    }

    @RequestMapping(value = "/importSections", method = RequestMethod.POST)
    public String importSections(HttpServletRequest request) {
        ManagerServiceImpl managerService = new ManagerServiceImpl();
//        TODO:导入不成功，展示冲突 session.setAttribute("conflict")
        System.out.println(request.getParameter("path"));
        if (managerService.importing_sections(request.getParameter("path"))) {
            return "succeed.html";//返回成功的页面
        } else {
            return "failed.html";//返回失败的页面
        }
    }

    @RequestMapping(value = "/getImportStudentPage", method = RequestMethod.GET)
    public String getImportStudentPage() {
        return "manager/manager_import_students.html";
    }

    @RequestMapping(value = "/importStudents", method = RequestMethod.POST)
    public String importStudents(HttpServletRequest request) {
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        if (managerService.importing_students(request.getParameter("path"))) {
            return "succeed.html";//返回成功的页面
        } else {
            return "failed.html";//返回失败的页面
        }

    }

    @RequestMapping(value = "/getImportTeacherPage", method = RequestMethod.GET)
    public String getImportTeacherPage() {
        return "manager/manager_import_teachers.html";
    }

    @RequestMapping(value = "/importTeachers", method = RequestMethod.POST)
    public String importTeachers(HttpServletRequest request) {
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        System.out.println(request.getParameter("path"));
        if (managerService.importing_teachers(request.getParameter("path"))) {
            return "succeed.html";//返回成功的页面
        } else {
            return "failed.html";//返回失败的页面
        }
    }

    @RequestMapping(value = "/getImportCoursePage", method = RequestMethod.GET)
    public String getImportCoursePage() {
        return "manager/manager_import_courses.html";
    }

    @RequestMapping(value = "/importCourses", method = RequestMethod.POST)
    public String importCourses(HttpServletRequest request) {
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        if (managerService.importing_course(request.getParameter("path"))) {
            return "succeed.html";//返回成功的页面
        } else {
            return "failed.html";//返回失败的页面
        }
    }

    @RequestMapping(value = "/getDeletePage",method = RequestMethod.GET)
    public String getDeletePage(){
        return "manager/manager_delete_section_page.html";
    }
    @RequestMapping(value = "/deleteSection",method = RequestMethod.POST)
    public String deleteSection(HttpServletRequest request){
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        if (managerService.deleteSection(request.getParameter("delete_section_id"))){
            return "succeed.html";//返回成功的页面
        } else {
            return "failed.html";//返回失败的页面
        }
    }


    /*
     *  teacher service
     */
    @RequestMapping(value = "/getRosterPage",method = RequestMethod.GET)
    public String getRosterPage(HttpSession session){
////        String teacher_id = (String)session.getAttribute("teacher_id");
//        String section_id = (String)session.getAttribute("section_id");
//        TeacherServiceImpl teacherService = new TeacherServiceImpl();
//        teacherService.getStudentRoster(section_id);
        return "teacher/teacher_query_roster.html";
    }
    @RequestMapping(value = "/getRoster",method = RequestMethod.POST)
    public String getRoster(HttpSession session,HttpServletRequest request){
        String section_id = request.getParameter("t_section");
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        List<Map<String,Object>> list = teacherService.getStudentRoster(section_id);
//        System.out.println(list);
        for(int i=0;i<list.size();i++){
            session.setAttribute("r_student_id"+i,list.get(i).get("student_id"));
            session.setAttribute("r_student_name"+i,list.get(i).get("name"));
        }
        return "teacher/roster.html";
    }
    @RequestMapping(value = "/getTeachingCourses",method = RequestMethod.GET)
    public String getTeachingCourses(HttpSession session){

        String teacher_id = (String)session.getAttribute("teacherId");
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        List<Map<String,Object>> list =  teacherService.getTeachingSection(teacher_id);
//        System.out.println(list);
        for(int i=0;i<list.size();i++){
            session.setAttribute("t_section_id" + i, list.get(i).get("section_id"));
            session.setAttribute("t_course_id" + i, list.get(i).get("course_id"));
            session.setAttribute("t_year" + i, list.get(i).get("year"));
            session.setAttribute("t_course_name" + i, list.get(i).get("course_name"));
            session.setAttribute("t_dept_name" + i, list.get(i).get("dept_name"));
            session.setAttribute("t_semester" + i, list.get(i).get("semester"));
            session.setAttribute("t_credit"+i,list.get(i).get("credit"));
        }
        return "teacher/teacher_teaching_courses.html";
    }

    @RequestMapping(value = "/getCheckApplicationPage",method = RequestMethod.GET)
    public String getCheckApplicationPage(){
        return "teacher/teacher_manager_application.html";
    }
    @RequestMapping(value = "/getCheckApplication",method = RequestMethod.POST)
    public String getCheckApplication(HttpServletRequest request,HttpSession session){
        String section_id = request.getParameter("application_section");
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        List<Map<String,Object>> list = teacherService.getSectionApplication(section_id);
//        System.out.println(list);
        for(int i=0;i<list.size();i++){
            session.setAttribute("application_student_name"+i,list.get(i).get("name"));
            session.setAttribute("application_student_id"+i,list.get(i).get("student_id"));
            session.setAttribute("application"+i,list.get(i).get("application"));
            session.setAttribute("application_dept_name"+i,list.get(i).get("dept_name"));
        }
        return "teacher/teacher_check.html";
    }

    @RequestMapping(value = "/getImportGradesPage",method = RequestMethod.GET)
    public String getImportGradesPage(){
        return "teacher/teacher_import_grades_page.html";
    }

    @RequestMapping(value = "/importGrades",method = RequestMethod.GET)
    public String importGrades(HttpServletRequest request){
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
//        if(teacherService.importGrades(request.getParameter("path")))
        return "";
    }

}