package com.oleh.chui.controller.command.impl;

import com.oleh.chui.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class PostLogInCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // TODO: do authentication

        // TODO: put into Session

        return "";
    }

}
