package com.oleh.chui.model.dao;

import com.oleh.chui.model.entity.Order;

public interface OrderDao extends GenericDao<Order> {

    boolean isExistedByUserIdAndTourId(Long userId, Long tourId);

    int findByUserIdCount(Long userId);

}
