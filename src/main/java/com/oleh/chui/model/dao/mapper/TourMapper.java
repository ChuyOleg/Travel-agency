package com.oleh.chui.model.dao.mapper;

import com.oleh.chui.model.dao.mapper.field.Fields;
import com.oleh.chui.model.entity.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourMapper {

    private final CityMapper cityMapper = new CityMapper(new CountryMapper());
    private final TourTypeMapper tourTypeMapper = new TourTypeMapper();
    private final HotelTypeMapper hotelTypeMapper = new HotelTypeMapper();

    public Tour extractFromResultSet(ResultSet rs) throws SQLException {
        return Tour.builder()
                .id(rs.getLong(Fields.TOUR_ID))
                .name(rs.getString(Fields.NAME))
                .price(rs.getBigDecimal(Fields.PRICE))
                .city(cityMapper.extractWithRelationsFromResultSet(rs))
                .description(rs.getString(Fields.DESCRIPTION))
                .maxDiscount(rs.getInt(Fields.MAX_DISCOUNT))
                .discountStep(rs.getDouble(Fields.DISCOUNT_STEP))
                .tourType(tourTypeMapper.extractFromResultSet(rs))
                .hotelType(hotelTypeMapper.extractFromResultSet(rs))
                .personNumber(rs.getInt(Fields.PERSON_NUMBER))
                .startDate(rs.getDate(Fields.START_DATE).toLocalDate())
                .endDate(rs.getDate(Fields.END_DATE).toLocalDate())
                .nightsNumber(rs.getInt(Fields.NIGHTS_NUMBER))
                .burning(rs.getBoolean(Fields.IS_BURNING))
                .build();
    }

}
