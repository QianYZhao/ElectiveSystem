package es.service.impl;

import es.entity.User;
import es.service.StudentService;
import es.service.UserService;

import java.sql.ResultSet;

public class StudentServiceImpl implements StudentService, UserService {
    @Override
    public boolean take(String section_id) {
        return false;
    }

    @Override
    public boolean drop(String section_id) {
        return false;
    }

    @Override
    public ResultSet searchSections() {
        return null;
    }

    @Override
    public boolean applySection(String section_id) {
        return false;
    }

    @Override
    public boolean completedSections() {
        return false;
    }

    @Override
    public boolean viewExamInfo() {
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
