package com.oleh.chui.model.entity;

import java.util.Objects;

public class HotelType {

    private Long id;
    private HotelTypeEnum value;

    public HotelType() {}

    public HotelType(HotelTypeEnum value) {
        this.value = value;
    }

    public HotelType(Long id, HotelTypeEnum value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HotelTypeEnum getValue() {
        return value;
    }

    public void setValue(HotelTypeEnum value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelType hotelType = (HotelType) o;
        return value == hotelType.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "HotelType{" +
                "value=" + value +
                '}';
    }

    public enum HotelTypeEnum {
        ONE_STAR(1),
        TWO_STARS(2),
        THREE_STARS(3),
        FOUR_STARS(4),
        FIVE_STARS(5);

        private final int starNumber;

        HotelTypeEnum(int starNumber) {
            this.starNumber = starNumber;
        }

        public int getStarNumber() {
            return starNumber;
        }
    }

}
