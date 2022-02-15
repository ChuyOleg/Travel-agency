package com.oleh.chui.controller.command.impl.mapper;

import com.oleh.chui.model.entity.HotelType;
import com.oleh.chui.model.entity.TourType;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;

public class CatalogMapper {

    private final Integer PAGE_SIZE = 6;

    public void insertInfoIntoRequest(TourService tourService, HttpServletRequest req) {
        // TODO: delete method tourService.getToursQuantity
        // TODO: get number if tours based of tourService.findAll() or tourService.findAllWithFilters()
        int tourQuantity = tourService.getToursQuantity();
        int pagesNumber = (int) Math.ceil((double) tourQuantity / PAGE_SIZE);

        req.setAttribute("pagesNumber", pagesNumber);
        req.setAttribute("tourTypeList", TourType.TourTypeEnum.values());
        req.setAttribute("hotelTypeList", HotelType.HotelTypeEnum.values());
    }

}
