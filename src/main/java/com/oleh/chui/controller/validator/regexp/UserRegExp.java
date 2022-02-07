package com.oleh.chui.controller.validator.regexp;

public class UserRegExp {

    private UserRegExp() {}

    public static final String PASSWORD = ".+@.+\\..+";
    public static final String EMAIL = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])$";

}
