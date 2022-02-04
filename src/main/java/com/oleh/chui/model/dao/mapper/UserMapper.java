package com.oleh.chui.model.dao.mapper;

import com.oleh.chui.model.dao.mapper.field.Fields;
import com.oleh.chui.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    private final RoleMapper roleMapper = new RoleMapper();

    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong(Fields.USER_ID))
                .username(rs.getString(Fields.USERNAME))
                .firstName(rs.getString(Fields.FIRST_NAME))
                .lastName(rs.getString(Fields.LAST_NAME))
                .email(rs.getString(Fields.EMAIL))
                .money(rs.getBigDecimal(Fields.MONEY))
                .role(roleMapper.extractFromResultSet(rs))
                .blocked(rs.getBoolean(Fields.IS_BLOCKED))
                .build();
    }

}
