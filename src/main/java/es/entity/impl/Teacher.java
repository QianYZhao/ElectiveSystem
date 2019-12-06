package es.entity.impl;

import es.entity.User;

public class Teacher implements User {
    private String teacher_id;
    private String password;
    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    public Teacher(String teacher_id,String password){
        this.teacher_id = teacher_id;
        this.password = password;
    }
}
