package com.oleh.chui.model.dao.mapper;

import com.oleh.chui.model.dao.mapper.field.Fields;
import com.oleh.chui.model.entity.TourType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourTypeMapper {

    public TourType extractFromResultSet(ResultSet rs) throws SQLException {
        return new TourType(
                rs.getLong(Fields.TOUR_TYPE_ID),
                TourType.TourTypeEnum.valueOf(rs.getString(Fields.TOUR_TYPE))
        );
    }

}
