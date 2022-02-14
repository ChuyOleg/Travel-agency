package com.oleh.chui.model.dao;

import com.oleh.chui.model.entity.Tour;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TourDao extends GenericDao<Tour> {

    Optional<Tour> findByName(String name);

    List<Tour> findAllUsingFilter(Map<String, String> filterFieldMap);

}
