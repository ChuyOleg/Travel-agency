package com.oleh.chui.model.dao.mapper;

import com.oleh.chui.model.dao.mapper.field.Fields;
import com.oleh.chui.model.entity.HotelType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelTypeMapper {

    public HotelType extractFromResultSet(ResultSet rs) throws SQLException {
        return new HotelType(
                rs.getLong(Fields.HOTEL_TYPE_ID),
                HotelType.HotelTypeEnum.valueOf(rs.getString(Fields.HOTEL_TYPE))
        );
    }

}
