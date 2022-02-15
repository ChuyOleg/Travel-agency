package com.oleh.chui.controller.command.impl;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.mapper.CatalogMapper;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.controller.validator.util.FieldValidator;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class GetCatalogCommand implements Command {

    private final CatalogMapper catalogMapper = new CatalogMapper();
    private final TourService tourService;
    private final Integer PAGE_SIZE = 4;
    private final Integer START_PAGE_NUMBER = 1;

    public GetCatalogCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Map<String, String> filterParameters = catalogMapper.fetchFilterParametersFromRequest(request);

        boolean fieldsAreValid = validateFields(filterParameters, request);

        if (fieldsAreValid) {
            int activePageNumber = getActivePageNumber(request);

            List<Tour> tourList = tourService.findAllUsingFiltersAndPagination(filterParameters, PAGE_SIZE, activePageNumber);
            int pagesNumber = tourService.getPagesNumber(filterParameters, PAGE_SIZE);

            request.setAttribute("pagesNumber", pagesNumber);
            request.setAttribute("tourList", tourList);
        }

        catalogMapper.insertInfoIntoRequest(filterParameters, request);
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

    private int getActivePageNumber(HttpServletRequest request) {
        String pageNumberString = request.getParameter("page");

        if (pageNumberString != null && !pageNumberString.isEmpty()) {
            return Integer.parseInt(pageNumberString);
        } else {
            return START_PAGE_NUMBER;
        }
    }
}
