package com.oleh.chui.model.dao.impl.query;

public class OrderQueries {

    private OrderQueries() {}

    public static final String CREATE =
            "INSERT INTO orders" +
            "(user_id, tour_id, status_id, creation_date, final_price)" +
            " VALUES (?, ?, (SELECT status_id FROM status WHERE status = ?), ?, ?)";

    public static final String FIND_BY_ID =
            "SELECT * FROM orders" +
            " JOIN status USING (status_id)" +
            " JOIN (SELECT * FROM tours" +
                " JOIN hotel_types USING (hotel_type_id)" +
                " JOIN tour_types USING (tour_type_id)" +
                " JOIN cities USING (city_id)" +
                " JOIN countries USING (country_id)) as tour_info USING (tour_id)" +
            " JOIN (SELECT * FROM users JOIN roles USING (role_id)) as user_info USING (user_id)" +
            " WHERE order_id = ?";

    public static final String FIND_BY_USER_ID_COUNT =
            "SELECT count(*) FROM orders WHERE user_id = ?";

    public static final String FIND_ALL =
            "SELECT * FROM orders" +
            " JOIN status USING (status_id)" +
            " JOIN (SELECT * FROM tours" +
                " JOIN hotel_types USING (hotel_type_id)" +
                " JOIN tour_types USING (tour_type_id)" +
                " JOIN cities USING (city_id)" +
                " JOIN countries USING (country_id)) as tour_info USING (tour_id)" +
            " JOIN (SELECT * FROM users JOIN roles USING (role_id)) as user_info USING (user_id)";

    public static final String IS_EXISTED_BY_USER_ID_AND_TOUR_ID =
            "SELECT count(*) FROM orders WHERE user_id = ? AND tour_id = ?";

    public static final String IS_EXISTED_BY_TOUR_ID =
            "SELECT count(*) FROM orders WHERE tour_id = ?";

    // TODO: Update object in java When update object in DB !!! THINK
    public static final String UPDATE = "";

    public static final String DELETE = "DELETE FROM orders WHERE order_id = ?";

}
