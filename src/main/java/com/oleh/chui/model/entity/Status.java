package com.oleh.chui.model.entity;

import java.util.Objects;

public class Status {

    private Long id;
    private StatusEnum value;

    public Status() {}

    public Status(StatusEnum value) {
        this.value = value;
    }

    public Status(Long id, StatusEnum value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getValue() {
        return value;
    }

    public void setValue(StatusEnum value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return value == status.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Status{" +
                "value=" + value +
                '}';
    }

    public enum StatusEnum {
        REGISTERED,
        PAID,
        CANCELED
    }

}
