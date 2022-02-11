package com.oleh.chui.controller.validator;

import com.oleh.chui.controller.exception.tour.*;
import com.oleh.chui.controller.validator.util.FieldValidator;
import com.oleh.chui.model.dto.TourDto;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.oleh.chui.controller.validator.restriction.TourRestriction.*;

public class TourValidator {

    private TourValidator() {}

    public static void validate(TourDto tourDto) throws
            NameIsEmptyException,
            PriceIsNotValidException,
            CountryIsEmptyException,
            CityIsEmptyException,
            DescriptionIsEmptyException,
            MaxDiscountIsNotValidException,
            DiscountStepIsNotValidException,
            TourTypeIsEmptyException,
            HotelTypeIsEmptyException,
            PersonNumberIsNotValidException,
            StartDateIsNotValidException,
            EndDateIsNotValidException {

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
    }

    private static void checkForNameIsNotEmpty(String name) throws NameIsEmptyException {
        if (FieldValidator.fieldIsEmpty(name)) throw new NameIsEmptyException();
    }

    private static void checkForValidPrice(String priceString) throws PriceIsNotValidException {
        if (!FieldValidator.fieldIsValidBigDecimal(priceString)) {
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
        if (!FieldValidator.fieldIsValidInteger(maxDiscountString)) {
            throw new MaxDiscountIsNotValidException();
        }

        int maxDiscount = Integer.parseInt(maxDiscountString);
        if (maxDiscount < MAX_DISCOUNT_MIN || maxDiscount > MAX_DISCOUNT_MAX) {
            throw new MaxDiscountIsNotValidException();
        }
    }

    private static void checkForValidDiscountStep(String discountStepString) throws DiscountStepIsNotValidException {
        if (!FieldValidator.fieldIsValidDouble(discountStepString)) {
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
        if (!FieldValidator.fieldIsValidInteger(personNumberString)) {
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
        if (startDate.isBefore(LocalDate.now())) {
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
