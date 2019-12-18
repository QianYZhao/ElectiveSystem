package es.controller;

import es.entity.Root;
import es.entity.Student;
import es.entity.Teacher;
import es.service.impl.ManagerServiceImpl;
import es.service.impl.StudentServiceImpl;
import es.service.impl.TeacherServiceImpl;
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
        return "student/teacherLoginPage.html";
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
//        TODO:
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
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        StudentServiceImpl studentService = new StudentServiceImpl();
        list = studentService.getMySections((String) session.getAttribute("studentId"));
        session.setAttribute("count_of_course", list.size());
        for (int i = 0; i < list.size(); i++) {
            session.setAttribute("student_id" + i, list.get(i).get("student_id"));
            session.setAttribute("name" + i, list.get(i).get("name"));
            session.setAttribute("entrance_year" + i, list.get(i).get("entrance_year"));
            session.setAttribute("dept_name" + i, list.get(i).get("dept_name"));
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
        List<Map<String, Object>> list = studentService.completedSections(student_id);
        session.setAttribute("count_of_section", list.size());
        for (int i = 0; i < list.size(); i++) {
            session.setAttribute("section_id" + i, list.get(i).get("student_id"));
            session.setAttribute("max_students" + i, list.get(i).get("name"));
            session.setAttribute("evaluation_mode" + i, list.get(i).get("entrance_year"));
            session.setAttribute("class_times" + i, list.get(i).get("dept_name"));
            session.setAttribute("year" + i, list.get(i).get("year"));
            session.setAttribute("semester" + i, list.get(i).get("semester"));
        }
        return "student/student_section_info.html";
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
        if (managerService.importing_teachers(request.getParameter("path"))) {
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
//        String teacher_id = (String)session.getAttribute("teacher_id");
        String section_id = (String)session.getAttribute("section_id") ;
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        teacherService.getStudentRoster(section_id);
        return "teacher_roster.html";
    }
    @RequestMapping(value = "/getTeachingCourses",method = RequestMethod.GET)
    public String getTeachingCourses(HttpSession session){

        String teacher_id = (String)session.getAttribute("teacher_id");
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        List<Map<String,Object>> list =  teacherService.getTeachingSection(teacher_id);

        for(int i=0;i<list.size();i++){
            session.setAttribute("section_id"+i,list.get(i).get("section_id"));
//            session.setAttribute("");
        }
        return "teacher_teaching_courses.html";
    }

    @RequestMapping(value = "/getCheckApplication",method = RequestMethod.GET)
    public String getCheckApplication(){
        return "teacher_manager_application.html";
    }


}