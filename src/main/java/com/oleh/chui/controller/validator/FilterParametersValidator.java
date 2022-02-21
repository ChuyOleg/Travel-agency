package com.oleh.chui.controller.validator;

import com.oleh.chui.controller.exception.filter_parameter.InvalidMaxPriceException;
import com.oleh.chui.controller.exception.filter_parameter.InvalidMinPriceException;
import com.oleh.chui.controller.exception.filter_parameter.InvalidPersonNumberException;
import com.oleh.chui.controller.validator.util.FieldValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.oleh.chui.controller.validator.alias.CatalogFilterExceptionNamesForRequest.*;

public class FilterParametersValidator {

    private static final String PERSON_NUMBER = "personNumber";
    private static final String MIN_PRICE = "minPrice";
    private static final String MAX_PRICE = "maxPrice";

    private static final Logger logger = LogManager.getLogger(FilterParametersValidator.class);

    private FilterParametersValidator() {}

    public static boolean validate(Map<String, String> filterParameters, HttpServletRequest request) {
        try {
            checkForValidPersonNumber(filterParameters.get(PERSON_NUMBER));
            checkForValidMinPrice(filterParameters.get(MIN_PRICE));
            checkForValidMaxPrice(filterParameters.get(MAX_PRICE));
            return true;
        } catch (InvalidMaxPriceException e) {
            logger.warn("<catalog filtration> maxPrice is invalid ({})", filterParameters.get(MAX_PRICE));
            request.setAttribute(INVALID_MAX_PRICE, true);
        } catch (InvalidMinPriceException e) {
            logger.warn("<catalog filtration> minPrice is invalid ({})", filterParameters.get(MIN_PRICE));
            request.setAttribute(INVALID_MIN_PRICE, true);
        } catch (InvalidPersonNumberException e) {
            logger.warn("<catalog filtration> personNumber is invalid ({})", filterParameters.get(PERSON_NUMBER));
            request.setAttribute(INVALID_PERSON_NUMBER, true);
        }

        return false;
    }

    private static void checkForValidPersonNumber(String personNumber) throws InvalidPersonNumberException {
        if (!FieldValidator.fieldIsEmpty(personNumber) && FieldValidator.fieldIsNotValidInteger(personNumber)) {
            throw new InvalidPersonNumberException();
        }
    }

    private static void checkForValidMinPrice(String minPrice) throws InvalidMinPriceException {
        if (!FieldValidator.fieldIsEmpty(minPrice) && FieldValidator.fieldIsNotValidBigDecimal(minPrice)) {
            throw new InvalidMinPriceException();
        }
    }

    private static void checkForValidMaxPrice(String maxPrice) throws InvalidMaxPriceException {
        if (!FieldValidator.fieldIsEmpty(maxPrice) && FieldValidator.fieldIsNotValidBigDecimal(maxPrice)) {
            throw new InvalidMaxPriceException();
        }
    }

}
