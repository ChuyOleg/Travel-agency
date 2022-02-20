package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.OrderDao;
import com.oleh.chui.model.entity.Order;
import com.oleh.chui.model.entity.Status;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private final Logger logger = LogManager.getLogger(OrderService.class);
    private final UserService userService;
    private final TourService tourService;
    private final OrderDao orderDao;

    public OrderService(UserService userService, TourService tourService, OrderDao orderDao) {
        this.userService = userService;
        this.tourService = tourService;
        this.orderDao = orderDao;
    }

    public boolean isExistedByUserIdAndTourId(Long userId, Long tourId) {
        return orderDao.isExistedByUserIdAndTourId(userId, tourId);
    }

    public List<Order> findAllByUserId(Long userId) {
        return orderDao.findAllByUserId(userId);
    }

    public boolean isExistedByTourId(Long tourId) {
        return orderDao.isExistedByTourId(tourId);
    }

    public void changeStatus(String newStatus, Long orderId) {
        Status.StatusEnum statusEnum = Status.StatusEnum.valueOf(newStatus);

        orderDao.changeStatus(statusEnum, orderId);
    }

    public void createOrder(Long userId, Long tourId) {
        Optional<User> userOptional = userService.findById(userId);
        Optional<Tour> tourOptional = tourService.findById(tourId);

        if (userOptional.isPresent() && tourOptional.isPresent()) {
            BigDecimal finalPrice = calculateFinalPrice(userId, tourOptional.get());
            Status status = new Status(Status.StatusEnum.REGISTERED);

            Order order = Order.builder()
                    .user(userOptional.get())
                    .tour(tourOptional.get())
                    .status(status)
                    .creationDate(LocalDate.now())
                    .finalPrice(finalPrice)
                    .build();

            orderDao.create(order);
        }
    }

    public BigDecimal calculateFinalPrice(Long userId, Tour tour) {
        final int MAX_PERCENTAGE = 100;
        int ordersCount = findByUserIdCount(userId);

        double totalDiscount = tour.getDiscountStep() * ordersCount;

        double finalDiscount = (totalDiscount > tour.getMaxDiscount())
                ? tour.getMaxDiscount()
                : totalDiscount;

        double FINAL_PRICE_IN_PERCENTAGE = MAX_PERCENTAGE - finalDiscount;

        return tour.getPrice().multiply(BigDecimal.valueOf(FINAL_PRICE_IN_PERCENTAGE / MAX_PERCENTAGE))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private int findByUserIdCount(Long userId) {
        return orderDao.findByUserIdCount(userId);
    }

}
