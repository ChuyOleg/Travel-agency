package com.oleh.chui.model.dao.mapper;

import com.oleh.chui.model.dao.mapper.field.Fields;
import com.oleh.chui.model.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper {

    public City extractFromResultSet(ResultSet rs) throws SQLException {
        return new City(
                rs.getLong(Fields.CITY_ID),
                rs.getString(Fields.CITY),
                null
        );
    }

}
