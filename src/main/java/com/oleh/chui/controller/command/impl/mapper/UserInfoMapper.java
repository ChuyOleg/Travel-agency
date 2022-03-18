package com.oleh.chui.controller.command.impl.mapper;

import com.oleh.chui.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Manages of fetching and inserting parameters related with User info
 * from or into Request.
 *
 * @author Oleh Chui
 */
public class UserInfoMapper {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_COPY = "passwordCopy";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";

    public UserDto fetchUserDtoFromRequest(HttpServletRequest req) {
        return new UserDto(
                req.getParameter(USERNAME),
                req.getParameter(PASSWORD),
                req.getParameter(PASSWORD_COPY),
                req.getParameter(FIRST_NAME),
                req.getParameter(LAST_NAME),
                req.getParameter(EMAIL)
        );
    }

    public void insertUserDtoIntoRequest(UserDto userDto, HttpServletRequest req) {
        req.setAttribute(USERNAME, userDto.getUsername());
        req.setAttribute(FIRST_NAME, userDto.getFirstName());
        req.setAttribute(LAST_NAME, userDto.getLastName());
        req.setAttribute(EMAIL, userDto.getEmail());
    }

}
