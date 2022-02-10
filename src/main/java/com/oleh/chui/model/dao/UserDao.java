package com.oleh.chui.model.dao;

import com.oleh.chui.model.entity.User;

import java.util.Optional;


public interface UserDao extends GenericDao<User>{

    boolean usernameIsReserved(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

}
