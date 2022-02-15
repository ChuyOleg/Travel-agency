package com.oleh.chui.controller.command.impl;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.mapper.CatalogFilterMapper;
import com.oleh.chui.controller.command.impl.mapper.CatalogMapper;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.controller.validator.util.FieldValidator;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class GetCatalogFilterCommand implements Command {

    private final CatalogMapper catalogMapper = new CatalogMapper();
    private final CatalogFilterMapper catalogFilterMapper = new CatalogFilterMapper();
    private final TourService tourService;

    public GetCatalogFilterCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Map<String, String> filterParameters = catalogFilterMapper.fetchFilterParametersFromRequest(request);

        boolean fieldsAreValid = validateFields(filterParameters, request);

        if (fieldsAreValid) {
            List<Tour> tourList = tourService.findAllUsingFilters(filterParameters);
            request.setAttribute("tourList", tourList);
        }

        catalogMapper.insertInfoIntoRequest(tourService, request);
        catalogFilterMapper.insertFilterParametersIntoRequest(filterParameters, request);
        return JspFilePath.CATALOG;
    }

    private boolean validateFields(Map<String, String> filterParameters, HttpServletRequest req) {
        String personNumber = filterParameters.get("personNumber");
        String minPrice = filterParameters.get("minPrice");
        String maxPrice = filterParameters.get("maxPrice");

        if (!FieldValidator.fieldIsEmpty(personNumber) && !FieldValidator.fieldIsValidInteger(personNumber)) {
            req.setAttribute("invalidPersonNumber", true);
            return false;
        } else if (!FieldValidator.fieldIsEmpty(minPrice) && !FieldValidator.fieldIsValidBigDecimal(minPrice)) {
            req.setAttribute("invalidMinPrice", true);
            return false;
        } else if (!FieldValidator.fieldIsEmpty(maxPrice) && !FieldValidator.fieldIsValidBigDecimal(maxPrice)) {
            req.setAttribute("invalidMaxPrice", true);
            return false;
        }

        return true;
    }
}
