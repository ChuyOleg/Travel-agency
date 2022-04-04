package com.oleh.chui.model.dao;

import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.service.util.pagination.PaginationInfo;

import java.util.Map;
import java.util.Optional;

public interface TourDao extends GenericDao<Tour> {

    Optional<Tour> findByName(String name);

    PaginationInfo getPaginationResultData(Map<String, String> filterFieldMap, int limit, int offset);

    void changeBurningStateById(Long id);

    void updateDiscountInfo(int maxDiscount, double discountStep, Long id);

}
