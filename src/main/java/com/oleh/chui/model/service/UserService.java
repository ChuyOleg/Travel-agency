package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.UserDao;
import com.oleh.chui.model.dto.UserDto;
import com.oleh.chui.model.entity.User;
import com.oleh.chui.model.exception.user.AuthenticationException;
import com.oleh.chui.model.exception.user.IsBlockedException;
import com.oleh.chui.model.exception.user.UsernameIsReservedException;
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

    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public User doAuthentication(String username, String password) throws AuthenticationException, IsBlockedException {
        Optional<User> userOptional = findByUsernameAndPassword(username, password);

        if (userOptional.isPresent()) {
            if (!userOptional.get().isBlocked()) {
                return userOptional.get();
            } else {
                logger.warn("User ({}) is blocked", username);
                throw new IsBlockedException();
            }
        } else {
            logger.warn("User ({}) incorrect credentials during authentication", username);
            throw new AuthenticationException();
        }

    }

    public void registerNewAccount(UserDto userDto) throws UsernameIsReservedException {
        checkUsernameIsUnique(userDto.getUsername());

        User user = new User(userDto);

        userDao.create(user);
        logger.info("New account {} has been created", user);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    public void blockById(Long id) {
        userDao.blockById(id);
        logger.info("User (id = {}) has been blocked", id);
    }

    public void unblockById(Long id) {
        userDao.unblockById(id);
        logger.info("User (id = {}) has been unblocked", id);
    }

    private void checkUsernameIsUnique(String username) throws UsernameIsReservedException {
        if (userDao.usernameIsReserved(username)) throw new UsernameIsReservedException();
    }

}
