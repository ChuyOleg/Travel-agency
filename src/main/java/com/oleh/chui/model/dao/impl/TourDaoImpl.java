package com.oleh.chui.model.dao.impl;

import com.oleh.chui.model.dao.TourDao;
import com.oleh.chui.model.dao.impl.query.TourQueries;
import com.oleh.chui.model.dao.impl.query.TourQueryFilterBuilder;
import com.oleh.chui.model.dao.mapper.TourMapper;
import com.oleh.chui.model.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TourDaoImpl implements TourDao {

    private final TourMapper tourMapper = new TourMapper();
    private final Logger logger = LogManager.getLogger(TourDaoImpl.class);

    @Override
    public void create(Tour entity) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.CREATE)) {
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getPrice());
            statement.setString(3, entity.getCity().getCity());
            statement.setString(4, entity.getCity().getCountry().getCountry());
            statement.setString(5, entity.getDescription());
            statement.setInt(6, entity.getMaxDiscount());
            statement.setDouble(7, entity.getDiscountStep());
            statement.setString(8, entity.getTourType().getValue().name());
            statement.setString(9, entity.getHotelType().getValue().name());
            statement.setInt(10, entity.getPersonNumber());
            statement.setDate(11, Date.valueOf(entity.getStartDate()));
            statement.setDate(12, Date.valueOf(entity.getEndDate()));
            statement.setInt(13, entity.getNightsNumber());
            statement.setBoolean(14, entity.isBurning());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to create new Tour", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public Optional<Tour> findById(long id) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.FIND_BY_ID)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(tourMapper.extractFromResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("{}, when trying to find Tour by ID={}", e.getMessage(), id);
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public List<Tour> findAll() {
        Connection connection = ConnectionPoolHolder.getConnection();
        String QUERY = TourQueries.FIND_ALL + TourQueries.ORDER_BURNING_FIRST;

        try (PreparedStatement statement = connection.prepareStatement(QUERY)) {
            List<Tour> tourList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Tour tour = tourMapper.extractFromResultSet(resultSet);
                tourList.add(tour);
            }

            return tourList;
        } catch (SQLException e) {
            logger.error("{}, when trying to find all Tours", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public void update(Tour entity) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.UPDATE)) {
            statement.setBigDecimal(1, entity.getPrice());
            statement.setString(2, entity.getCity().getCity());
            statement.setString(3, entity.getCity().getCountry().getCountry());
            statement.setString(4, entity.getDescription());
            statement.setInt(5, entity.getMaxDiscount());
            statement.setDouble(6, entity.getDiscountStep());
            statement.setString(7, entity.getTourType().getValue().name());
            statement.setString(8, entity.getHotelType().getValue().name());
            statement.setInt(9, entity.getPersonNumber());
            statement.setDate(10, Date.valueOf(entity.getStartDate()));
            statement.setDate(11, Date.valueOf(entity.getEndDate()));
            statement.setInt(12, entity.getNightsNumber());
            statement.setBoolean(13, entity.isBurning());
            statement.setLong(14, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to update Tour" , e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public void delete(long id) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.DELETE)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to delete Tour by ID={}", e.getMessage(), id);
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public Optional<Tour> findByName(String name) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.FIND_BY_NAME)) {
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(tourMapper.extractFromResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("{}, when trying to find Tour by name ({})", e.getMessage(), name);
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public List<Tour> findAllUsingFilterAndPagination(Map<String, String> filterFieldMap, int limit, int offSet) {
        Connection connection = ConnectionPoolHolder.getConnection();
        String QUERY_WITH_FILTERS = TourQueryFilterBuilder.buildTourQueryFilterForFindAll(filterFieldMap);
        String QUERY_WITH_FILTERS_AND_PAGINATION = QUERY_WITH_FILTERS + TourQueries.LIMIT_OFFSET;

        try (PreparedStatement statement = connection.prepareStatement(QUERY_WITH_FILTERS_AND_PAGINATION)) {
            List<Tour> tourList = new ArrayList<>();
            setFilterAndPaginationParametersIntoStatement(filterFieldMap, limit, offSet, statement);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Tour tour = tourMapper.extractFromResultSet(resultSet);
                tourList.add(tour);
            }

            return tourList;
        } catch (SQLException e) {
            logger.error("{}, when trying to find all tours using filters", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public int findFilteredToursQuantity(Map<String, String> filterFieldMap) {
        Connection connection = ConnectionPoolHolder.getConnection();
        String FIND_ALL_WITH_FILTERS_COUNT = TourQueryFilterBuilder.buildTourQueryFilterForFindCount(filterFieldMap);

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_WITH_FILTERS_COUNT)) {
            setFilerParametersIntoStatementAndGetIndex(filterFieldMap, statement);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("count");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            logger.error("{}, when trying to find tours quantity", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public void changeBurningStateById(Long id) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.CHANGE_BURNING_STATUS_BY_ID)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to change tour's burning state by id=({})", e.getMessage(), id);
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public void updateDiscountInfo(int maxDiscount, double discountStep, Long id) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.UPDATE_DISCOUNT_INFO)) {
            statement.setInt(1, maxDiscount);
            statement.setDouble(2, discountStep);
            statement.setLong(3, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to update discount", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    private int setFilerParametersIntoStatementAndGetIndex(Map<String, String> filterFieldMap, PreparedStatement statement) throws SQLException {
        int indexCounter = 1;
        for (Map.Entry<String, String> entry : filterFieldMap.entrySet()) {
            if (entry.getKey().equals("minPrice") || entry.getKey().equals("maxPrice")) {
                statement.setBigDecimal(indexCounter++, BigDecimal.valueOf(Double.parseDouble(entry.getValue())));
            } else if (entry.getKey().equals("personNumber")) {
                statement.setInt(indexCounter++, Integer.parseInt(entry.getValue()));
            }
        }
        return indexCounter;
    }

    private void setFilterAndPaginationParametersIntoStatement(Map<String, String> filterFieldMap, int limit, int offSet, PreparedStatement statement) throws SQLException {
        int indexCounter = setFilerParametersIntoStatementAndGetIndex(filterFieldMap, statement);
        statement.setInt(indexCounter++, limit);
        statement.setInt(indexCounter, offSet);
    }

}
