package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.OrderDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderService {

    private final Logger logger = LogManager.getLogger(OrderService.class);
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
