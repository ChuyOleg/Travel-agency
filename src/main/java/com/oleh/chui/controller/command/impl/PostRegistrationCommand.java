package com.oleh.chui.controller.command.impl;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.mapper.UserInfoMapper;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.controller.validator.UserValidator;
import com.oleh.chui.model.dto.UserDto;
import com.oleh.chui.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PostRegistrationCommand implements Command {

    private final Logger logger = LogManager.getLogger(PostRegistrationCommand.class);
    private final UserInfoMapper userInfoMapper = new UserInfoMapper();
    private final UserService userService;

    public PostRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        UserDto userDto = userInfoMapper.fetchUserDtoFromRequest(request);

        boolean userDtoIsValid = UserValidator.validate(userDto, request);

        if (userDtoIsValid) {
            boolean usernameIsReserved = userService.usernameIsReserved(userDto.getUsername());

            if (usernameIsReserved) {
                request.setAttribute("usernameIsReserved", true);
            } else {
                userService.registerNewAccount(userDto);
                return UriPath.REDIRECT + UriPath.LOGIN;
            }
        }

        userInfoMapper.insertUserDtoIntoRequest(userDto, request);
        return JspFilePath.REGISTRATION;
    }
}
