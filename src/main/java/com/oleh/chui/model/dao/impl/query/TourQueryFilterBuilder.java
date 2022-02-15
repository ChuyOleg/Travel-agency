package com.oleh.chui.model.dao.impl.query;

import com.oleh.chui.model.entity.HotelType;
import com.oleh.chui.model.entity.TourType;

import java.util.*;

public class TourQueryFilterBuilder {

    private static final String WHERE = " WHERE";
    private static final String AND = " AND";
    private static final String OR = " OR";
    private static final String TOUR_TYPE = " tour_type_id = (SELECT tour_type_id FROM tour_types WHERE tour_type = '%s')";
    private static final String HOTEL_TYPE = " hotel_type_id = (SELECT hotel_type_id FROM hotel_types WHERE hotel_type = '%s')";
    private static final String PRICE_GREATER_THAN = " price >= ?";
    private static final String PRICE_LESS_THAN = " price <= ?";
    private static final String PERSON_NUMBER = " person_number = ?";

    private static final String MIN_PRICE_KEY = "minPrice";
    private static final String MAX_PRICE_KEY = "maxPrice";
    private static final String PERSON_NUMBER_KEY = "personNumber";

    private TourQueryFilterBuilder() {}

    public static String buildTourQueryFilterForFindAll(Map<String, String> filterFieldMap) {
        if (filterFieldMap.isEmpty()) {
            return TourQueries.FIND_ALL_ORDER_BURNING_FIRST;
        } else {
            String condition = buildFullCondition(filterFieldMap);

            return TourQueries.FIND_ALL + WHERE + condition + TourQueries.ORDER_BURNING_FIRST;
        }
    }

    public static String buildTourQueryFilterForFindCount(Map<String, String> filterFieldMap) {
        if (filterFieldMap.isEmpty()) {
            return TourQueries.FIND_ALL_COUNT;
        } else {
            String condition = buildFullCondition(filterFieldMap);

            return TourQueries.FIND_ALL_COUNT + WHERE + condition;
        }
    }

    private static String buildFullCondition(Map<String, String> filterFieldMap) {
        StringBuilder conditions = new StringBuilder();

        buildTourTypeCondition(conditions, filterFieldMap);
        buildHotelTypeCondition(conditions, filterFieldMap);
        buildPriceAndPersonNumberCondition(conditions, filterFieldMap);

        return conditions.toString();
    }

    private static void buildTourTypeCondition(StringBuilder conditions, Map<String, String> filterFieldMap) {
        StringBuilder condition = new StringBuilder();
        for (TourType.TourTypeEnum tourTypeEnum : TourType.TourTypeEnum.values()) {
            if (filterFieldMap.containsKey(tourTypeEnum.name())) {
                String addition = String.format(TOUR_TYPE, tourTypeEnum.name());
                appendConditionOR(condition, addition);
            }
        }

        if (conditionIsNotEmpty(condition)) {
            appendConditionAND(conditions,"(" + condition + ")");
        }
    }

    private static void buildHotelTypeCondition(StringBuilder conditions, Map<String, String> filterFieldMap) {
        StringBuilder condition = new StringBuilder();
        for (HotelType.HotelTypeEnum hotelTypeEnum : HotelType.HotelTypeEnum.values()) {
            if (filterFieldMap.containsKey(hotelTypeEnum.name())) {
                String addition = String.format(HOTEL_TYPE, hotelTypeEnum.name());
                appendConditionOR(condition, addition);
            }
        }

        if (conditionIsNotEmpty(condition)) {
            appendConditionAND(conditions, "(" + condition + ")");
        }
    }

    private static void buildPriceAndPersonNumberCondition(StringBuilder conditions, Map<String, String> filterFieldMap) {
        for (Map.Entry<String, String> entry : filterFieldMap.entrySet()) {
            switch (entry.getKey()) {
                case MIN_PRICE_KEY:
                    appendConditionAND(conditions, PRICE_GREATER_THAN);
                    break;
                case MAX_PRICE_KEY:
                    appendConditionAND(conditions, PRICE_LESS_THAN);
                    break;
                case PERSON_NUMBER_KEY:
                    appendConditionAND(conditions, PERSON_NUMBER);
                    break;
            }
        }
    }

    private static void appendConditionAND(StringBuilder condition, String addition) {
        if (conditionIsNotEmpty(condition)) {
            condition.append(AND);
        }
        condition.append(addition);
    }

    private static void appendConditionOR(StringBuilder condition, String addition) {
        if (conditionIsNotEmpty(condition)) {
            condition.append(OR);
        }
        condition.append(addition);
    }

    private static boolean conditionIsNotEmpty(StringBuilder condition) {
        return condition.length() > 0;
    }

}
