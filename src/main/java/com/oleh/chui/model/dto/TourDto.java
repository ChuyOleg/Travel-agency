package com.oleh.chui.model.dto;


import java.util.Objects;

public class TourDto {

    private String name;
    private String price;
    private String country;
    private String city;
    private String description;
    private String maxDiscount;
    private String discountStep;
    private String tourType;
    private String hotelType;
    private String personNumber;
    private String startDate;
    private String endDate;
    private String burning;

    public TourDto() {}

    public TourDto(String name,
                   String price,
                   String country,
                   String city,
                   String description,
                   String maxDiscount,
                   String discountStep,
                   String tourType,
                   String hotelType,
                   String personNumber,
                   String startDate,
                   String endDate,
                   String burning) {

        this.name = name;
        this.price = price;
        this.country = country;
        this.city = city;
        this.description = description;
        this.maxDiscount = maxDiscount;
        this.discountStep = discountStep;
        this.tourType = tourType;
        this.hotelType = hotelType;
        this.personNumber = personNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.burning = burning;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(String maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public String getDiscountStep() {
        return discountStep;
    }

    public void setDiscountStep(String discountStep) {
        this.discountStep = discountStep;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String isBurning() {
        return burning;
    }

    public void setBurning(String burning) {
        this.burning = burning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourDto tourDto = (TourDto) o;
        return Objects.equals(name, tourDto.name) && Objects.equals(price, tourDto.price) && Objects.equals(country, tourDto.country) && Objects.equals(city, tourDto.city) && Objects.equals(description, tourDto.description) && Objects.equals(maxDiscount, tourDto.maxDiscount) && Objects.equals(discountStep, tourDto.discountStep) && Objects.equals(tourType, tourDto.tourType) && Objects.equals(hotelType, tourDto.hotelType) && Objects.equals(personNumber, tourDto.personNumber) && Objects.equals(startDate, tourDto.startDate) && Objects.equals(endDate, tourDto.endDate) && Objects.equals(burning, tourDto.burning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, country, city, description, maxDiscount, discountStep, tourType, hotelType, personNumber, startDate, endDate, burning);
    }

    @Override
    public String toString() {
        return "TourDto{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", maxDiscount='" + maxDiscount + '\'' +
                ", discountStep='" + discountStep + '\'' +
                ", tourType='" + tourType + '\'' +
                ", hotelType='" + hotelType + '\'' +
                ", personNumber='" + personNumber + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", burning='" + burning + '\'' +
                '}';
    }
}
