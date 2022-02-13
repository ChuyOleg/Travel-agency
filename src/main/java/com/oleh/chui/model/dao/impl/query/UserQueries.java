package com.oleh.chui.model.dao.impl.query;

public class UserQueries {

    private UserQueries() {}

    public static final String CREATE =
            "INSERT INTO users" +
            "(username, password, first_name, last_name, email, role_id)" +
            " VALUES(?, ?, ?, ?, ?, (SELECT role_id FROM roles WHERE role = ?))";

    public static final String FIND_BY_ID =
            "SELECT * FROM users u" +
            " JOIN roles USING (role_id)" +
            " WHERE u.user_id = ?";

    public static final String FIND_ALL =
            "SELECT * FROM users u" +
            " JOIN roles USING (role_id)";

    public static final String FIND_ALL_USERS =
            "SELECT * FROM users u" +
            " JOIN roles USING (role_id)" +
            " WHERE role = 'USER'";

    // TODO: THINK WHAT FIELDS HAVE TO BE CHANGED
    public static final String UPDATE =
            "UPDATE users SET" +
            " first_name = ?," +
            " last_name = ?," +
            " email = ?" +
            " WHERE user_id = ?";

    public static final String BLOCK_BY_ID =
            "UPDATE users" +
            " SET is_blocked = true" +
            " WHERE user_id = ?";

    public static final String UNBLOCK_BY_ID =
            "UPDATE users" +
            " SET is_blocked = false" +
            " WHERE user_id = ?";

    public static final String DELETE =
            "DELETE FROM users WHERE user_id = ?";

    public static final String FIND_BY_USERNAME =
            "SELECT * FROM users WHERE username = ?";

    public static final String FIND_BY_USERNAME_AND_PASSWORD =
            "SELECT * FROM users" +
            " JOIN roles USING (role_id)" +
            " WHERE username = ? AND password = ?";

}
