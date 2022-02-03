package com.oleh.chui.model.dao.impl;

import com.oleh.chui.model.dao.UserDao;
import com.oleh.chui.model.dao.impl.query.UserQueries;
import com.oleh.chui.model.entity.Role;
import com.oleh.chui.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDaoImpl implements UserDao {

    private final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public void create(User entity) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(UserQueries.CREATE)) {
            statement.setString(1, entity.getUsername());
            statement.setString(2, String.valueOf(entity.getPassword()));
            statement.setString(3, entity.getFirstName());
            statement.setString(4, entity.getLastName());
            statement.setString(5, entity.getEmail());
            statement.setBigDecimal(6, entity.getMoney());
            statement.setString(7, entity.getRole().name());
            statement.setBoolean(8, entity.isBlocked());

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("{}, when trying to create new User", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public Optional<User> findById(long id) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(UserQueries.FIND_BY_ID)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(buildUserFromResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("{}, when trying to find User by ID", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public List<User> findAll() {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(UserQueries.FIND_ALL)) {
            List<User> userList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = buildUserFromResultSet(resultSet);
                userList.add(user);
            }

            return userList;
        } catch (SQLException e) {
            logger.error("{}, when trying to find all Users", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public void update(User entity) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(UserQueries.UPDATE)) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setLong(4, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to update User", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public void delete(long id) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(UserQueries.DELETE)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to delete User", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    private User buildUserFromResultSet(ResultSet resultSet) {
        try {
            return User.builder()
                    .id(resultSet.getLong("user_id"))
                    .username(resultSet.getString("username"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .email(resultSet.getString("email"))
                    .money(resultSet.getBigDecimal("money"))
                    .role(Role.valueOf(resultSet.getString("role")))
                    .blocked(resultSet.getBoolean("is_blocked"))
                    .build();
        } catch (SQLException e) {
            logger.error("{}, when trying to build User from ResultSet", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
