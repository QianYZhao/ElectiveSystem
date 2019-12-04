package es.entity;

import es.entity.User;

public class Teacher implements User {
    private String name;
    private String  teacher_id;
    private String dept_name;
    private String password;

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
