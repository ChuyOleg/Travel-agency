package com.oleh.chui.controller.validator.restriction;

import java.math.BigDecimal;

public class TourRestriction {

    private TourRestriction() {}

    public static final BigDecimal PRICE_MIN = BigDecimal.valueOf(1);
    public static final BigDecimal PRICE_MAX = BigDecimal.valueOf(100000);

    public static final int MAX_DISCOUNT_MIN = 0;
    public static final int MAX_DISCOUNT_MAX = 100;

    public static final int DISCOUNT_STEP_MIN = 0;
    public static final int DISCOUNT_STEP_MAX = 50;

    public static final int PERSON_NUMBER_MIN = 1;
    public static final int PERSON_NUMBER_MAX = 50;


}
