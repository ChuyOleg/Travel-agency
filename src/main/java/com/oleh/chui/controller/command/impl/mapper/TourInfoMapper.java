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
        req.setAttribute("name", tourDto.getName());
        req.setAttribute("price", tourDto.getPrice());
        req.setAttribute("country", tourDto.getCountry());
        req.setAttribute("city", tourDto.getCity());
        req.setAttribute("description", tourDto.getDescription());
        req.setAttribute("maxDiscount", tourDto.getMaxDiscount());
        req.setAttribute("discountStep", tourDto.getDiscountStep());
        req.setAttribute("tourType", tourDto.getTourType());
        req.setAttribute("hotelType", tourDto.getHotelType());
        req.setAttribute("personNumber", tourDto.getPersonNumber());
        req.setAttribute("startDate", tourDto.getStartDate());
        req.setAttribute("endDate", tourDto.getEndDate());
        req.setAttribute("isBurning", tourDto.isBurning());
        req.setAttribute("tourTypeList", TourType.TourTypeEnum.values());
        req.setAttribute("hotelTypeList", HotelType.HotelTypeEnum.values());
    }

}
