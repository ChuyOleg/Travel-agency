package com.oleh.chui.controller.command.impl.manager;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class PostChangeOrderStatusCommand implements Command {

    private static final String URL_USER_ID_PARAMETER = "?userId=";
    private static final String ORDER_ID = "orderId";
    private static final String USER_ID = "userId";
    private static final String NEW_STATUS = "newStatus";

    private final OrderService orderService;

    public PostChangeOrderStatusCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long orderId = Long.valueOf(request.getParameter(ORDER_ID));
        long userId = Long.parseLong(request.getParameter(USER_ID));
        String newStatus = request.getParameter(NEW_STATUS);

        orderService.changeStatus(newStatus, orderId);

        return UriPath.REDIRECT + UriPath.MANAGER_USER_PAGE + URL_USER_ID_PARAMETER + userId;
    }
}
