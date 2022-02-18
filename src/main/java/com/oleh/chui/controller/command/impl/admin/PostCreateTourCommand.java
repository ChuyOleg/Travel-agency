package com.oleh.chui.controller.command.impl.admin;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.mapper.TourInfoMapper;
import com.oleh.chui.controller.exception.tour.*;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.controller.validator.TourValidator;
import com.oleh.chui.model.dto.TourDto;
import com.oleh.chui.model.entity.Country;
import com.oleh.chui.model.service.CountryService;
import com.oleh.chui.model.service.TourService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class PostCreateTourCommand implements Command {

    private final Logger logger = LogManager.getLogger(PostCreateTourCommand.class);
    private final TourInfoMapper tourInfoMapper = new TourInfoMapper();
    private final TourService tourService;
    private final CountryService countryService;

    public PostCreateTourCommand(TourService tourService, CountryService countryService) {
        this.tourService = tourService;
        this.countryService = countryService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        TourDto tourDto = tourInfoMapper.fetchTourDtoFromRequest(request);

        boolean tourDtoIsValid = TourValidator.validate(tourDto, request);

        if (tourDtoIsValid) {
            boolean isTourNameUnique = checkTourNameIsUnique(tourDto.getName(), request);

            if (isTourNameUnique) {
                boolean countryAndCityExist = checkCountryAndCityExist(tourDto.getCountry(), tourDto.getCity(), request);

                if (countryAndCityExist) {
                    tourService.create(tourDto);

                    return UriPath.REDIRECT + UriPath.CATALOG;
                }
            }
        }

        tourInfoMapper.insertTourDtoIntoRequest(tourDto, request);
        return JspFilePath.ADMIN_CREATE_TOUR;
    }

    private boolean checkTourNameIsUnique(String tourName, HttpServletRequest req) {
        boolean isTourNameReserved = tourService.isTourWithThisNameAlreadyExists(tourName);

        if (isTourNameReserved) {
            req.setAttribute("nameIsReserved", true);
            return false;
        } else {
            return true;
        }
    }

    private boolean checkCountryAndCityExist(String country, String city, HttpServletRequest req) {
        Optional<Country> countryOptional = countryService.findByName(country);

        if (countryOptional.isPresent()) {
            boolean cityNotExists = countryOptional.get().getCityList()
                    .stream()
                    .noneMatch(cityObj -> cityObj.getCity().equals(city));

            if (cityNotExists) {
                req.setAttribute("cityIsUndefined", true);
                return false;
            }
        } else {
            req.setAttribute("countryIsUndefined", true);
            return false;
        }

        return true;
    }

}
