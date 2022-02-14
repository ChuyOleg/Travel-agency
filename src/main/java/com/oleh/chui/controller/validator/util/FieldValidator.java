package com.oleh.chui.controller.validator.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FieldValidator {

    private FieldValidator() {}

    public static boolean fieldIsEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }

    public static boolean fieldIsValidInteger(String field) {
        try {
            Integer.parseInt(field);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean fieldIsValidDouble(String field) {
        try {
            Double.parseDouble(field);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean fieldIsValidBigDecimal(String field) {
        try {
            new BigDecimal(field);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
