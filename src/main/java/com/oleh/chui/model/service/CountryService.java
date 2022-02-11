package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.CountryDao;
import com.oleh.chui.model.entity.Country;

import java.util.Optional;

public class CountryService {

    private final CountryDao countryDao;

    public CountryService(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public Optional<Country> findByName(String countryName) {
        return countryDao.findByName(countryName);
    }
}
