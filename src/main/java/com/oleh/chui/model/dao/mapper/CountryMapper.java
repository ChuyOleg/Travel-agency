package com.oleh.chui.model.dao.mapper;

import com.oleh.chui.model.dao.mapper.field.Fields;
import com.oleh.chui.model.entity.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper {

    public Country extractFromResultSet(ResultSet rs) throws SQLException {
        return new Country(
                rs.getLong(Fields.COUNTRY_ID),
                rs.getString(Fields.COUNTRY)
        );
    }

}
