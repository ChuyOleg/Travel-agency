package com.oleh.chui.controller.util;

public class JspFilePath {

    private JspFilePath() {}

    private static final String COMMON_DIR = "/template/common/";
    private static final String GUEST_DIR = "/template/guest/";
    private static final String USER_DIR = "/template/user/";
    private static final String MANAGER_DIR = "/template/manager/";
    private static final String ADMIN_DIR = "/template/admin/";

    public static final String CATALOG = COMMON_DIR + "catalogPage.jsp";
    public static final String TOUR_DETAILS = COMMON_DIR + "tourDetailsPage.jsp";

    public static final String LOGIN = GUEST_DIR + "logInPage.jsp";
    public static final String REGISTRATION = GUEST_DIR + "registrationPage.jsp";

    public static final String USER_ACCOUNT = USER_DIR + "accountPage.jsp";

    public static final String MANAGER_USERS = MANAGER_DIR + "usersPage.jsp";
    public static final String MANAGER_USER_PAGE = MANAGER_DIR + "userAccountPage.jsp";
    public static final String MANAGER_CHANGE_DISCOUNT = MANAGER_DIR +  "changeDiscountPage.jsp";

    public static final String ADMIN_CREATE_TOUR = ADMIN_DIR + "createTourPage.jsp";
    public static final String ADMIN_UPDATE_TOUR = ADMIN_DIR + "updateTourPage.jsp";

}
