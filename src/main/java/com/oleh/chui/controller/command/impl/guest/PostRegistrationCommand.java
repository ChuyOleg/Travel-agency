package com.oleh.chui.controller.command.impl.guest;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.mapper.UserInfoMapper;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.controller.validator.UserValidator;
import com.oleh.chui.model.dto.UserDto;
import com.oleh.chui.model.exception.user.UsernameIsReservedException;
import com.oleh.chui.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Manages registration in the system.
 *
 * @author Oleh Chui
 */
public class PostRegistrationCommand implements Command {

    private static final String USERNAME_IS_RESERVED_EXCEPTION = "usernameIsReserved";

    private final UserInfoMapper userInfoMapper = new UserInfoMapper();
    private final UserService userService;

    public PostRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates new account.
     *
     * @param request An instance of HttpServletRequest class.
     * @return String representing Uri to Login (if registration is success) or
     *         Path to Registration JSP file (if registration is failed).
     */
    @Override
    public String execute(HttpServletRequest request) {
        UserDto userDto = userInfoMapper.fetchUserDtoFromRequest(request);

        boolean userDtoIsValid = UserValidator.validate(userDto, request);

        if (userDtoIsValid) {
            try {
                userService.registerNewAccount(userDto);

                return UriPath.REDIRECT + UriPath.LOGIN;
            } catch (UsernameIsReservedException e) {
                request.setAttribute(USERNAME_IS_RESERVED_EXCEPTION, true);
            }
        }

        userInfoMapper.insertUserDtoIntoRequest(userDto, request);
        return JspFilePath.REGISTRATION;
    }
}
