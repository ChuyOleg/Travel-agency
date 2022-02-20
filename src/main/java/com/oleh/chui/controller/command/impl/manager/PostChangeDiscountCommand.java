package com.oleh.chui.controller.command.impl.manager;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.mapper.TourInfoMapper;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.controller.validator.TourValidator;
import com.oleh.chui.model.dto.TourDto;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;

public class PostChangeDiscountCommand implements Command {

    private final String URL_ID_PARAMETER = "?tourId=";
    private final String URL_SUCCESS_PARAMETER = "&success";

    private final TourInfoMapper tourInfoMapper = new TourInfoMapper();
    private final TourService tourService;

    public PostChangeDiscountCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long tourId = Long.valueOf(request.getParameter("tourId"));

        TourDto tourDto = tourInfoMapper.fetchTourDtoFromRequest(request);

        boolean newDiscountIsValid = TourValidator.validateDiscountInfo(tourDto, request);

        if (newDiscountIsValid) {
            tourService.changeDiscount(tourDto, tourId);
            return UriPath.REDIRECT + UriPath.MANAGER_CHANGE_DISCOUNT +URL_ID_PARAMETER + tourId + URL_SUCCESS_PARAMETER;
        }

        tourInfoMapper.insertTourDtoIntoRequest(tourDto, request);

        return JspFilePath.MANAGER_CHANGE_DISCOUNT;
    }
}
