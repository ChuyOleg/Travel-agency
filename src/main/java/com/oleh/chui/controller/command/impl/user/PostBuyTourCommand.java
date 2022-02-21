package com.oleh.chui.controller.command.impl.user;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PostBuyTourCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String TOUR_ID = "tourId";

    private final OrderService orderService;

    public PostBuyTourCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Long userId = (Long) session.getAttribute(USER_ID);
        Long tourId = Long.valueOf(request.getParameter(TOUR_ID));

        orderService.createOrder(userId, tourId);

        return UriPath.REDIRECT + UriPath.CATALOG;
    }
}
