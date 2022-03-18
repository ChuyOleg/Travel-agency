package com.oleh.chui.controller.command.impl.mapper;

import com.oleh.chui.controller.validator.util.FieldValidator;
import com.oleh.chui.model.entity.HotelType;
import com.oleh.chui.model.entity.TourType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages of fetching and inserting parameters related with Catalog page
 * from or into Request.
 *
 * @author Oleh Chui
 */
public class CatalogMapper {

    private static final String MIN_PRICE = "minPrice";
    private static final String MAX_PRICE = "maxPrice";
    private static final String PERSON_NUMBER = "personNumber";
    private static final String PAGE = "page";
    private static final String ACTIVE_PAGE_NUMBER = "activePageNumber";
    private static final Integer START_PAGE_NUMBER = 1;

    private final TourInfoMapper tourInfoMapper = new TourInfoMapper();

    public Map<String, String> fetchFilterParametersFromRequest(HttpServletRequest req) {
        Map<String, String> filterParameters = new HashMap<>();

        if (!FieldValidator.fieldIsEmpty(req.getParameter(MIN_PRICE))) filterParameters.put(MIN_PRICE, req.getParameter(MIN_PRICE));
        if (!FieldValidator.fieldIsEmpty(req.getParameter(MAX_PRICE))) filterParameters.put(MAX_PRICE, req.getParameter(MAX_PRICE));
        if (!FieldValidator.fieldIsEmpty(req.getParameter(PERSON_NUMBER))) filterParameters.put(PERSON_NUMBER, req.getParameter(PERSON_NUMBER));

        for (TourType.TourTypeEnum tourTypeEnum : TourType.TourTypeEnum.values()) {
            String tourTypeisChecked = req.getParameter(tourTypeEnum.name());
            if (tourTypeisChecked != null) {
                filterParameters.put(tourTypeEnum.name(), tourTypeisChecked);
            }
        }

        for (HotelType.HotelTypeEnum hotelTypeEnum : HotelType.HotelTypeEnum.values()) {
            String hotelTypeIsChecked = req.getParameter(hotelTypeEnum.name());
            if (hotelTypeIsChecked != null) {
                filterParameters.put(hotelTypeEnum.name(), hotelTypeIsChecked);
            }
        }

        return filterParameters;
    }

    public void insertInfoIntoRequest(Map<String, String> filterParameters, HttpServletRequest req) {
        String pageNumber = req.getParameter(PAGE);

        if (pageNumber != null && !pageNumber.isEmpty()) {
            req.setAttribute(ACTIVE_PAGE_NUMBER, Integer.parseInt(pageNumber));
        } else {
            req.setAttribute(ACTIVE_PAGE_NUMBER, START_PAGE_NUMBER);
        }

        for (Map.Entry<String, String> entry : filterParameters.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }

        tourInfoMapper.insertTourAndHotelTypesIntoRequest(req);
    }

}
