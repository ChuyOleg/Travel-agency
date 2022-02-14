package com.oleh.chui.controller.command.impl.mapper;

import com.oleh.chui.model.entity.HotelType;
import com.oleh.chui.model.entity.TourType;

import javax.servlet.http.HttpServletRequest;

public class CatalogMapper {

    public void insertInfoIntoRequest(HttpServletRequest req) {
        req.setAttribute("tourTypeList", TourType.TourTypeEnum.values());
        req.setAttribute("hotelTypeList", HotelType.HotelTypeEnum.values());
    }

}
