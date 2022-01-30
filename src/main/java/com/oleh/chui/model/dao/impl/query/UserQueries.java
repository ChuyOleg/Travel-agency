package com.oleh.chui.model.dao.impl.query;

public class UserQueries {

    // TODO: delete FIND_ROLE_BY_ROLE_ID and use Join for find methods

    private UserQueries() {}

    public static final String CREATE = "";

    public static final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";

    public static final String FIND_ALL = "SELECT * FROM users";

    public static final String UPDATE = "";

    public static final String DELETE = "";

    public static final String FIND_ROLE_BY_ROLE_ID = "SELECT * FROM roles WHERE id = ?";

}
