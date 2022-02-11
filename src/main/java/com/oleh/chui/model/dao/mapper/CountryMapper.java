package com.oleh.chui.model.dao.mapper;

import com.oleh.chui.model.dao.mapper.field.Fields;
import com.oleh.chui.model.entity.City;
import com.oleh.chui.model.entity.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;

public class CountryMapper {

    private final CityMapper cityMapper = new CityMapper();

    public Country extractFromResultSet(ResultSet rs) throws SQLException {
        return new Country(
                rs.getLong(Fields.COUNTRY_ID),
                rs.getString(Fields.COUNTRY),
                new HashSet<>()
        );
    }

    public Country extractWithRelationsFromResultSet(Country country, ResultSet rs) throws SQLException {
        if (country == null) {
            country = extractFromResultSet(rs);
        }

        City city = cityMapper.extractFromResultSet(rs);
        country.getCityList().add(city);
        return country;
    }

}
