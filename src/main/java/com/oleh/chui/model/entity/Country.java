package com.oleh.chui.model.entity;

import java.util.Objects;
import java.util.Set;

public class Country {

    private Long id;
    private String country;
    private Set<City> cityList;

    public Country() {}

    public Country(String country, Set<City> cityList) {
        this.country = country;
        this.cityList = cityList;
    }

    public Country(Long id, String country, Set<City> cityList) {
        this.id = id;
        this.country = country;
        this.cityList = cityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<City> getCityList() {
        return cityList;
    }

    public void setCityList(Set<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country1 = (Country) o;
        return country.equals(country1.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country);
    }
}
