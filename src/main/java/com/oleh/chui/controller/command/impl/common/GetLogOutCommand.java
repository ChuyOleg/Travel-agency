package com.oleh.chui.controller.command.impl.common;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.model.entity.Role;

import javax.servlet.http.HttpServletRequest;

/**
 * Process logout from system.
 *
 * @author Oleh Chui
 */
public class GetLogOutCommand implements Command {

    private static final String USER_ID = "userId";
    private static final String ROLE = "role";

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(USER_ID, null);
        request.getSession().setAttribute(ROLE, Role.RoleEnum.UNKNOWN.name());

        return UriPath.REDIRECT + UriPath.LOGIN;
    }

}
