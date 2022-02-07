package com.oleh.chui.controller.command.impl;

import com.oleh.chui.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class PostRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        return "";
    }
}
