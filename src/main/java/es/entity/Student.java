package es.entity;

import es.entity.User;

public class Student implements User {
    private String name;
    private String student_id;
    private String password;
    private String entrance_year;
    private String dept_name;

    public Student(String student_id, String name, String entrance_year, String dept_name) {
        this.student_id = student_id;
        this.name = name;
        this.entrance_year = entrance_year;
        this.dept_name = dept_name;
    }

    public Student(String student_id, String password) {
        this.password = password;
        this.student_id = student_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getPassword() {
        return password;
    }

    public String getEntrance_year() {
        return entrance_year;
    }

    public String getDept_name() {
        return dept_name;
    }


    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return student_id;
    }

}
