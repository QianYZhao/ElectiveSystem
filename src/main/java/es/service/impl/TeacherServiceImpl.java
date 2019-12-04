package es.service.impl;

import es.entity.User;
import es.service.TeacherService;
import es.service.UserService;

import java.sql.ResultSet;

public class TeacherServiceImpl implements TeacherService, UserService {
    @Override
    public ResultSet getStudentRoster(String section_id) {
        return null;
    }

    @Override
    public void managerApplication() {

    }

    @Override
    public boolean importGrades() {
        return false;
    }

    @Override
    public boolean login(User user) {
        return false;
    }

    @Override
    public boolean quit() {
        return false;
    }
}
