package com.oleh.chui.controller.command.impl;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetCatalogCommand implements Command {

    private final TourService tourService;

    public GetCatalogCommand(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Tour> tourList = tourService.findAll();

        request.setAttribute("tourList", tourList);
        return JspFilePath.CATALOG;
    }
}
