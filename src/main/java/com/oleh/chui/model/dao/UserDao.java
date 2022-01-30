package com.oleh.chui.model.dao;

import com.oleh.chui.model.entity.Role;
import com.oleh.chui.model.entity.User;

import java.sql.Connection;
import java.util.Optional;

public interface UserDao extends GenericDao<User>{

    Optional<Role> findRoleById(Long roleId, Connection connection);

}
