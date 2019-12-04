package es.service;

import es.entity.User;

public interface UserService {
    boolean login(User user);
    boolean quit();
}
