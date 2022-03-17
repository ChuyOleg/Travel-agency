package com.oleh.chui.controller.validator;

import com.oleh.chui.model.dto.TourDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

import static com.oleh.chui.controller.validator.alias.TourExceptionNamesForRequest.*;
import static org.mockito.Mockito.*;

class TourValidatorTest {

    private final HttpServletRequest REQUEST = mock(HttpServletRequest.class);

    private static final String CORRECT_NAME = "Best tour";
    private static final String CORRECT_PRICE = "1100";
    private static final String CORRECT_COUNTRY = "Spain";
    private static final String CORRECT_CITY = "Madrid";
    private static final String CORRECT_DESCRIPTION = "Some info";
    private static final String CORRECT_MAX_DISCOUNT = "10";
    private static final String CORRECT_DISCOUNT_STEP = "1";
    private static final String CORRECT_TOUR_TYPE = "VACATION";
    private static final String CORRECT_HOTEL_TYPE = "FIVE_STARS";
    private static final String CORRECT_PERSON_NUMBER = "2";
    private static final String CORRECT_START_DATE = "2022-06-15";
    private static final String CORRECT_END_DATE = "2022-06-28";
    private static final String IS_BURNING = "on";

    private static final String INCORRECT_NAME = "";
    private static final String INCORRECT_PRICE = "1100,k";
    private static final String INCORRECT_COUNTRY = "";
    private static final String INCORRECT_CITY = "";
    private static final String INCORRECT_DESCRIPTION = "";
    private static final String INCORRECT_MAX_DISCOUNT = "10,a";
    private static final String INCORRECT_DISCOUNT_STEP = "-5";
    private static final String INCORRECT_TOUR_TYPE = "";
    private static final String INCORRECT_HOTEL_TYPE = "";
    private static final String INCORRECT_PERSON_NUMBER = "-3";
    private static final String INCORRECT_START_DATE = "2022-02-23";
    private static final String INCORRECT_END_DATE = "2022-02-21";

    private TourDto CORRECT_TOUR_DTO;

    @BeforeEach
    void init() {
        CORRECT_TOUR_DTO = new TourDto(
                CORRECT_NAME,
                CORRECT_PRICE,
                CORRECT_COUNTRY,
                CORRECT_CITY,
                CORRECT_DESCRIPTION,
                CORRECT_MAX_DISCOUNT,
                CORRECT_DISCOUNT_STEP,
                CORRECT_TOUR_TYPE,
                CORRECT_HOTEL_TYPE,
                CORRECT_PERSON_NUMBER,
                CORRECT_START_DATE,
                CORRECT_END_DATE,
                IS_BURNING
        );
    }

    @Test
    void checkValidateShouldReturnTrue() {
        assertTrue(TourValidator.validate(CORRECT_TOUR_DTO, REQUEST));
    }

