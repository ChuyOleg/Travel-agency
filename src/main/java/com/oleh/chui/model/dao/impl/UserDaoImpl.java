package com.oleh.chui.model.dao.impl;

import com.oleh.chui.model.dao.UserDao;
import com.oleh.chui.model.dao.impl.query.UserQueries;
import com.oleh.chui.model.dao.impl.tables_fields.RoleTableFields;
import com.oleh.chui.model.dao.impl.tables_fields.UserTableFields;
import com.oleh.chui.model.entity.Role;
import com.oleh.chui.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public void create(User entity) {
    }

    @Override
    public Optional<User> findById(long id) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(UserQueries.FIND_BY_ID)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Optional<Role> role = findRoleById(resultSet.getLong(UserTableFields.ROLE_ID), connection);
                return role.map(value -> buildUserFromResultSet(resultSet, value));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("{}, when trying to find User by ID", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Optional<Role> findRoleById(Long roleId, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(UserQueries.FIND_ROLE_BY_ROLE_ID)) {
            statement.setLong(1, roleId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(Role.valueOf(resultSet.getString(RoleTableFields.ROLE)));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("{}, when trying to find Role by ID", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private User buildUserFromResultSet(ResultSet resultSet, Role role) {
        try {
            return new User(
                resultSet.getLong(UserTableFields.ID),
                resultSet.getString(UserTableFields.USERNAME),
                resultSet.getString(UserTableFields.FIRST_NAME),
                resultSet.getString(UserTableFields.LAST_NAME),
                resultSet.getString(UserTableFields.EMAIL),
                resultSet.getBigDecimal(UserTableFields.MONEY),
                role,
                resultSet.getBoolean(UserTableFields.IS_BLOCKED)
            );
        } catch (SQLException e) {
            logger.error("{}, when trying to build User from ResultSet", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
