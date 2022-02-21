package com.oleh.chui.controller.command.impl.admin;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.model.service.OrderService;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;

public class PostDeleteTourCommand implements Command {

    private static final String URL_ID_PARAMETER = "?id=";
    private static final String URL_ERROR_PARAMETER = "&error";
    private static final String TOUR_ID = "tourId";

    private final TourService tourService;
    private final OrderService orderService;

    public PostDeleteTourCommand(TourService tourService, OrderService orderService) {
        this.tourService = tourService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long tourId = Long.valueOf(request.getParameter(TOUR_ID));

        if (orderService.isExistedByTourId(tourId)) {
            return UriPath.REDIRECT + UriPath.TOUR_DETAILS + URL_ID_PARAMETER + tourId + URL_ERROR_PARAMETER;
        }

        tourService.deleteTour(tourId);

        return UriPath.REDIRECT + UriPath.CATALOG;
    }
}
