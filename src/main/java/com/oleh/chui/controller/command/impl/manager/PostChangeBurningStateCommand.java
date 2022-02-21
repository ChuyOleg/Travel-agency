package com.oleh.chui.controller.command.impl.manager;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;

public class PostChangeBurningStateCommand implements Command {

    private static final String URL_ID_PARAMETER = "?id=";
    private static final String TOUR_ID = "tourId";

    private final TourService tourService;

    public PostChangeBurningStateCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long tourId = Long.valueOf(request.getParameter(TOUR_ID));

        tourService.changeBurningState(tourId);

        return UriPath.REDIRECT + UriPath.TOUR_DETAILS + URL_ID_PARAMETER + tourId;
    }
}
