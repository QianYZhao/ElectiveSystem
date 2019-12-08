package es.controller;

import es.entity.Student;
import es.entity.Teacher;
import es.service.impl.StudentServiceImpl;
import es.service.impl.TeacherServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String getHomePage(){
        return "homePage.html";
    }

//    login Page
    @RequestMapping(value = "/sLogin",method = RequestMethod.GET)
    public String sLogin(){
        return "studentLoginPage.html";
    }

    @RequestMapping(value = "/tLogin",method = RequestMethod.GET)
    public String tLogin(){
        return "teacherLoginPage.html";
    }

    @RequestMapping(value = "/sLogin",method = RequestMethod.POST)
    public String sLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        String studentId = request.getParameter("studentId");
        String password = request.getParameter("password");

        Student student = new Student(studentId,password);

        StudentServiceImpl studentService = new StudentServiceImpl();
//        TODO
        if(true){
            session.setAttribute("studentId",studentId);
            session.setAttribute("password",password);
            return "studentPage.html";
        }else {
            return "studentLoginPage.html";
        }

    }

    @RequestMapping(value = "/tLogin",method = RequestMethod.POST)
    public String tLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        String teacherId = request.getParameter("teacherId");
        String password = request.getParameter("password");

        Teacher teacher = new Teacher(teacherId,password);

        TeacherServiceImpl teacherService = new TeacherServiceImpl();
//        TODO:
        if(true){
            session.setAttribute("teacherId",teacherId);
            session.setAttribute("password",password);
            return "teacherPage.html";
        }else {
            return "teacherLoginPage.html";
        }
    }

//    Student Page
    @RequestMapping(value = "/getMySections",method = RequestMethod.GET)
    public String getMySections(HttpSession session){
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        StudentServiceImpl studentService = new StudentServiceImpl();
//        System.out.println((String) session.getAttribute("studentId"));
//        list = studentService.getMySections((String) session.getAttribute("studentId"));
//        for(int i=0;i<list.size();i++){
////            session.setAttribute();
//        }

        return "student_mySection.html";
    }

    @RequestMapping(value = "/take",method = RequestMethod.GET)
    public String take(HttpSession session){
        StudentServiceImpl studentService = new StudentServiceImpl();
        return "studentLoginPage.html";
    }

}