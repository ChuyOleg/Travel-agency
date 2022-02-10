package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.UserDao;
import com.oleh.chui.model.dto.UserDto;
import com.oleh.chui.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final Logger logger = LogManager.getLogger(UserService.class);
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public boolean usernameIsReserved(String username) {
        return userDao.usernameIsReserved(username);
    }

    public void registerNewAccount(UserDto userDto) {
        User user = new User(userDto);

        userDao.create(user);
        logger.info("New account {} has been created", user);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
}