    @Test
    void checkValidateTourWithIncorrectName() {
        final TourDto TOUR_WITH_INCORRECT_NAME = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_NAME.setName(INCORRECT_NAME);

        boolean isValid = TourValidator.validate(TOUR_WITH_INCORRECT_NAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(NAME_IS_EMPTY, true);
    }

    @Test
    void checkValidateTourWithIncorrectPrice() {
        final TourDto TOUR_WITH_INCORRECT_PRICE = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_PRICE.setPrice(INCORRECT_PRICE);

        boolean isValid = TourValidator.validate(TOUR_WITH_INCORRECT_PRICE, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(PRICE_IS_NOT_VALID, true);
    }

    @Test
    void checkValidateTourWithIncorrectCountry() {
        final TourDto TOUR_WITH_INCORRECT_COUNTRY = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_COUNTRY.setCountry(INCORRECT_COUNTRY);

        boolean isValid = TourValidator.validate(TOUR_WITH_INCORRECT_COUNTRY, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(COUNTRY_IS_EMPTY, true);
    }

    @Test
    void checkValidateTourWithIncorrectCity() {
        final TourDto TOUR_WITH_INCORRECT_CITY = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_CITY.setCity(INCORRECT_CITY);

        boolean isValid = TourValidator.validate(TOUR_WITH_INCORRECT_CITY, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(CITY_IS_EMPTY, true);
    }

    @Test
    void checkValidateTourWithIncorrectDescription() {
        final TourDto TOUR_WITH_INCORRECT_DESCRIPTION = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_DESCRIPTION.setDescription(INCORRECT_DESCRIPTION);

        boolean isValid = TourValidator.validate(TOUR_WITH_INCORRECT_DESCRIPTION, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(DESCRIPTION_IS_EMPTY, true);
    }

    @Test
    void checkValidateTourWithIncorrectMaxDiscount() {
        final TourDto TOUR_WITH_INCORRECT_MAX_DISCOUNT = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_MAX_DISCOUNT.setMaxDiscount(INCORRECT_MAX_DISCOUNT);

        boolean isValid = TourValidator.validate(TOUR_WITH_INCORRECT_MAX_DISCOUNT, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(MAX_DISCOUNT_IS_NOT_VALID, true);
    }

    @Test
    void checkValidateTourWithIncorrectDiscountStep() {
        final TourDto TOUR_WITH_INCORRECT_DISCOUNT_STEP = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_DISCOUNT_STEP.setDiscountStep(INCORRECT_DISCOUNT_STEP);

        boolean isValid = TourValidator.validate(TOUR_WITH_INCORRECT_DISCOUNT_STEP, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(DISCOUNT_STEP_IS_NOT_VALID, true);
    }

    @Test
    void checkValidateTourWithIncorrectTourType() {
        final TourDto TOUR_WITH_INCORRECT_TOUR_TYPE = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_TOUR_TYPE.setTourType(INCORRECT_TOUR_TYPE);

        boolean isValid = TourValidator.validate(TOUR_WITH_INCORRECT_TOUR_TYPE, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(TOUR_TYPE_IS_EMPTY, true);
    }

    @Test
    void checkValidateTourWithIncorrectHotelType() {
        final TourDto TOUR_WITH_INCORRECT_HOTEL_TYPE = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_HOTEL_TYPE.setHotelType(INCORRECT_HOTEL_TYPE);

        boolean isValid = TourValidator.validate(TOUR_WITH_INCORRECT_HOTEL_TYPE, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(HOTEL_TYPE_IS_EMPTY, true);
    }

    @Test
    void checkValidateTourWithIncorrectPersonNumber() {
        final TourDto TOUR_WITH_INCORRECT_PERSON_NUMBER = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_PERSON_NUMBER.setPersonNumber(INCORRECT_PERSON_NUMBER);

        boolean isValid = TourValidator.validate(TOUR_WITH_INCORRECT_PERSON_NUMBER, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(PERSON_NUMBER_IS_NOT_VALID, true);
    }

    @Test
    void checkValidateTourWithIncorrectStartDate() {
        final TourDto TOUR_WITH_INCORRECT_START_DATE = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_START_DATE.setStartDate(INCORRECT_START_DATE);

        boolean isValid = TourValidator.validate(TOUR_WITH_INCORRECT_START_DATE, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(START_DATE_IS_NOT_VALID, true);
    }

    @Test
    void checkValidateTourWithIncorrectEndDate() {
        final TourDto TOUR_WITH_INCORRECT_END_DATE = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_END_DATE.setEndDate(INCORRECT_END_DATE);

        boolean isValid = TourValidator.validate(TOUR_WITH_INCORRECT_END_DATE, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(END_DATE_IS_NOT_VALID, true);
    }


    @Test
    void checkValidateDiscountInfoShouldReturnTrue() {
        assertTrue(TourValidator.validateDiscountInfo(CORRECT_TOUR_DTO, REQUEST));
    }

    @Test
    void checkValidateDiscountInfoWithIncorrectMaxDiscount() {
        final TourDto TOUR_WITH_INCORRECT_MAX_DISCOUNT = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_MAX_DISCOUNT.setMaxDiscount(INCORRECT_MAX_DISCOUNT);

        boolean isValid = TourValidator.validateDiscountInfo(TOUR_WITH_INCORRECT_MAX_DISCOUNT, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(MAX_DISCOUNT_IS_NOT_VALID, true);
    }

    @Test
    void checkValidateDiscountInfoWithIncorrectDiscountStep() {
        final TourDto TOUR_WITH_INCORRECT_DISCOUNT_STEP = CORRECT_TOUR_DTO;
        TOUR_WITH_INCORRECT_DISCOUNT_STEP.setDiscountStep(INCORRECT_DISCOUNT_STEP);

        boolean isValid = TourValidator.validateDiscountInfo(TOUR_WITH_INCORRECT_DISCOUNT_STEP, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(DISCOUNT_STEP_IS_NOT_VALID, true);
    }

}