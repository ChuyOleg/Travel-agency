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

    public Optional<Tour> findById(Long id) {
        return tourDao.findById(id);
    }

    public List<Tour> findAllUsingFiltersAndPagination(Map<String, String> filterParameters, int pageSize, int pageNum) {
        int offSet = pageSize * (pageNum - 1);

        return tourDao.findAllUsingFilterAndPagination(filterParameters, pageSize, offSet);
    }

    public int getPagesNumber(Map<String, String> filterParameters, int pageSize) {
        int toursNumber = tourDao.findFilteredToursQuantity(filterParameters);

        return (int) Math.ceil((double) toursNumber / pageSize);
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
