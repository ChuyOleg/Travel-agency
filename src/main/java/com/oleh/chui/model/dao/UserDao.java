package com.oleh.chui.model.dao;

import com.oleh.chui.model.entity.User;


public interface UserDao extends GenericDao<User>{

    boolean usernameIsReserved(String username);

}
