package com.oleh.chui.controller.command.impl.admin;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Process blocking and unblocking User.
 *
 * @author Oleh Chui
 */
public class PostBlockUnblockUserCommand implements Command {

    private static final String ID = "id";
    private static final String IS_BLOCKED = "isBlocked";
    private static final String FALSE = "false";
    private static final String TRUE = "true";

    private final UserService userService;

    public PostBlockUnblockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getParameter(ID));
        String isBlocked = request.getParameter(IS_BLOCKED);

        if (isBlocked.equals(FALSE)) {
            userService.blockById(userId);
        } else if (isBlocked.equals(TRUE)) {
            userService.unblockById(userId);
        }

        return UriPath.REDIRECT + UriPath.MANAGER_USERS;
    }
}
