package com.oleh.chui.controller.command.impl.manager;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class PostChangeOrderStatusCommand implements Command {

    private final String URL_USER_ID_PARAMETER = "?userId=";

    private final OrderService orderService;

    public PostChangeOrderStatusCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        Long userId = Long.valueOf(request.getParameter("userId"));
        String newStatus = request.getParameter("newStatus");

        orderService.changeStatus(newStatus, orderId);

        return UriPath.REDIRECT + UriPath.MANAGER_USER_PAGE + URL_USER_ID_PARAMETER + userId;
    }
}
