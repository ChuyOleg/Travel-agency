package com.oleh.chui.model.dao.mapper;

import com.oleh.chui.model.dao.mapper.field.Fields;
import com.oleh.chui.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper {

    public Role extractFromResultSet(ResultSet rs) throws SQLException {
        return new Role(
                rs.getLong(Fields.ROLE_ID),
                Role.RoleEnum.valueOf(rs.getString(Fields.ROLE))
        );
    }

}
