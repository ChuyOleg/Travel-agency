package com.oleh.chui.model.dao.impl.query;

public class UserQueries {

    private UserQueries() {}

    public static final String CREATE =
            "INSERT INTO users" +
            "(username, password, first_name, last_name, email, money, role_id, is_blocked)" +
            " VALUES(?, ?, ?, ?, ?, ?, (SELECT role_id FROM roles WHERE role = ?), ?)";

    public static final String FIND_BY_ID =
            "SELECT * FROM users u" +
            " JOIN roles r" +
            " ON u.role_id = r.role_id" +
            " WHERE u.user_id = ?";

    public static final String FIND_ALL =
            "SELECT * FROM users u" +
            " JOIN roles r ON u.role_id = r.role_id";

    public static final String UPDATE =
            "UPDATE users SET" +
            " first_name = ?," +
            " last_name = ?," +
            " email = ?" +
            " WHERE user_id = ?";

    public static final String DELETE =
            "DELETE FROM users WHERE user_id = ?";

}
