package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.TourDao;
import com.oleh.chui.model.dto.TourDto;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.exception.city.CityNotExistException;
import com.oleh.chui.model.exception.country.CountryNotExistException;
import com.oleh.chui.model.exception.tour.TourNameIsReservedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TourService {

    private final Logger logger = LogManager.getLogger(TourService.class);
    private final CountryService countryService;
    private final TourDao tourDao;

    public TourService(CountryService countryService, TourDao tourDao) {
        this.countryService = countryService;
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

    public void checkTourNameIsReserved(String name) throws TourNameIsReservedException {
        Optional<Tour> optionalTour = tourDao.findByName(name);

        if (optionalTour.isPresent()) {
            throw new TourNameIsReservedException();
        }
    }

    public void create(TourDto tourDto) throws TourNameIsReservedException, CityNotExistException, CountryNotExistException {
        checkTourNameIsReserved(tourDto.getName());
        countryService.checkCountryAndCityExist(tourDto.getCountry(), tourDto.getCity());

        Tour tour = new Tour(tourDto);

        tourDao.create(tour);
        logger.info("Tour ({}) has been created", tourDto.getName());
    }

    public void update(TourDto tourDto, Long id) throws TourNameIsReservedException, CityNotExistException, CountryNotExistException {
        countryService.checkCountryAndCityExist(tourDto.getCountry(), tourDto.getCity());

        Tour tour = new Tour(tourDto);
        tour.setId(id);

        tourDao.update(tour);
        logger.info("Tour (id = {}) has been updated", id);
    }

    public void changeBurningState(Long id) {
        tourDao.changeBurningStateById(id);
    }

    public void deleteTour(Long id) {
        tourDao.delete(id);
    }

}
