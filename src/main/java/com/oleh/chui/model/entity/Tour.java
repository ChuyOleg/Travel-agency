package com.oleh.chui.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Tour {

    private Long id;
    private String name;
    private BigDecimal price;
    private Country country;
    private String description;
    private int maxDiscount;
    private double discountStep;
    private TourType tourType;
    private HotelType hotelType;
    private int personNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private int nightsNumber;
    private boolean isBurning;

    public Tour() {}

    public Tour(Long id,
                String name,
                BigDecimal price,
                Country country,
                String description,
                int maxDiscount,
                double discountStep,
                TourType tourType,
                HotelType hotelType,
                int personNumber,
                LocalDate startDate,
                LocalDate endDate,
                int nightsNumber,
                boolean isBurning) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.country = country;
        this.description = description;
        this.maxDiscount = maxDiscount;
        this.discountStep = discountStep;
        this.tourType = tourType;
        this.hotelType = hotelType;
        this.personNumber = personNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nightsNumber = nightsNumber;
        this.isBurning = isBurning;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public double getDiscountStep() {
        return discountStep;
    }

    public void setDiscountStep(double discountStep) {
        this.discountStep = discountStep;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNightsNumber() {
        return nightsNumber;
    }

    public void setNightsNumber(int nightsNumber) {
        this.nightsNumber = nightsNumber;
    }

    public boolean isBurning() {
        return isBurning;
    }

    public void setBurning(boolean burning) {
        isBurning = burning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return name.equals(tour.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
