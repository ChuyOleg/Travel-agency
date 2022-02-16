package com.oleh.chui.controller.command.impl;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.model.entity.User;
import com.oleh.chui.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class PostLogInCommand implements Command {

    private final Logger logger = LogManager.getLogger(PostLogInCommand.class);
    private final UserService userService;

    public PostLogInCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> userOptional = userService.findByUsernameAndPassword(username, password);
        if (userOptional.isPresent()) {
            if (!userOptional.get().isBlocked()) {
                request.getSession().setAttribute("userId", userOptional.get().getId());
                request.getSession().setAttribute("role", userOptional.get().getRole().getValue().toString());
                return UriPath.REDIRECT + UriPath.CATALOG;
            } else {
                request.setAttribute("accountIsBlocked", true);
            }
        } else {
            request.setAttribute("authenticationException", true);
        }

        return JspFilePath.LOGIN;
    }
}
