package es.entity;

import es.entity.User;

public class Teacher implements User {
    private String name;
    private String  teacher_id;
    private String dept_name;
    private String password;
    private Double salary;

    public Teacher(String teacher_id,String name,String dept_name,Double salary){
        this.teacher_id = teacher_id;
        this.name = name;
        this.dept_name = dept_name;
        this.salary = salary;
    }
    public Teacher(String teacher_id,String password){
        this.teacher_id = teacher_id;
        this.password = password;
    }
    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getPassword() {
       return password;
    }

    public String getDept_name() {
        return dept_name;
    }

    public String getName() {
        return name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }
}
