package com.oleh.chui.model.dao.mapper;

import com.oleh.chui.model.dao.mapper.field.Fields;
import com.oleh.chui.model.entity.City;
import com.oleh.chui.model.entity.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class CountryMapper {

    private CityMapper cityMapper;

    public CountryMapper() {}

    public CountryMapper(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

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

        City city = cityMapper.extractWithoutRelationsFromResultSet(rs);
        country.getCityList().add(city);
        return country;
    }
}
