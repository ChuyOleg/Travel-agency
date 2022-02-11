package com.oleh.chui.model.dao;

import com.oleh.chui.model.entity.Country;

import java.util.Optional;

public interface CountryDao {

    Optional<Country> findByName(String country);

}
