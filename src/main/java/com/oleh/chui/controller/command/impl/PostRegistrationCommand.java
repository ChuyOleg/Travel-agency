package com.oleh.chui.controller.command.impl;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.mapper.UserInfoMapper;
import com.oleh.chui.controller.exception.user.*;
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

        boolean userDtoIsValid = validateUserDto(userDto,request);

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



    private boolean validateUserDto(UserDto userDto, HttpServletRequest req) {
        try {
            UserValidator.validate(userDto);
            return true;
        } catch (UsernameSizeOutOfBoundsException e) {
            logger.warn("Username size out of bounds ({})", userDto.getUsername());
            req.setAttribute("usernameSizeOutOfBoundsException", true);
        } catch (PasswordSizeOutOfBoundsException e) {
            logger.warn("Password size out of bounds ({})", userDto.getPassword());
            req.setAttribute("passwordSizeOutOfBoundsException", true);
        } catch (PasswordNotMatchTemplateException e) {
            logger.warn("Password not match template ({})", userDto.getPassword());
            req.setAttribute("passwordNotMatchTemplateException", true);
        } catch (PasswordsNotMatchException e) {
            logger.warn("Passwords not match");
            req.setAttribute("passwordsNotMatchException", true);
        } catch (FirstNameSizeOutOfBoundsException e) {
            logger.warn("First name size out of bounds ({})", userDto.getFirstName());
            req.setAttribute("firstNameSizeOutOfBoundsException", true);
        } catch (LastNameSizeOutOfBoundsException e) {
            logger.warn("Last name size out of bounds ({})", userDto.getLastName());
            req.setAttribute("lastNameSizeOutOfBoundsException", true);
        } catch (EmailSizeOutOfBoundsException e) {
            logger.warn("Email size out of bounds ({})", userDto.getEmail());
            req.setAttribute("emailSizeOutOfBoundsException", true);
        } catch (EmailNotMatchTemplateException e) {
            logger.warn("Email not match template ({})", userDto.getEmail());
            req.setAttribute("emailNotMatchTemplateException", true);
        }

        return false;
    }

}
