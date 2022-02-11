package com.oleh.chui.model.dao.impl;

import com.oleh.chui.model.dao.CountryDao;
import com.oleh.chui.model.dao.impl.query.CountryQueries;
import com.oleh.chui.model.dao.mapper.CountryMapper;
import com.oleh.chui.model.entity.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CountryDaoImpl implements CountryDao {

    Logger logger = LogManager.getLogger(CountryDaoImpl.class);
    CountryMapper countryMapper = new CountryMapper();

    @Override
    public Optional<Country> findByName(String countryName) {
        Country country = null;
        Connection connection = ConnectionPoolHolder.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(CountryQueries.FIND_BY_NAME)) {
            statement.setString(1, countryName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                country = countryMapper.extractWithRelationsFromResultSet(country, resultSet);
            }

            return Optional.ofNullable(country);
        } catch (SQLException e) {
            logger.error("{}, when trying to find Country by name ({})", e.getMessage(), countryName);
            throw new RuntimeException(e);
        } finally {
            ConnectionPoolHolder.closeConnection(connection);
        }
    }
}
