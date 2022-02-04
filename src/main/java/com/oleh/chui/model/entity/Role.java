package com.oleh.chui.model.entity;

import java.util.Objects;

public class Role {

    private Long id;
    private RoleEnum value;

    public Role() {}

    public Role(RoleEnum value) {
        this.value = value;
    }

    public Role(Long id, RoleEnum value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getValue() {
        return value;
    }

    public void setValue(RoleEnum value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return value == role.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Role{" +
                "value=" + value +
                '}';
    }

    public enum RoleEnum {
        UNKNOWN,
        USER,
        MANAGER,
        ADMIN
    }

}
