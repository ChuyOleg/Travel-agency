package com.oleh.chui.controller.command.impl.mapper;

import com.oleh.chui.model.dto.TourDto;
import com.oleh.chui.model.entity.HotelType;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.entity.TourType;

import javax.servlet.http.HttpServletRequest;

public class TourInfoMapper {

    public TourDto fetchTourDtoFromRequest(HttpServletRequest req) {
        return new TourDto(
                req.getParameter("name"),
                req.getParameter("price"),
                req.getParameter("country"),
                req.getParameter("city"),
                req.getParameter("description"),
                req.getParameter("maxDiscount"),
                req.getParameter("discountStep"),
                req.getParameter("tourType"),
                req.getParameter("hotelType"),
                req.getParameter("personNumber"),
                req.getParameter("startDate"),
                req.getParameter("endDate"),
                req.getParameter("isBurning")
        );
    }

    public void insertTourDtoIntoRequest(TourDto tourDto, HttpServletRequest req) {
        req.setAttribute("tourDto", tourDto);
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
                tour.isBurning() ? "on" : ""
        );

        insertTourDtoIntoRequest(tourDto, req);
    }

    public void insertTourAndHotelTypesIntoRequest(HttpServletRequest req) {
        TourType.TourTypeEnum[] tourTypeEnums = TourType.TourTypeEnum.values();
        HotelType.HotelTypeEnum[] hotelTypeEnums = HotelType.HotelTypeEnum.values();

        req.setAttribute("tourTypeList", tourTypeEnums);
        req.setAttribute("hotelTypeList", hotelTypeEnums);
    }

}
