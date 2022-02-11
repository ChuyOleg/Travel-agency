package com.oleh.chui.model.dao.impl.query;

public class CountryQueries {

    private CountryQueries() {}

    public static final String FIND_BY_NAME =
            "SELECT * FROM countries" +
            " JOIN cities USING (country_id)" +
            " WHERE country = ?";

}
