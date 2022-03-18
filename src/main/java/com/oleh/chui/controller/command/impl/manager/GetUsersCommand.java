package com.oleh.chui.controller.command.impl.manager;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.model.entity.User;
import com.oleh.chui.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Returns Users page.
 *
 * @author Oleh Chui
 */
public class GetUsersCommand implements Command {

    private static final String USER_LIST = "userList";

    private final UserService userService;

    public GetUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> userList = userService.findAllUsers();

        request.setAttribute(USER_LIST, userList);

        return JspFilePath.MANAGER_USERS;
    }
}
