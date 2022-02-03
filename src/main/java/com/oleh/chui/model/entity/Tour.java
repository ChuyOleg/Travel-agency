package com.oleh.chui.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Tour {

    private Long id;
    private String name;
    private BigDecimal price;
    private City city;
    private String description;
    private int maxDiscount;
    private double discountStep;
    private TourType tourType;
    private HotelType hotelType;
    private int personNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private int nightsNumber;
    private boolean burning;

    public Tour() {}

    public Tour(Long id,
                String name,
                BigDecimal price,
                City city,
                String description,
                int maxDiscount,
                double discountStep,
                TourType tourType,
                HotelType hotelType,
                int personNumber,
                LocalDate startDate,
                LocalDate endDate,
                int nightsNumber,
                boolean burning) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.city = city;
        this.description = description;
        this.maxDiscount = maxDiscount;
        this.discountStep = discountStep;
        this.tourType = tourType;
        this.hotelType = hotelType;
        this.personNumber = personNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nightsNumber = nightsNumber;
        this.burning = burning;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
        return burning;
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public static TourBuilder builder() {
        return new TourBuilder();
    }

    public static class TourBuilder {
        private Long id;
        private String name;
        private BigDecimal price;
        private City city;
        private String description;
        private int maxDiscount;
        private double discountStep;
        private TourType tourType;
        private HotelType hotelType;
        private int personNumber;
        private LocalDate startDate;
        private LocalDate endDate;
        private int nightsNumber;
        private boolean burning;

        TourBuilder() {}

        public TourBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TourBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TourBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public TourBuilder city(City city) {
            this.city = city;
            return this;
        }

        public TourBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TourBuilder maxDiscount(int maxDiscount) {
            this.maxDiscount = maxDiscount;
            return this;
        }

        public TourBuilder discountStep(double discountStep) {
            this.discountStep = discountStep;
            return this;
        }

        public TourBuilder tourType(TourType tourType) {
            this.tourType = tourType;
            return this;
        }

        public TourBuilder hotelType(HotelType hotelType) {
            this.hotelType = hotelType;
            return this;
        }

        public TourBuilder personNumber(int personNumber) {
            this.personNumber = personNumber;
            return this;
        }

        public TourBuilder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public TourBuilder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public TourBuilder nightsNumber(int nightsNumber) {
            this.nightsNumber = nightsNumber;
            return this;
        }

        public TourBuilder burning(boolean burning) {
            this.burning = burning;
            return this;
        }

        public Tour build() {
            return new Tour(
                    this.id,
                    this.name,
                    this.price,
                    this.city,
                    this.description,
                    this.maxDiscount,
                    this.discountStep,
                    this.tourType,
                    this.hotelType,
                    this.personNumber,
                    this.startDate,
                    this.endDate,
                    this.nightsNumber,
                    this.burning
            );
        }

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

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", city=" + city +
                ", description='" + description + '\'' +
                ", maxDiscount=" + maxDiscount +
                ", discountStep=" + discountStep +
                ", tourType=" + tourType +
                ", hotelType=" + hotelType +
                ", personNumber=" + personNumber +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", nightsNumber=" + nightsNumber +
                ", burning=" + burning +
                '}';
    }
}
