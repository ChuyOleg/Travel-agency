package com.oleh.chui.controller.command.impl;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

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

        // TODO: do authentication

        // TODO: put into Session

        return "";
    }

}
