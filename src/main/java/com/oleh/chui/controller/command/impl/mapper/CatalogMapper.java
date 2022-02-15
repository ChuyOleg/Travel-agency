package com.oleh.chui.controller.command.impl.mapper;

import com.oleh.chui.model.entity.HotelType;
import com.oleh.chui.model.entity.TourType;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;

public class CatalogMapper {

    public void insertInfoIntoRequest(TourService tourService, HttpServletRequest req) {
        // TODO: delete method tourService.getToursQuantity
        // TODO: get number if tours based of tourService.findAll() or tourService.findAllWithFilters()

        String pageNumber = req.getParameter("page");

        if (pageNumber != null && !pageNumber.isEmpty()) {
            req.setAttribute("activePageNumber", Integer.parseInt(pageNumber));
        } else {
            req.setAttribute("activePageNumber", 1);
        }
//        req.setAttribute("pagesNumber", pagesNumber);
        req.setAttribute("tourTypeList", TourType.TourTypeEnum.values());
        req.setAttribute("hotelTypeList", HotelType.HotelTypeEnum.values());
    }

}
