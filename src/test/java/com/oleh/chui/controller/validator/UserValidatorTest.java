package com.oleh.chui.controller.validator;

import com.oleh.chui.model.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static com.oleh.chui.controller.validator.alias.UserExceptionNamesForRequest.*;

class UserValidatorTest {

    private final HttpServletRequest REQUEST = mock(HttpServletRequest.class);

    private static final String CORRECT_USERNAME = "Fabio_Fernandez";
    private static final String CORRECT_FIRST_NAME = "Fabio";
    private static final String CORRECT_LAST_NAME = "Hernandez";
    private static final String CORRECT_EMAIL = "fabio@gmail.com";
    private static final String CORRECT_PASSWORD = "Fabio2002";
    private static final String CORRECT_PASSWORD_COPY = CORRECT_PASSWORD;

    private static final String INCORRECT_USERNAME = "Fab";
    private static final String INCORRECT_FIRST_NAME = "";
    private static final String INCORRECT_LAST_NAME = "";
    private static final String INCORRECT_EMAIL = "fabio.gmail.com";
    private static final String INCORRECT_PASSWORD = "11111111";
    private static final String INCORRECT_PASSWORD_COPY = "12345678";

    private UserDto CORRECT_USER_DTO;

    @BeforeEach
    void init() {
        CORRECT_USER_DTO = new UserDto(
                CORRECT_USERNAME,
                CORRECT_PASSWORD,
                CORRECT_PASSWORD_COPY,
                CORRECT_FIRST_NAME,
                CORRECT_LAST_NAME,
                CORRECT_EMAIL
        );
    }

    @Test
    void checkValidateShouldReturnTrue() {
        assertTrue(UserValidator.validate(CORRECT_USER_DTO, REQUEST));
    }

    @Test
    void checkValidateUserWithIncorrectUsername() {
        final UserDto USER_WITH_INCORRECT_USERNAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_USERNAME.setUsername(INCORRECT_USERNAME);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(USERNAME_SIZE_OUT_OF_BOUNDS, true);

    }

    @Test
    void checkValidateUserWithIncorrectFirstName() {
        final UserDto USER_WITH_INCORRECT_FIRST_NAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_FIRST_NAME.setFirstName(INCORRECT_FIRST_NAME);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_FIRST_NAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(FIRST_NAME_SIZE_OUT_OF_BOUNDS, true);
    }

    @Test
    void checkValidateUserWithIncorrectLastName() {
        final UserDto USER_WITH_INCORRECT_LAST_NAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_LAST_NAME.setLastName(INCORRECT_LAST_NAME);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_LAST_NAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(LAST_NAME_SIZE_OUT_OF_BOUNDS, true);
    }

    @Test
    void checkValidateUserWithIncorrectEmail() {
        final UserDto USER_WITH_INCORRECT_EMAIL = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_EMAIL.setEmail(INCORRECT_EMAIL);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_EMAIL, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(EMAIL_NOT_MATCH_TEMPLATE, true);
    }

    @Test
    void checkValidateUserWithIncorrectPassword() {
        final UserDto USER_WITH_INCORRECT_PASSWORD = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_PASSWORD.setPassword(INCORRECT_PASSWORD);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_PASSWORD, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(PASSWORD_NOT_MATCH_TEMPLATE, true);
    }

    @Test
    void checkValidateUserWithIncorrectPasswordCopy() {
        final UserDto USER_WITH_INCORRECT_PASSWORD_COPY = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_PASSWORD_COPY.setPasswordCopy(INCORRECT_PASSWORD_COPY);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_PASSWORD_COPY, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1)).setAttribute(PASSWORDS_NOT_MATCH, true);
    }

}