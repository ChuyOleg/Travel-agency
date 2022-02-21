package com.oleh.chui.controller.command.impl.guest;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.model.entity.User;
import com.oleh.chui.model.exception.user.AuthenticationException;
import com.oleh.chui.model.exception.user.IsBlockedException;
import com.oleh.chui.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class PostLogInCommand implements Command {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USER_ID = "userId";
    private static final String ROLE = "role";
    private static final String AUTHENTICATION_EXCEPTION = "authenticationException";
    private static final String ACCOUNT_IS_BLOCKED_EXCEPTION = "accountIsBlocked";

    private final UserService userService;

    public PostLogInCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        try {
            User user = userService.doAuthentication(username, password);

            request.getSession().setAttribute(USER_ID, user.getId());
            request.getSession().setAttribute(ROLE, user.getRole().getValue().toString());
            return UriPath.REDIRECT + UriPath.CATALOG;
        } catch (AuthenticationException e) {
            request.setAttribute(AUTHENTICATION_EXCEPTION, true);
        } catch (IsBlockedException e) {
            request.setAttribute(ACCOUNT_IS_BLOCKED_EXCEPTION, true);
        }

        return JspFilePath.LOGIN;
    }
}
