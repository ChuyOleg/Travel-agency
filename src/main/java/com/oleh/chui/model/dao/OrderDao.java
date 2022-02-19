package com.oleh.chui.model.dao;

import com.oleh.chui.model.entity.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    boolean isExistedByUserIdAndTourId(Long userId, Long tourId);

    boolean isExistedByTourId(Long tourId);

    List<Order> findAllByUserId(Long userId);

    int findByUserIdCount(Long userId);

}
