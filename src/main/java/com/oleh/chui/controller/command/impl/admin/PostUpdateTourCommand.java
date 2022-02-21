package com.oleh.chui.controller.command.impl.admin;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.mapper.TourInfoMapper;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.controller.validator.TourValidator;
import com.oleh.chui.model.dto.TourDto;
import com.oleh.chui.model.exception.city.CityNotExistException;
import com.oleh.chui.model.exception.country.CountryNotExistException;
import com.oleh.chui.model.exception.tour.TourNameIsReservedException;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;

public class PostUpdateTourCommand implements Command {

    private static final String URL_ID_PARAMETER = "?tourId=";
    private static final String URL_SUCCESS_PARAMETER = "&success";
    private static final String TOUR_ID = "tourId";
    private static final String NAME_IS_RESERVED = "nameIsReserved";
    private static final String CITY_IS_UNDEFINED = "cityIsUndefined";
    private static final String COUNTRY_IS_UNDEFINED = "countryIsUndefined";

    private final TourInfoMapper tourInfoMapper = new TourInfoMapper();
    private final TourService tourService;

    public PostUpdateTourCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long tourId = Long.valueOf(request.getParameter(TOUR_ID));

        TourDto tourDto = tourInfoMapper.fetchTourDtoFromRequest(request);

        boolean tourDtoIsValid = TourValidator.validate(tourDto, request);

        if (tourDtoIsValid) {
            try {
                tourService.update(tourDto, tourId);
                return UriPath.REDIRECT + UriPath.ADMIN_UPDATE_TOUR + URL_ID_PARAMETER + tourId + URL_SUCCESS_PARAMETER;
            } catch (TourNameIsReservedException e) {
                request.setAttribute(NAME_IS_RESERVED, true);
            } catch (CityNotExistException e) {
                request.setAttribute(CITY_IS_UNDEFINED, true);
            } catch (CountryNotExistException e) {
                request.setAttribute(COUNTRY_IS_UNDEFINED, true);
            }
        }

        tourInfoMapper.insertTourDtoIntoRequest(tourDto, request);
        tourInfoMapper.insertTourAndHotelTypesIntoRequest(request);

        return JspFilePath.ADMIN_UPDATE_TOUR;
    }
}
