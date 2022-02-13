package com.oleh.chui.model.dao;

import com.oleh.chui.model.entity.Tour;

import java.util.Optional;

public interface TourDao extends GenericDao<Tour> {

    Optional<Tour> findByName(String name);

}
