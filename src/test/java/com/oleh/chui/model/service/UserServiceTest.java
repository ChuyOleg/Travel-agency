package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.UserDao;
import com.oleh.chui.model.dto.UserDto;
import com.oleh.chui.model.entity.User;
import com.oleh.chui.model.exception.user.AuthenticationException;
import com.oleh.chui.model.exception.user.IsBlockedException;
import com.oleh.chui.model.exception.user.UsernameIsReservedException;
import com.oleh.chui.model.service.util.PBKDF2PasswordEncoder;
import com.oleh.chui.model.service.util.PasswordEncoder;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    UserDao userDao = mock(UserDao.class);
    PasswordEncoder passwordEncoder = new PBKDF2PasswordEncoder();
    UserService userService = new UserService(passwordEncoder, userDao);

    private static final String USERNAME = "Oleh";
    private static final String PASSWORD = "SUPER_PASSWORD_31";
    private static final String FAKE_PASSWORD = "FAKE_PASSWORD";

    private static final User UNBLOCKED_USER = User.builder()
            .id(1L)
            .username(USERNAME)
            .password(PASSWORD)
            .blocked(false)
            .build();

    private static final User BLOCKED_USER = User.builder()
            .id(1L)
            .username(USERNAME)
            .password(PASSWORD)
            .blocked(true)
            .build();

    private static final UserDto USER_DTO = new UserDto(
            USERNAME,
            PASSWORD,
            PASSWORD,
            "FIRST_NAME",
            "LAST_NAME",
            "EMAIL"
    );

    @Test
    void checkDoAuthenticationShouldNotThrowException() {
        String encodedPassword = passwordEncoder.encode(PASSWORD);
        when(userDao.findByUsernameAndPassword(USERNAME, encodedPassword)).thenReturn(Optional.of(UNBLOCKED_USER));

        assertDoesNotThrow(() -> userService.doAuthentication(USERNAME, PASSWORD));
    }

    @Test
    void checkDoAuthenticationShouldThrowIsBlockedException() {
        String encodedPassword = passwordEncoder.encode(PASSWORD);
        when(userDao.findByUsernameAndPassword(USERNAME, encodedPassword)).thenReturn(Optional.of(BLOCKED_USER));

        assertThrows(
                IsBlockedException.class,
                () -> userService.doAuthentication(USERNAME, PASSWORD)
        );
    }

    @Test
    void checkDoAuthenticationShouldThrowAuthenticationException() {
        String encodedPassword = passwordEncoder.encode(PASSWORD);
        when(userDao.findByUsernameAndPassword(USERNAME, encodedPassword)).thenReturn(Optional.empty());

        assertThrows(
                AuthenticationException.class,
                () -> userService.doAuthentication(USERNAME, PASSWORD)
        );
    }

    @Test
    void checkRegisterNewAccountShouldThrowUsernameIsReservedException() {
        when(userDao.usernameIsReserved(USERNAME)).thenReturn(true);

        assertThrows(
                UsernameIsReservedException.class,
                () -> userService.registerNewAccount(USER_DTO)
        );
    }

    @Test
    void checkRegisterNewAccountShouldWorkWithoutException() {
        when(userDao.usernameIsReserved(USERNAME)).thenReturn(false);

        assertDoesNotThrow(() -> userService.registerNewAccount(USER_DTO));

        verify(userDao, times(1)).create(UNBLOCKED_USER);
    }

    @Test
    void checkFindByUsernameAndPassword() {
        when(userService.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(Optional.of(UNBLOCKED_USER));
        when(userService.findByUsernameAndPassword(USERNAME, FAKE_PASSWORD)).thenReturn(Optional.empty());

        assertEquals(Optional.of(UNBLOCKED_USER), userService.findByUsernameAndPassword(USERNAME, PASSWORD));
        assertNotEquals(Optional.of(UNBLOCKED_USER), userService.findByUsernameAndPassword(USERNAME, FAKE_PASSWORD));
    }

}