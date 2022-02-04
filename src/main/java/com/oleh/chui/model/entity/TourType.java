package com.oleh.chui.model.entity;

import java.util.Objects;

public class TourType {

    private Long id;
    private TourTypeEnum value;

    public TourType() {}

    public TourType(TourTypeEnum value) {
        this.value = value;
    }

    public TourType(Long id, TourTypeEnum value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TourTypeEnum getValue() {
        return value;
    }

    public void setValue(TourTypeEnum value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourType tourType = (TourType) o;
        return value == tourType.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "TourType{" +
                "value=" + value +
                '}';
    }

    public enum TourTypeEnum {
        VACATION,
        EXCURSION,
        SHOPPING
    }

}
