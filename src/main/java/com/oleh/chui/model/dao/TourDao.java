package com.oleh.chui.model.dao;

import com.oleh.chui.model.entity.Tour;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TourDao extends GenericDao<Tour> {

    Optional<Tour> findByName(String name);

    List<Tour> findAllUsingFilterAndPagination(Map<String, String> filterFieldMap, int limit, int offSet);

    int findFilteredToursQuantity(Map<String, String> filterFieldMap);

    void changeBurningStateById(Long id);

    void updateDiscountInfo(int maxDiscount, double discountStep, Long id);

}
