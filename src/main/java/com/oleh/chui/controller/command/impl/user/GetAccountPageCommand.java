package com.oleh.chui.controller.command.impl.user;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.model.entity.Order;
import com.oleh.chui.model.entity.User;
import com.oleh.chui.model.service.OrderService;
import com.oleh.chui.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class GetAccountPageCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String USER = "user";
    private static final String ORDER_LIST = "orderList";

    private final UserService userService;
    private final OrderService orderService;

    public GetAccountPageCommand(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(USER_ID);

        Optional<User> userOptional = userService.findById(userId);
        List<Order> orderList = orderService.findAllByUserId(userId);

        userOptional.ifPresent(user -> {
            request.setAttribute(USER, user);
            request.setAttribute(ORDER_LIST, orderList);
        });

        return JspFilePath.USER_ACCOUNT;
    }
}
