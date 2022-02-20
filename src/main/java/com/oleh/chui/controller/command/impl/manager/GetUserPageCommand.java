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

    private final UserService userService;
    private final OrderService orderService;

    public GetUserPageCommand(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getParameter("userId"));

        Optional<User> userOptional = userService.findById(userId);
        List<Order> orderList = orderService.findAllByUserId(userId);

        userOptional.ifPresent(user -> {
            request.setAttribute("user", user);
            request.setAttribute("orderList", orderList);
        });

        return JspFilePath.MANAGER_USER_PAGE;
    }
}