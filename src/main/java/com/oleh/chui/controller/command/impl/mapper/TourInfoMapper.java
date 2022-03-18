package com.oleh.chui.controller.command.impl.mapper;

import com.oleh.chui.model.dto.TourDto;
import com.oleh.chui.model.entity.HotelType;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.entity.TourType;

import javax.servlet.http.HttpServletRequest;

/**
 * Manages of fetching and inserting parameters related with Tour info
 * from or into Request
 *
 * @author Oleh Chui
 */
public class TourInfoMapper {

    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String DESCRIPTION = "description";
    private static final String MAX_DISCOUNT = "maxDiscount";
    private static final String DISCOUNT_STEP = "discountStep";
    private static final String TOUR_TYPE = "tourType";
    private static final String HOTEL_TYPE = "hotelType";
    private static final String PERSON_NUMBER = "personNumber";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String IS_BURNING = "isBurning";
    private static final String TOUR_DTO = "tourDto";
    private static final String ON = "on";
    private static final String EMPTY_LINE = "";
    private static final String TOUR_TYPE_LIST = "tourTypeList";
    private static final String HOTEL_TYPE_LIST = "hotelTypeList";

    public TourDto fetchTourDtoFromRequest(HttpServletRequest req) {
        return new TourDto(
                req.getParameter(NAME),
                req.getParameter(PRICE),
                req.getParameter(COUNTRY),
                req.getParameter(CITY),
                req.getParameter(DESCRIPTION),
                req.getParameter(MAX_DISCOUNT),
                req.getParameter(DISCOUNT_STEP),
                req.getParameter(TOUR_TYPE),
                req.getParameter(HOTEL_TYPE),
                req.getParameter(PERSON_NUMBER),
                req.getParameter(START_DATE),
                req.getParameter(END_DATE),
                req.getParameter(IS_BURNING)
        );
    }

    public void insertTourDtoIntoRequest(TourDto tourDto, HttpServletRequest req) {
        req.setAttribute(TOUR_DTO, tourDto);
    }

    public void insertTourIntoRequest(Tour tour, HttpServletRequest req) {
        TourDto tourDto = new TourDto(
                tour.getName(),
                tour.getPrice().toString(),
                tour.getCity().getCountry().getCountry(),
                tour.getCity().getCity(),
                tour.getDescription(),
                String.valueOf(tour.getMaxDiscount()),
                String.valueOf(tour.getDiscountStep()),
                tour.getTourType().getValue().name(),
                tour.getHotelType().getValue().name(),
                String.valueOf(tour.getPersonNumber()),
                tour.getStartDate().toString(),
                tour.getEndDate().toString(),
                tour.isBurning() ? ON : EMPTY_LINE
        );

        insertTourDtoIntoRequest(tourDto, req);
    }

    public void insertTourAndHotelTypesIntoRequest(HttpServletRequest req) {
        TourType.TourTypeEnum[] tourTypeEnums = TourType.TourTypeEnum.values();
        HotelType.HotelTypeEnum[] hotelTypeEnums = HotelType.HotelTypeEnum.values();

        req.setAttribute(TOUR_TYPE_LIST, tourTypeEnums);
        req.setAttribute(HOTEL_TYPE_LIST, hotelTypeEnums);
    }

}
