package com.oleh.chui.controller.command.impl.mapper;

import com.oleh.chui.controller.validator.util.FieldValidator;
import com.oleh.chui.model.entity.HotelType;
import com.oleh.chui.model.entity.TourType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CatalogFilterMapper {

    public Map<String, String> fetchFilterParametersFromRequest(HttpServletRequest req) {
        Map<String, String> filterParameters = new HashMap<>();

        if (!FieldValidator.fieldIsEmpty(req.getParameter("minPrice"))) filterParameters.put("minPrice", req.getParameter("minPrice"));
        if (!FieldValidator.fieldIsEmpty(req.getParameter("maxPrice"))) filterParameters.put("maxPrice", req.getParameter("maxPrice"));
        if (!FieldValidator.fieldIsEmpty(req.getParameter("personNumber"))) filterParameters.put("personNumber", req.getParameter("personNumber"));

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

    public void insertFilterParametersIntoRequest(Map<String, String> filterParameters, HttpServletRequest req) {
        for (Map.Entry<String, String> entry : filterParameters.entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
    }

}