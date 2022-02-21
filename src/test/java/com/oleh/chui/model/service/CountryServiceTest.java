package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.CountryDao;
import com.oleh.chui.model.entity.City;
import com.oleh.chui.model.entity.Country;
import com.oleh.chui.model.exception.city.CityNotExistException;
import com.oleh.chui.model.exception.country.CountryNotExistException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CountryServiceTest {

    CountryDao countryDao = mock(CountryDao.class);
    CountryService countryService = new CountryService(countryDao);

    private static final String COUNTRY_STRING = "Vacanda";
    private static final String CITY_KALIV_STRING = "Kaliv";
    private static final String CITY_ORLEAN_STRING = "Orlean";
    private static final String CITY_DEKOR_STRING = "Dekor";

    private static final City CITY_KALIV = new City(CITY_KALIV_STRING, null);
    private static final City CITY_ORLEAN = new City(CITY_ORLEAN_STRING, null);
    private static final City CITY_DEKOR = new City(CITY_DEKOR_STRING, null);

    private static final Set<City> CITY_LIST_NOT_CONTAIN_KALIV = new HashSet<>(Arrays.asList(CITY_ORLEAN, CITY_DEKOR));
    private static final Set<City> CITY_LIST_CONTAIN_KALIV = new HashSet<>(Arrays.asList(CITY_ORLEAN, CITY_KALIV));

    @Test
    void testCheckCountryAndCityExistShouldNotThrowException() {
        Country country = new Country(1L, COUNTRY_STRING, CITY_LIST_CONTAIN_KALIV);
        when(countryDao.findByName(COUNTRY_STRING)).thenReturn(Optional.of(country));

        assertDoesNotThrow(() -> countryService.checkCountryAndCityExist(COUNTRY_STRING, CITY_KALIV_STRING));
    }

    @Test
    void testCheckCountryAndCityExistShouldThrowCountryNotExistException() {
        when(countryDao.findByName(COUNTRY_STRING)).thenReturn(Optional.empty());

        assertThrows(
                CountryNotExistException.class,
                () -> countryService.checkCountryAndCityExist(COUNTRY_STRING, CITY_KALIV_STRING)
        );
    }

    @Test
    void testCheckCountryAndCityExistShouldThrowCityNotExistException() {
        Country country = new Country(1L, COUNTRY_STRING, CITY_LIST_NOT_CONTAIN_KALIV);
        when(countryDao.findByName(COUNTRY_STRING)).thenReturn(Optional.of(country));

        assertThrows(
                CityNotExistException.class,
                () -> countryService.checkCountryAndCityExist(COUNTRY_STRING, CITY_KALIV_STRING)
        );
    }
}