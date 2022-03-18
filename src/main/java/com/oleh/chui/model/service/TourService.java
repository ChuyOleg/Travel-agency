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

/**
 * Manages business logic related with Tour.
 *
 * @author Oleh Chui
 */
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

    /**
     * Checks if name of the tour is free and country and city are existed.
     * Creates Tour from TourDto.
     * Invokes method for creating Tour in DAO layer.
     *
     * @param tourDto TourDto instance.
     * @throws TourNameIsReservedException Indicates that name of the tour is reserved.
     * @throws CityNotExistException Indicates that city of the tour does not exist.
     * @throws CountryNotExistException Indicates that country of the tour does not exist.
     */
    public void create(TourDto tourDto) throws TourNameIsReservedException, CityNotExistException, CountryNotExistException {
        checkTourNameIsReserved(tourDto.getName());
        countryService.checkCountryAndCityExist(tourDto.getCountry(), tourDto.getCity());

        Tour tour = new Tour(tourDto);

        tourDao.create(tour);
        logger.info("Tour ({}) has been created", tourDto.getName());
    }

    /**
     * Process updating Tour information.
     *
     * @param tourDto TourDto that contains updated information.
     * @param id Long representing id of Tour that should be updated.
     * @throws TourNameIsReservedException Indicates that name of the tour is reserved.
     * @throws CityNotExistException Indicates that city of the tour does not exist.
     * @throws CountryNotExistException Indicates that country of the tour does not exist.
     */
    public void update(TourDto tourDto, Long id) throws TourNameIsReservedException, CityNotExistException, CountryNotExistException {
        countryService.checkCountryAndCityExist(tourDto.getCountry(), tourDto.getCity());

        Tour tour = new Tour(tourDto);
        tour.setId(id);

        tourDao.update(tour);
        logger.info("Tour (id = {}) has been updated", id);
    }

    public void changeDiscount(TourDto tourDto, Long id) {
        int maxDiscount = Integer.parseInt(tourDto.getMaxDiscount());
        double discountStep = Double.parseDouble(tourDto.getDiscountStep());

        tourDao.updateDiscountInfo(maxDiscount, discountStep, id);
        logger.info("Discount info of tour (id = {}) has been updated", id);
    }

    public void changeBurningState(Long id) {
        tourDao.changeBurningStateById(id);
        logger.info("Burning state of tour (id = {}) has been changed", id);
    }

    public void deleteTour(Long id) {
        tourDao.delete(id);
        logger.info("Tour (id = {}) has been deleted", id);
    }

    private void checkTourNameIsReserved(String name) throws TourNameIsReservedException {
        Optional<Tour> optionalTour = tourDao.findByName(name);

        if (optionalTour.isPresent()) {
            throw new TourNameIsReservedException();
        }
    }

}
