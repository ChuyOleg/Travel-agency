package com.oleh.chui.controller.command.impl.admin;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.exception.tour.*;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.controller.validator.TourValidator;
import com.oleh.chui.model.dto.TourDto;
import com.oleh.chui.model.entity.Country;
import com.oleh.chui.model.entity.HotelType;
import com.oleh.chui.model.entity.TourType;
import com.oleh.chui.model.service.CountryService;
import com.oleh.chui.model.service.TourService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class PostCreateTourCommand implements Command {

    private final Logger logger = LogManager.getLogger(PostCreateTourCommand.class);
    private final TourService tourService;
    private final CountryService countryService;

    public PostCreateTourCommand(TourService tourService, CountryService countryService) {
        this.tourService = tourService;
        this.countryService = countryService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        TourDto tourDto = fetchTourDtoFromRequest(request);

        boolean tourDtoIsValid = validateTourDto(tourDto, request);

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

        insertTourDtoIntoRequest(tourDto, request);
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

    private TourDto fetchTourDtoFromRequest(HttpServletRequest req) {
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

    private boolean validateTourDto(TourDto tourDto, HttpServletRequest req) {
        try {
            TourValidator.validate(tourDto);
            return true;
        } catch (NameIsEmptyException e) {
            logger.warn("<tour creating> name is empty");
            req.setAttribute("nameIsEmptyException", true);
        } catch (PriceIsNotValidException e) {
            logger.warn("<tour creating> price is not valid ({})", tourDto.getPrice());
            req.setAttribute("priceIsNotValidException", true);
        } catch (CountryIsEmptyException e) {
            logger.warn("<tour creating> country is empty");
            req.setAttribute("countryIsEmptyException", true);
        } catch (CityIsEmptyException e) {
            logger.warn("<tour creating> city is empty");
            req.setAttribute("cityIsEmptyException", true);
        } catch (DescriptionIsEmptyException e) {
            logger.warn("<tour creating> description is empty");
            req.setAttribute("descriptionIsEmptyException", true);
        } catch (MaxDiscountIsNotValidException e) {
            logger.warn("<tour creating> max discount value is not valid ({})", tourDto.getMaxDiscount());
            req.setAttribute("maxDiscountIsNotValidException", true);
        } catch (DiscountStepIsNotValidException e) {
            logger.warn("<tour creating> discount step is not valid ({})", tourDto.getDiscountStep());
            req.setAttribute("discountStepIsNotValidException", true);
        } catch (TourTypeIsEmptyException e) {
            logger.warn("<tour creating> tour type is empty");
            req.setAttribute("tourTypeIsEmptyException", true);
        } catch (HotelTypeIsEmptyException e) {
            logger.warn("<tour creating> hotel type is empty");
            req.setAttribute("hotelTypeIsEmptyException", true);
        } catch (PersonNumberIsNotValidException e) {
            logger.warn("<tour creating> person number is not valid ({})", tourDto.getPersonNumber());
            req.setAttribute("personNumberIsNotValidException", true);
        } catch (StartDateIsNotValidException e) {
            logger.warn("<tour creating> start date is not valid ({})", tourDto.getStartDate());
            req.setAttribute("startDateIsNotValidException", true);
        } catch (EndDateIsNotValidException e) {
            logger.warn("<tour creating> end date is not valid ({})", tourDto.getEndDate());
            req.setAttribute("endDateIsNotValidException", true);
        }

        return false;
    }

    private void insertTourDtoIntoRequest(TourDto tourDto, HttpServletRequest req) {
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

        TourType.TourTypeEnum[] tourTypeEnums = TourType.TourTypeEnum.values();
        HotelType.HotelTypeEnum[] hotelTypeEnums = HotelType.HotelTypeEnum.values();

        req.setAttribute("tourTypeList", tourTypeEnums);
        req.setAttribute("hotelTypeList", hotelTypeEnums);
    }

}
