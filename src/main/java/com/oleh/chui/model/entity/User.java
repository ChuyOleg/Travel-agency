package com.oleh.chui.model.entity;


import com.oleh.chui.model.dto.UserDto;

import java.math.BigDecimal;
import java.util.Objects;

public class User {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal money;
    private Role role;
    private boolean blocked;

    public User() {

    }

    // TODO: think about appropriation of password
    // Constructor for DAO
    public User(Long id,
                String username,
                String password,
                String firstName,
                String lastName,
                String email,
                BigDecimal money,
                Role role,
                boolean blocked) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.money = money;
        this.role = role;
        this.blocked = blocked;
    }

    public User(UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.role = new Role(Role.RoleEnum.USER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private BigDecimal money;
        private Role role;
        private boolean blocked;

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder money(BigDecimal money) {
            this.money = money;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder blocked(boolean blocked) {
            this.blocked = blocked;
            return this;
        }

        public User build() {
            return new User(
                    this.id,
                    this.username,
                    this.password,
                    this.firstName,
                    this.lastName,
                    this.email,
                    this.money,
                    this.role,
                    this.blocked
            );
        }

        public UserBuilder() {}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", money=" + money +
                ", role=" + role +
                ", blocked=" + blocked +
                '}';
    }
}
