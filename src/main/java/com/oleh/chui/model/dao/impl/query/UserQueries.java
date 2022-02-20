package com.oleh.chui.model.dao.impl.query;

public class UserQueries {

    private UserQueries() {}

    public static final String CREATE =
            "INSERT INTO users" +
            "(username, password, first_name, last_name, email, role_id)" +
            " VALUES(?, ?, ?, ?, ?, (SELECT role_id FROM roles WHERE role = ?))";

    public static final String FIND_ALL =
            "SELECT * FROM users u" +
            " JOIN roles USING (role_id)";

    public static final String FIND_BY_ID =
            FIND_ALL + " WHERE u.user_id = ?";

    public static final String FIND_BY_USERNAME =
            FIND_ALL + " WHERE username = ?";

    public static final String FIND_BY_USERNAME_AND_PASSWORD =
            FIND_ALL + " WHERE username = ? AND password = ?";

    public static final String FIND_ALL_USERS =
            FIND_ALL +
            " WHERE role = 'USER'" +
            " ORDER BY username";

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

}
