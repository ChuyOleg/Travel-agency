package com.oleh.chui.controller.validator;

import com.oleh.chui.controller.exception.user.*;
import com.oleh.chui.controller.validator.regexp.UserRegExp;
import com.oleh.chui.model.dto.UserDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.oleh.chui.controller.validator.restriction.UserRestriction.*;

public class UserValidator {

    private UserValidator() {}

    public static void validate(UserDto userDto) throws
            UsernameSizeOutOfBoundsException,
            PasswordSizeOutOfBoundsException,
            PasswordNotMatchTemplateException,
            PasswordsNotMatchException,
            FirstNameSizeOutOfBoundsException,
            LastNameSizeOutOfBoundsException,
            EmailSizeOutOfBoundsException,
            EmailNotMatchTemplateException {

        checkForUsernameSize(userDto.getUsername());
        checkForPasswordSize(userDto.getPassword());
        checkForPasswordMatchTemplate(userDto.getPassword());
        checkForPasswordsMatching(userDto.getPassword(), userDto.getPasswordCopy());
        checkForFirstNameSize(userDto.getFirstName());
        checkForLastNameSize(userDto.getLastName());
        checkForEmailSize(userDto.getEmail());
        checkForEmailMatchingTemplate(userDto.getEmail());
    }

    private static void checkForUsernameSize(String username) throws UsernameSizeOutOfBoundsException {
        if (isEmpty(username)) throw new UsernameSizeOutOfBoundsException();

        final int SIZE = username.length();

        if (SIZE < USERNAME_MIN_SIZE || SIZE > USERNAME_MAX_SIZE) {
            throw new UsernameSizeOutOfBoundsException();
        }
    }

    private static void checkForPasswordSize(String password) throws PasswordSizeOutOfBoundsException {
        if (isEmpty(password)) throw new PasswordSizeOutOfBoundsException();

        final int SIZE = password.length();

        if (SIZE < PASSWORD_MIN_SIZE || SIZE > PASSWORD_MAX_SIZE) {
            throw new PasswordSizeOutOfBoundsException();
        }
    }

    private static void checkForPasswordMatchTemplate(String password) throws PasswordNotMatchTemplateException {
        Pattern pattern = Pattern.compile(UserRegExp.PASSWORD);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            throw new PasswordNotMatchTemplateException();
        }
    }

    private static void checkForPasswordsMatching(String password, String passwordCopy) throws PasswordsNotMatchException {
        if (!password.equals(passwordCopy)) {
            throw new PasswordsNotMatchException();
        }
    }

    private static void checkForFirstNameSize(String firstName) throws FirstNameSizeOutOfBoundsException {
        if (isEmpty(firstName)) throw new FirstNameSizeOutOfBoundsException();

        final int SIZE = firstName.length();

        if (SIZE > FIRST_NAME_MAX_SIZE) {
            throw new FirstNameSizeOutOfBoundsException();
        }
    }

    private static void checkForLastNameSize(String lastName) throws LastNameSizeOutOfBoundsException {
        if (isEmpty(lastName)) throw new LastNameSizeOutOfBoundsException();

        final int SIZE = lastName.length();

        if (SIZE > LAST_NAME_MAX_SIZE) {
            throw new LastNameSizeOutOfBoundsException();
        }
    }

    private static void checkForEmailSize(String email) throws EmailSizeOutOfBoundsException {
        if (isEmpty(email)) throw new EmailSizeOutOfBoundsException();

        final int SIZE = email.length();

        if (SIZE > EMAIL_MAX_SIZE) {
            throw new EmailSizeOutOfBoundsException();
        }
    }

    private static void checkForEmailMatchingTemplate(String email) throws EmailNotMatchTemplateException {
        Pattern pattern = Pattern.compile(UserRegExp.EMAIL);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new EmailNotMatchTemplateException();
        }
    }

    private static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

}
