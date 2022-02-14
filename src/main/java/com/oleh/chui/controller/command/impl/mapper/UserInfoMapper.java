package com.oleh.chui.controller.command.impl.mapper;

import com.oleh.chui.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public class UserInfoMapper {

    public UserDto fetchUserDtoFromRequest(HttpServletRequest req) {
        return new UserDto(
                req.getParameter("username"),
                req.getParameter("password"),
                req.getParameter("passwordCopy"),
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("email")
        );
    }

    public void insertUserDtoIntoRequest(UserDto userDto, HttpServletRequest req) {
        req.setAttribute("username", userDto.getUsername());
        req.setAttribute("firstName", userDto.getFirstName());
        req.setAttribute("lastName", userDto.getLastName());
        req.setAttribute("email", userDto.getEmail());
    }

}
