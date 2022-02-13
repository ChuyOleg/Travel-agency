package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.TourDao;
import com.oleh.chui.model.dto.TourDto;
import com.oleh.chui.model.entity.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TourService {

    private final Logger logger = LogManager.getLogger(TourService.class);
    private final TourDao tourDao;

    public TourService(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    public List<Tour> findAll() {
        return tourDao.findAll();
    }

    public boolean isTourWithThisNameAlreadyExists(String name) {
        Optional<Tour> optionalTour = tourDao.findByName(name);

        return optionalTour.isPresent();
    }

    public void create(TourDto tourDto) {
        Tour tour = new Tour(tourDto);

        tourDao.create(tour);
    }
}
