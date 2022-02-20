package com.oleh.chui.controller.validator;

import com.oleh.chui.controller.exception.tour.*;
import com.oleh.chui.controller.validator.util.FieldValidator;
import com.oleh.chui.model.dto.TourDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.oleh.chui.controller.validator.restriction.TourRestriction.*;

public class TourValidator {

    private static final Logger logger = LogManager.getLogger(TourValidator.class);

    private TourValidator() {}

    public static boolean validate(TourDto tourDto, HttpServletRequest request) {
        try {
            checkForNameIsNotEmpty(tourDto.getName());
            checkForValidPrice(tourDto.getPrice());
            checkForCountryIsNotEmpty(tourDto.getCountry());
            checkForCityIsNotEmpty(tourDto.getCity());
            checkForDescriptionIsNotEmpty(tourDto.getDescription());
            checkForValidMaxDiscount(tourDto.getMaxDiscount());
            checkForValidDiscountStep(tourDto.getDiscountStep());
            checkForTourTypeisNotEmpty(tourDto.getTourType());
            checkForHotelTypeIsNotEmpty(tourDto.getHotelType());
            checkForValidPersonNumber(tourDto.getPersonNumber());
            checkForValidStartDate(tourDto.getStartDate());
            checkForValidEndDate(tourDto.getStartDate(), tourDto.getEndDate());
            return true;
        } catch (NameIsEmptyException e) {
            logger.warn("<tour creating> name is empty");
            request.setAttribute("nameIsEmptyException", true);
        } catch (PriceIsNotValidException e) {
            logger.warn("<tour creating> price is not valid ({})", tourDto.getPrice());
            request.setAttribute("priceIsNotValidException", true);
        } catch (CountryIsEmptyException e) {
            logger.warn("<tour creating> country is empty");
            request.setAttribute("countryIsEmptyException", true);
        } catch (CityIsEmptyException e) {
            logger.warn("<tour creating> city is empty");
            request.setAttribute("cityIsEmptyException", true);
        } catch (DescriptionIsEmptyException e) {
            logger.warn("<tour creating> description is empty");
            request.setAttribute("descriptionIsEmptyException", true);
        } catch (MaxDiscountIsNotValidException e) {
            logger.warn("<tour creating> max discount value is not valid ({})", tourDto.getMaxDiscount());
            request.setAttribute("maxDiscountIsNotValidException", true);
        } catch (DiscountStepIsNotValidException e) {
            logger.warn("<tour creating> discount step is not valid ({})", tourDto.getDiscountStep());
            request.setAttribute("discountStepIsNotValidException", true);
        } catch (TourTypeIsEmptyException e) {
            logger.warn("<tour creating> tour type is empty");
            request.setAttribute("tourTypeIsEmptyException", true);
        } catch (HotelTypeIsEmptyException e) {
            logger.warn("<tour creating> hotel type is empty");
            request.setAttribute("hotelTypeIsEmptyException", true);
        } catch (PersonNumberIsNotValidException e) {
            logger.warn("<tour creating> person number is not valid ({})", tourDto.getPersonNumber());
            request.setAttribute("personNumberIsNotValidException", true);
        } catch (StartDateIsNotValidException e) {
            logger.warn("<tour creating> start date is not valid ({})", tourDto.getStartDate());
            request.setAttribute("startDateIsNotValidException", true);
        } catch (EndDateIsNotValidException e) {
            logger.warn("<tour creating> end date is not valid ({})", tourDto.getEndDate());
            request.setAttribute("endDateIsNotValidException", true);
        }

        return false;
    }

    public static boolean validateDiscountInfo(String maxDiscountString, String discountStepString, HttpServletRequest req) {
        try {
            checkForValidMaxDiscount(maxDiscountString);
            checkForValidDiscountStep(discountStepString);
            return true;
        } catch (MaxDiscountIsNotValidException e) {
            logger.warn("<discount updating> max discount value is not valid ({})", maxDiscountString);
            req.setAttribute("maxDiscountIsNotValidException", true);
        } catch (DiscountStepIsNotValidException e) {
            logger.warn("<discount updating> discount step is not valid ({})", discountStepString);
            req.setAttribute("discountStepIsNotValidException", true);
        }

        return false;
    }

