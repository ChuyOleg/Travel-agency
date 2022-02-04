package com.oleh.chui.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Order {

    private Long id;
    private User user;
    private Tour tour;
    private Status status;
    private LocalDate creationDate;
    private BigDecimal finalPrice;

    public Order() {}

    public Order(Long id,
                 User user,
                 Tour tour,
                 Status status,
                 LocalDate creationDate,
                 BigDecimal finalPrice) {

        this.id = id;
        this.user = user;
        this.tour = tour;
        this.status = status;
        this.creationDate = creationDate;
        this.finalPrice = finalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private Long id;
        private User user;
        private Tour tour;
        private Status status;
        private LocalDate creationDate;
        private BigDecimal finalPrice;

        public OrderBuilder() {}

        public OrderBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder user(User user) {
            this.user = user;
            return this;
        }

        public OrderBuilder tour(Tour tour) {
            this.tour = tour;
            return this;
        }

        public OrderBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public OrderBuilder creationDate(LocalDate creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public OrderBuilder finalPrice(BigDecimal finalPrice) {
            this.finalPrice = finalPrice;
            return this;
        }

        public Order build() {
            return new Order(
                    this.id,
                    this.user,
                    this.tour,
                    this.status,
                    this.creationDate,
                    this.finalPrice
            );
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", tour=" + tour +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", finalPrice=" + finalPrice +
                '}';
    }
}
