package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.OrderDao;
import com.oleh.chui.model.entity.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderService {

    private final Logger logger = LogManager.getLogger(OrderService.class);
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public boolean isExistedByUserIdAndTourId(Long userId, Long tourId) {
        return orderDao.isExistedByUserIdAndTourId(userId, tourId);
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
