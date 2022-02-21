package com.oleh.chui.controller.command.impl.common;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.mapper.CatalogMapper;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.controller.validator.FilterParametersValidator;
import com.oleh.chui.controller.validator.util.FieldValidator;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class GetCatalogCommand implements Command {

    private final CatalogMapper catalogMapper = new CatalogMapper();
    private final TourService tourService;
    private static final Integer PAGE_SIZE = 4;
    private static final Integer START_PAGE_NUMBER = 1;
    private static final String PAGES_NUMBER = "pagesNumber";
    private static final String TOUR_LIST = "tourList";
    private static final String PAGE = "page";

    public GetCatalogCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Map<String, String> filterParameters = catalogMapper.fetchFilterParametersFromRequest(request);

        boolean fieldsAreValid = FilterParametersValidator.validate(filterParameters, request);

        if (fieldsAreValid) {
            int activePageNumber = getActivePageNumber(request);

            List<Tour> tourList = tourService.findAllUsingFiltersAndPagination(filterParameters, PAGE_SIZE, activePageNumber);
            int pagesNumber = tourService.getPagesNumber(filterParameters, PAGE_SIZE);

            request.setAttribute(PAGES_NUMBER, pagesNumber);
            request.setAttribute(TOUR_LIST, tourList);
        }

        catalogMapper.insertInfoIntoRequest(filterParameters, request);
        return JspFilePath.CATALOG;
    }

    private int getActivePageNumber(HttpServletRequest request) {
        String pageNumberString = request.getParameter(PAGE);

        if (!FieldValidator.fieldIsEmpty(pageNumberString)) {
            return Integer.parseInt(pageNumberString);
        } else {
            return START_PAGE_NUMBER;
        }
    }
}
