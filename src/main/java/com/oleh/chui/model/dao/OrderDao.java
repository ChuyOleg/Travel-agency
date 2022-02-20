package com.oleh.chui.model.dao;

import com.oleh.chui.model.entity.Order;
import com.oleh.chui.model.entity.Status;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    boolean isExistedByUserIdAndTourId(Long userId, Long tourId);

    boolean isExistedByTourId(Long tourId);

    List<Order> findAllByUserId(Long userId);

    int findByUserIdCount(Long userId);

    void changeStatus(Status.StatusEnum newStatus, Long orderId);

}
