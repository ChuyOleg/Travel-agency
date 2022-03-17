package com.oleh.chui.controller.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static com.oleh.chui.controller.validator.alias.CatalogFilterExceptionNamesForRequest.*;

class FilterParametersValidatorTest {

    private final HttpServletRequest REQUEST = mock(HttpServletRequest.class);

    private static final String PERSON_NUMBER_KEY = "personNumber";
    private static final String MIN_PRICE_KEY = "minPrice";
    private static final String MAX_PRICE_KEY = "maxPrice";

    private static final String CORRECT_PERSON_NUMBER = "2";
    private static final String CORRECT_MIN_PRICE = "400";
    private static final String CORRECT_MAX_PRICE = "1500";

    private static final String INCORRECT_PERSON_NUMBER = "2,5";
    private static final String INCORRECT_MIN_PRICE = "v";
    private static final String INCORRECT_MAX_PRICE = "five hundreds";

    private Map<String, String> CORRECT_FILTER_PARAMETERS;

    @BeforeEach
    void init() {
        CORRECT_FILTER_PARAMETERS = new HashMap<>();
        CORRECT_FILTER_PARAMETERS.put(PERSON_NUMBER_KEY, CORRECT_PERSON_NUMBER);
        CORRECT_FILTER_PARAMETERS.put(MIN_PRICE_KEY, CORRECT_MIN_PRICE);
        CORRECT_FILTER_PARAMETERS.put(MAX_PRICE_KEY, CORRECT_MAX_PRICE);
    }

    @Test
    void checkValidateShouldReturnTrue() {
        assertTrue(FilterParametersValidator.validate(CORRECT_FILTER_PARAMETERS, REQUEST));
    }

    @Test
    void checkValidateWithIncorrectPersonNumber() {
        final Map<String, String> FILTER_PARAMETERS_WITH_INCORRECT_PERSON_NUMBER = CORRECT_FILTER_PARAMETERS;
        FILTER_PARAMETERS_WITH_INCORRECT_PERSON_NUMBER.put(PERSON_NUMBER_KEY, INCORRECT_PERSON_NUMBER);

        boolean areValid = FilterParametersValidator.validate(FILTER_PARAMETERS_WITH_INCORRECT_PERSON_NUMBER, REQUEST);

        assertFalse(areValid);
        verify(REQUEST, times(1)).setAttribute(INVALID_PERSON_NUMBER, true);
    }

    @Test
    void checkValidateWithIncorrectMinPrice() {
        final Map<String, String> FILTER_PARAMETERS_WITH_INCORRECT_MIN_PRICE = CORRECT_FILTER_PARAMETERS;
        FILTER_PARAMETERS_WITH_INCORRECT_MIN_PRICE.put(MIN_PRICE_KEY, INCORRECT_MIN_PRICE);

        boolean areValid = FilterParametersValidator.validate(FILTER_PARAMETERS_WITH_INCORRECT_MIN_PRICE, REQUEST);

        assertFalse(areValid);
        verify(REQUEST, times(1)).setAttribute(INVALID_MIN_PRICE, true);
    }

    @Test
    void checkValidateWithIncorrectMaxPrice() {
        final Map<String, String> FILTER_PARAMETERS_WITH_INCORRECT_MAX_PRICE = CORRECT_FILTER_PARAMETERS;
        FILTER_PARAMETERS_WITH_INCORRECT_MAX_PRICE.put(MAX_PRICE_KEY, INCORRECT_MAX_PRICE);

        boolean areValid = FilterParametersValidator.validate(FILTER_PARAMETERS_WITH_INCORRECT_MAX_PRICE, REQUEST);

        assertFalse(areValid);
        verify(REQUEST, times(1)).setAttribute(INVALID_MAX_PRICE, true);
    }

}