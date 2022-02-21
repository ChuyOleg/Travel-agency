package com.oleh.chui.controller.command.impl.manager;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.model.entity.Order;
import com.oleh.chui.model.entity.User;
import com.oleh.chui.model.service.OrderService;
import com.oleh.chui.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class GetUserPageCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String USER = "user";
    private static final String ORDER_List = "orderList";

    private final UserService userService;
    private final OrderService orderService;

    public GetUserPageCommand(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getParameter(USER_ID));

        Optional<User> userOptional = userService.findById(userId);
        List<Order> orderList = orderService.findAllByUserId(userId);

        userOptional.ifPresent(user -> {
            request.setAttribute(USER, user);
            request.setAttribute(ORDER_List, orderList);
        });

        return JspFilePath.MANAGER_USER_PAGE;
    }
}
