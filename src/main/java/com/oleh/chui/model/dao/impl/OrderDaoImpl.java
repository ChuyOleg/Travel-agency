package com.oleh.chui.model.dao.impl;

import com.oleh.chui.model.dao.OrderDao;
import com.oleh.chui.model.dao.impl.query.OrderQueries;
import com.oleh.chui.model.dao.mapper.OrderMapper;
import com.oleh.chui.model.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    OrderMapper orderMapper = new OrderMapper();
    Logger logger = LogManager.getLogger(OrderDaoImpl.class);

    @Override
    public void create(Order entity) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(OrderQueries.CREATE)) {
            statement.setLong(1, entity.getUser().getId());
            statement.setLong(2, entity.getTour().getId());
            statement.setString(3, entity.getStatus().getValue().name());
            statement.setDate(4, Date.valueOf(entity.getCreationDate()));
            statement.setBigDecimal(5, entity.getFinalPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to create new Order", e.getMessage());
            throw new RuntimeException();
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public Optional<Order> findById(long id) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(OrderQueries.FIND_BY_ID)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(orderMapper.extractFromResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("{}, when trying to find Order by ID = {}", e.getMessage(), id);
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public List<Order> findAll() {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(OrderQueries.FIND_ALL)) {
            List<Order> orders = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = orderMapper.extractFromResultSet(resultSet);

                orders.add(order);
            }

            return orders;
        } catch (SQLException e) {
            logger.error("{}, when trying to find all Orders", e.getMessage());
            throw new RuntimeException();
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    // TODO: implement
    @Override
    public void update(Order entity) {
    }

    @Override
    public void delete(long id) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(OrderQueries.DELETE)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to delete Order by ID = {}", e.getMessage(), id);
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }
}
