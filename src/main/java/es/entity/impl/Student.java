package es.entity.impl;

import es.entity.User;

public class Student implements User {
    private String name;
    private String student_id;
    private String password;

    public Student(String student_id, String password){
        this.password= password;
        this.student_id= student_id;
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
