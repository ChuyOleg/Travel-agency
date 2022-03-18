package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.UserDao;
import com.oleh.chui.model.dto.UserDto;
import com.oleh.chui.model.entity.User;
import com.oleh.chui.model.exception.user.AuthenticationException;
import com.oleh.chui.model.exception.user.IsBlockedException;
import com.oleh.chui.model.exception.user.UsernameIsReservedException;
import com.oleh.chui.model.service.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Manages business logic related with User.
 *
 * @author Oleh Chui
 */
public class UserService {

    private final Logger logger = LogManager.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    public UserService(PasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    /**
     * Process authentication.
     *
     * @param username String representing username.
     * @param password String representing not encoded password.
     * @return User instance representing that authentication has been done successful.
     * @throws AuthenticationException Indicates that credentials are incorrect.
     * @throws IsBlockedException Indicates that User is blocked.
     */
    public User doAuthentication(String username, String password) throws AuthenticationException, IsBlockedException {
        password = passwordEncoder.encode(password);
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

    /**
     * Process creating new user account.
     *
     * @param userDto UserDto instance.
     * @throws UsernameIsReservedException Indicates that username is reserved.
     */
    public void registerNewAccount(UserDto userDto) throws UsernameIsReservedException {
        checkUsernameIsUnique(userDto.getUsername());

        User user = new User(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

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
