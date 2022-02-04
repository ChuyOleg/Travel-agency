package com.oleh.chui.model.dao.mapper;

import com.oleh.chui.model.dao.mapper.field.Fields;
import com.oleh.chui.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper {

    private final TourMapper tourMapper = new TourMapper();
    private final UserMapper userMapper = new UserMapper();
    private final StatusMapper statusMapper = new StatusMapper();

    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        return Order.builder()
                .id(rs.getLong(Fields.ORDER_ID))
                .user(userMapper.extractFromResultSet(rs))
                .tour(tourMapper.extractFromResultSet(rs))
                .status(statusMapper.extractFromResultSet(rs))
                .creationDate(rs.getDate(Fields.CREATION_DATE).toLocalDate())
                .finalPrice(rs.getBigDecimal(Fields.FINAL_PRICE))
                .build();
    }

}
