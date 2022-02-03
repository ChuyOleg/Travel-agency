package com.oleh.chui.model.dao.impl;

import com.oleh.chui.model.dao.TourDao;
import com.oleh.chui.model.dao.impl.query.TourQueries;
import com.oleh.chui.model.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourDaoImpl implements TourDao {

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
            statement.setString(8, entity.getTourType().name());
            statement.setString(9, entity.getHotelType().name());
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
                return Optional.of(buildTourFromResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("{}, when trying to find Tour by ID", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    @Override
    public List<Tour> findAll() {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.FIND_ALL)) {
            List<Tour> tourList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Tour tour = buildTourFromResultSet(resultSet);
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

    public static void main(String[] args) {
        TourDaoImpl dao = new TourDaoImpl();

    }

    @Override
    public void update(Tour entity) {

    }

    @Override
    public void delete(long id) {
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(TourQueries.DELETE)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("{}, when trying to delete Tour", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }

    private Tour buildTourFromResultSet(ResultSet resultSet) {
        try {
            Country country = new Country(
                    resultSet.getLong("country_id"),
                    resultSet.getString("country")
            );
            City city = new City(
                    resultSet.getLong("city_id"),
                    resultSet.getString("city"),
                    country
            );
            TourType tourType = TourType.valueOf(resultSet.getString("tour_type"));
            HotelType hotelType = HotelType.valueOf(resultSet.getString("hotel_type"));

            return Tour.builder()
                    .id(resultSet.getLong("tour_id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getBigDecimal("price"))
                    .city(city)
                    .description(resultSet.getString("description"))
                    .maxDiscount(resultSet.getInt("max_discount"))
                    .discountStep(resultSet.getDouble("discount_step"))
                    .tourType(tourType)
                    .hotelType(hotelType)
                    .personNumber(resultSet.getInt("person_number"))
                    .startDate(resultSet.getDate("start_date").toLocalDate())
                    .endDate(resultSet.getDate("end_date").toLocalDate())
                    .nightsNumber(resultSet.getInt("nights_number"))
                    .burning(resultSet.getBoolean("is_burning"))
                    .build();
        } catch (SQLException e) {
            logger.error("{}, when trying to build Tour from ResultSet", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
