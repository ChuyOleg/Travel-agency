package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.TourDao;
import com.oleh.chui.model.dto.TourDto;
import com.oleh.chui.model.entity.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TourService {

    private final Logger logger = LogManager.getLogger(TourService.class);
    private final TourDao tourDao;

    public TourService(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    public List<Tour> findAll() {
        List<Tour> tourList = tourDao.findAll();

        sortBurningFirst(tourList);

        return tourList;
    }

    public List<Tour> findAllUsingFilters(Map<String, String> filterParameters) {
        List<Tour> tourList = tourDao.findAllUsingFilter(filterParameters);

        sortBurningFirst(tourList);

        return tourList;
    }

    public int getToursQuantity() {
        return tourDao.findToursQuantity();
    }

    public boolean isTourWithThisNameAlreadyExists(String name) {
        Optional<Tour> optionalTour = tourDao.findByName(name);

        return optionalTour.isPresent();
    }

    public void create(TourDto tourDto) {
        Tour tour = new Tour(tourDto);

        tourDao.create(tour);
    }

    private void sortBurningFirst(List<Tour> tourList) {
        tourList.sort((o1, o2) -> Boolean.compare(o2.isBurning(), o1.isBurning()));
    }
}
