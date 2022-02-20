package com.oleh.chui.controller.command.impl;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.model.entity.Role;

import javax.servlet.http.HttpServletRequest;

public class GetLogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("userId", null);
        request.getSession().setAttribute("role", Role.RoleEnum.UNKNOWN);

        return UriPath.REDIRECT + UriPath.LOGIN;
    }

}
