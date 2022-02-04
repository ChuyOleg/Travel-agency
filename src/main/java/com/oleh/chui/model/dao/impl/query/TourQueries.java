package com.oleh.chui.model.dao.impl.query;

public class TourQueries {

    private TourQueries() {}

    public static final String CREATE = "" +
            "INSERT INTO tours" +
            " (name, price, city_id, description, max_discount, discount_step, tour_type_id, hotel_type_id," +
            " person_number, start_date, end_date, nights_number, is_burning)" +
            " VALUES(?, ?, (SELECT city_id FROM cities JOIN countries c ON c.country_id = cities.country_id" +
                            " WHERE city = ? AND country = ?)," +
            " ?, ?, ?, (SELECT tour_type_id FROM tour_types WHERE type = ?)," +
            " (SELECT hotel_type_id FROM hotel_types WHERE type = ?), ?, ?, ?, ?, ?)";

    public static final String FIND_BY_ID =
            "SELECT tour_id, name, price, description, max_discount," +
            " discount_step, person_number, start_date, end_date, nights_number," +
            " is_burning, ht.hotel_type_id, ht.type as hotel_type, tt.tour_type_id," +
            " tt.type as tour_type, c.city_id, city, co.country_id, country FROM tours" +
            " JOIN hotel_types ht ON ht.hotel_type_id = tours.hotel_type_id" +
            " JOIN tour_types tt ON tt.tour_type_id = tours.tour_type_id" +
            " JOIN cities c ON c.city_id = tours.city_id" +
            " JOIN countries co ON co.country_id = c.country_id" +
            " WHERE tour_id = ?";

    public static final String FIND_ALL =
            "SELECT tour_id, name, price, description, max_discount," +
            " discount_step, person_number, start_date, end_date, nights_number," +
            " is_burning, ht.hotel_type_id, ht.type as hotel_type, tt.tour_type_id," +
            " tt.type as tour_type, c.city_id, city, co.country_id, country FROM tours" +
            " JOIN hotel_types ht ON ht.hotel_type_id = tours.hotel_type_id" +
            " JOIN tour_types tt ON tt.tour_type_id = tours.tour_type_id" +
            " JOIN cities c ON c.city_id = tours.city_id" +
            " JOIN countries co ON co.country_id = c.country_id";

    // TODO: THINK WHAT FIELDS HAVE TO BE CHANGED
    public static final String UPDATE =
            "UPDATE tours SET" +
            " name = ?," +
            " price = ?" +
            " WHERE tour_id = ?";

    public static final String DELETE = "DELETE FROM tours WHERE tour_id = ?";

}
