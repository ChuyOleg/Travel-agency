package com.oleh.chui.controller.validator;

import com.oleh.chui.controller.exception.user.*;
import com.oleh.chui.controller.validator.regexp.UserRegExp;
import com.oleh.chui.controller.validator.util.FieldValidator;
import com.oleh.chui.model.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.oleh.chui.controller.validator.alias.UserExceptionNamesForRequest.*;
import static com.oleh.chui.controller.validator.restriction.UserRestriction.*;

/**
 * Validate UserDto
 *
 * @author Oleh Chui
 */
public class UserValidator {

    private static final Logger logger = LogManager.getLogger(UserValidator.class);

    private UserValidator() {}

    /**
     * Validate all fields of UserDto and process exceptions
     *
     * @param userDto An instance of UserDto class that should be validated
     * @param request An instance of HttpServletRequest
     * @return A boolean representing is UserDto valid or not
     */
    public static boolean validate(UserDto userDto, HttpServletRequest request) {
        try {
            checkForUsernameSize(userDto.getUsername());
            checkForFirstNameSize(userDto.getFirstName());
            checkForLastNameSize(userDto.getLastName());
            checkForEmailSize(userDto.getEmail());
            checkForEmailMatchingTemplate(userDto.getEmail());
            checkForPasswordSize(userDto.getPassword());
            checkForPasswordMatchTemplate(userDto.getPassword());
            checkForPasswordsMatching(userDto.getPassword(), userDto.getPasswordCopy());
            return true;
        } catch (UsernameSizeOutOfBoundsException e) {
            logger.warn("Username size out of bounds ({})", userDto.getUsername());
            request.setAttribute(USERNAME_SIZE_OUT_OF_BOUNDS, true);
        } catch (FirstNameSizeOutOfBoundsException e) {
            logger.warn("First name size out of bounds ({})", userDto.getFirstName());
            request.setAttribute(FIRST_NAME_SIZE_OUT_OF_BOUNDS, true);
        } catch (LastNameSizeOutOfBoundsException e) {
            logger.warn("Last name size out of bounds ({})", userDto.getLastName());
            request.setAttribute(LAST_NAME_SIZE_OUT_OF_BOUNDS, true);
        } catch (EmailSizeOutOfBoundsException e) {
            logger.warn("Email size out of bounds ({})", userDto.getEmail());
            request.setAttribute(EMAIL_SIZE_OUT_OF_BOUNDS, true);
        } catch (EmailNotMatchTemplateException e) {
            logger.warn("Email not match template ({})", userDto.getEmail());
            request.setAttribute(EMAIL_NOT_MATCH_TEMPLATE, true);
        } catch (PasswordSizeOutOfBoundsException e) {
            logger.warn("Password size out of bounds ({})", userDto.getPassword());
            request.setAttribute(PASSWORD_SIZE_OUT_OF_BOUNDS, true);
        } catch (PasswordNotMatchTemplateException e) {
            logger.warn("Password not match template ({})", userDto.getPassword());
            request.setAttribute(PASSWORD_NOT_MATCH_TEMPLATE, true);
        } catch (PasswordsNotMatchException e) {
            logger.warn("Passwords not match");
            request.setAttribute(PASSWORDS_NOT_MATCH, true);
        }

        return false;
    }

    private static void checkForUsernameSize(String username) throws UsernameSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(username)) throw new UsernameSizeOutOfBoundsException();

        final int SIZE = username.length();

        if (SIZE < USERNAME_MIN_SIZE || SIZE > USERNAME_MAX_SIZE) {
            throw new UsernameSizeOutOfBoundsException();
        }
    }

    private static void checkForPasswordSize(String password) throws PasswordSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(password)) throw new PasswordSizeOutOfBoundsException();

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
        if (FieldValidator.fieldIsEmpty(firstName)) throw new FirstNameSizeOutOfBoundsException();

        final int SIZE = firstName.length();

        if (SIZE > FIRST_NAME_MAX_SIZE) {
            throw new FirstNameSizeOutOfBoundsException();
        }
    }

    private static void checkForLastNameSize(String lastName) throws LastNameSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(lastName)) throw new LastNameSizeOutOfBoundsException();

        final int SIZE = lastName.length();

        if (SIZE > LAST_NAME_MAX_SIZE) {
            throw new LastNameSizeOutOfBoundsException();
        }
    }

    private static void checkForEmailSize(String email) throws EmailSizeOutOfBoundsException {
        if (FieldValidator.fieldIsEmpty(email)) throw new EmailSizeOutOfBoundsException();

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

}
