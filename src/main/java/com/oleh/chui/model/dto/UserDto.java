package com.oleh.chui.model.dto;

import java.util.Objects;

public class UserDto {

    private String username;
    private String password;
    private String passwordCopy;
    private String firstName;
    private String lastName;
    private String email;

    public UserDto() {}

    public UserDto(String username, String password, String passwordCopy, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.passwordCopy = passwordCopy;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordCopy() {
        return passwordCopy;
    }

    public void setPasswordCopy(String passwordCopy) {
        this.passwordCopy = passwordCopy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(username, userDto.username)
                && Objects.equals(password, userDto.password)
                && Objects.equals(passwordCopy, userDto.passwordCopy)
                && Objects.equals(firstName, userDto.firstName)
                && Objects.equals(lastName, userDto.lastName)
                && Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, passwordCopy, firstName, lastName, email);
    }
}
