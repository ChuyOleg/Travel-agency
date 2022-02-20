package com.oleh.chui.controller.util;

public class UriPath {

    private UriPath() {}

    public static final String USER_PREFIX = "/User";
    public static final String MANAGER_PREFIX = "/Manager";
    public static final String ADMIN_PREFIX = "/Admin";
    public static final String REDIRECT = "redirect:";

    public static final String LOGIN = "/login";
    public static final String REGISTRATION = "/registration";
    public static final String CATALOG = "/catalog";
    public static final String TOUR_DETAILS = "/tour/details";

    public static final String LOGOUT = "/logout";

    public static final String USER_ACCOUNT = USER_PREFIX + "/account";
    public static final String USER_BUY_TOUR = USER_PREFIX + "/tour/buy";

    public static final String MANAGER_USERS = MANAGER_PREFIX + "/users";
    public static final String MANAGER_USER_PAGE = MANAGER_PREFIX + "/user";
    public static final String MANAGER_CHANGE_ORDER_STATUS = MANAGER_PREFIX + "/order/changeStatus";
    public static final String MANAGER_CHANGE_TOUR_BURNING_STATE = MANAGER_PREFIX + "/tour/changeBurningState";
    public static final String MANAGER_CHANGE_DISCOUNT = MANAGER_PREFIX + "/tour/changeDiscount";

    public static final String ADMIN_CREATE_TOUR = ADMIN_PREFIX + "/tour/create";
    public static final String ADMIN_UPDATE_TOUR = ADMIN_PREFIX + "/tour/update";
    public static final String ADMIN_DELETE_TOUR = ADMIN_PREFIX + "/tour/delete";

    public static final String STATIC_RESOURCES_PREFIX = "/static";

}
