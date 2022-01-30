package com.oleh.chui.model.entity;


import java.math.BigDecimal;
import java.util.Objects;

public class User {

    private Long id;
    private String username;
    private char[] password;
    private String firstName;
    private String lastName;
    private String email;
    // TODO: Specify save money as just BigDecimal or BigDecimal(*, 2)
    private BigDecimal money;
    private Role role;
    private boolean blocked;

    public User() {

    }

    // TODO: think about appropriation of password
    // Constructor for DAO
    public User(Long id,
                String username,
//                char[] password,
                String firstName,
                String lastName,
                String email,
                BigDecimal money,
                Role role,
                boolean blocked) {

        this.id = id;
        this.username = username;
//        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.money = money;
        this.role = role;
        this.blocked = blocked;
    }

    // Constructor for DTO
    public User(String username,
                char[] password,
                String firstName,
                String lastName,
                String email) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = Role.USER;
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

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
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
                "username='" + username + '\'' +
                '}';
    }
}