    private static void checkForNameIsNotEmpty(String name) throws NameIsEmptyException {
        if (FieldValidator.fieldIsEmpty(name)) throw new NameIsEmptyException();
    }

    private static void checkForValidPrice(String priceString) throws PriceIsNotValidException {
        if (FieldValidator.fieldIsEmpty(priceString) || !FieldValidator.fieldIsValidBigDecimal(priceString)) {
            throw new PriceIsNotValidException();
        }

        BigDecimal price = new BigDecimal(priceString);
        if (price.compareTo(PRICE_MIN) < 0 || price.compareTo(PRICE_MAX) > 0) {
            throw new PriceIsNotValidException();
        }
    }

    private static void checkForCountryIsNotEmpty(String country) throws CountryIsEmptyException {
        if (FieldValidator.fieldIsEmpty(country)) throw new CountryIsEmptyException();
    }

    private static void checkForCityIsNotEmpty(String city) throws CityIsEmptyException {
        if (FieldValidator.fieldIsEmpty(city)) throw new CityIsEmptyException();
    }

    private static void checkForDescriptionIsNotEmpty(String description) throws DescriptionIsEmptyException {
        if (FieldValidator.fieldIsEmpty(description)) throw new DescriptionIsEmptyException();
    }

    private static void checkForValidMaxDiscount(String maxDiscountString) throws MaxDiscountIsNotValidException {
        if (FieldValidator.fieldIsEmpty(maxDiscountString) || !FieldValidator.fieldIsValidInteger(maxDiscountString)) {
            throw new MaxDiscountIsNotValidException();
        }

        int maxDiscount = Integer.parseInt(maxDiscountString);
        if (maxDiscount < MAX_DISCOUNT_MIN || maxDiscount > MAX_DISCOUNT_MAX) {
            throw new MaxDiscountIsNotValidException();
        }
    }

    private static void checkForValidDiscountStep(String discountStepString) throws DiscountStepIsNotValidException {
        if (FieldValidator.fieldIsEmpty(discountStepString) || !FieldValidator.fieldIsValidDouble(discountStepString)) {
            throw new DiscountStepIsNotValidException();
        }

        double discountStep = Double.parseDouble(discountStepString);
        if (discountStep < DISCOUNT_STEP_MIN || discountStep > DISCOUNT_STEP_MAX) {
            throw new DiscountStepIsNotValidException();
        }
    }

    private static void checkForTourTypeisNotEmpty(String tourType) throws TourTypeIsEmptyException {
        if (FieldValidator.fieldIsEmpty(tourType)) {
            throw new TourTypeIsEmptyException();
        }
    }

    private static void checkForHotelTypeIsNotEmpty(String hotelType) throws HotelTypeIsEmptyException {
        if (FieldValidator.fieldIsEmpty(hotelType)) {
            throw new HotelTypeIsEmptyException();
        }
    }

    private static void checkForValidPersonNumber(String personNumberString) throws PersonNumberIsNotValidException {
        if (FieldValidator.fieldIsEmpty(personNumberString) || !FieldValidator.fieldIsValidInteger(personNumberString)) {
            throw new PersonNumberIsNotValidException();
        }

        int personNumber = Integer.parseInt(personNumberString);
        if (personNumber < PERSON_NUMBER_MIN || personNumber > PERSON_NUMBER_MAX) {
            throw new PersonNumberIsNotValidException();
        }
    }

    private static void checkForValidStartDate(String startDateString) throws StartDateIsNotValidException {
        if (FieldValidator.fieldIsEmpty(startDateString)) {
            throw new StartDateIsNotValidException();
        }

        LocalDate startDate = LocalDate.parse(startDateString);
        if (!startDate.isAfter(LocalDate.now())) {
            throw new StartDateIsNotValidException();
        }
    }

    private static void checkForValidEndDate(String startDateString, String endDateString) throws EndDateIsNotValidException {
        if (FieldValidator.fieldIsEmpty(endDateString)) {
            throw new EndDateIsNotValidException();
        }

        if (!FieldValidator.fieldIsEmpty(startDateString)) {
            LocalDate startDate = LocalDate.parse(startDateString);
            LocalDate endDate = LocalDate.parse(endDateString);
            if (!endDate.isAfter(startDate)) {
                throw new EndDateIsNotValidException();
            }
        }
    }

}
