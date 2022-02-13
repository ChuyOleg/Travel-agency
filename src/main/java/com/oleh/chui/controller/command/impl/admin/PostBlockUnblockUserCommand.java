package com.oleh.chui.controller.command.impl.admin;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PostBlockUnblockUserCommand implements Command {

    private final Logger logger = LogManager.getLogger(PostBlockUnblockUserCommand.class);
    private final UserService userService;

    public PostBlockUnblockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getParameter("id"));
        String isBlocked = request.getParameter("isBlocked");

        if (isBlocked.equals("false")) {
            userService.blockById(userId);
        } else if (isBlocked.equals("true")) {
            userService.unblockById(userId);
        }

        return UriPath.REDIRECT + UriPath.ADMIN_USERS;
    }
}
