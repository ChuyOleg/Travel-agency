package com.oleh.chui.model.dao.mapper;

import com.oleh.chui.model.dao.mapper.field.Fields;
import com.oleh.chui.model.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusMapper {

    public Status extractFromResultSet(ResultSet rs) throws SQLException {
        return new Status(
                rs.getLong(Fields.STATUS_ID),
                Status.StatusEnum.valueOf(rs.getString(Fields.STATUS))
        );
    }

}
