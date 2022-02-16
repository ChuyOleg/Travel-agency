package com.oleh.chui.controller.command.impl;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.model.entity.Role;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.service.OrderService;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

public class GetTourDetailsCommand implements Command {

    private final OrderService orderService;
    private final TourService tourService;

    public GetTourDetailsCommand(OrderService orderService, TourService tourService) {
        this.orderService = orderService;
        this.tourService = tourService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long tourId = Long.valueOf(request.getParameter("id"));
        Role.RoleEnum role = Role.RoleEnum.valueOf(((String) session.getAttribute("role")));

        Optional<Tour> tourOptional = tourService.findById(tourId);

        tourOptional.ifPresent(tour -> {
            request.setAttribute("tour", tour);
            if (role.equals(Role.RoleEnum.USER)) {
                Long activeUserId = (Long) session.getAttribute("userId");

                if (orderService.isExistedByUserIdAndTourId(activeUserId, tourId)) {
                    request.setAttribute("tourIsBought", true);
                } else {
                    BigDecimal finalPrice = orderService.calculateFinalPrice(activeUserId, tour);
                    request.setAttribute("finalPrice", finalPrice);
                }
            }
        });

        return JspFilePath.TOUR_DETAILS;
    }

}
