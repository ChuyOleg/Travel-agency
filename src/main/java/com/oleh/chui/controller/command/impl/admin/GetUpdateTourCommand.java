package com.oleh.chui.controller.command.impl.admin;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.mapper.TourInfoMapper;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class GetUpdateTourCommand implements Command {

    private static final String TOUR_ID = "tourId";

    private final TourInfoMapper tourInfoMapper = new TourInfoMapper();
    private final TourService tourService;

    public GetUpdateTourCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long tourId = Long.valueOf(request.getParameter(TOUR_ID));

        Optional<Tour> tourOptional = tourService.findById(tourId);

        tourOptional.ifPresent(tour ->  {
            tourInfoMapper.insertTourIntoRequest(tour, request);
            tourInfoMapper.insertTourAndHotelTypesIntoRequest(request);
        });

        return JspFilePath.ADMIN_UPDATE_TOUR;
    }
}
