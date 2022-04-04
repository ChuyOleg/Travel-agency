package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.TourDao;
import com.oleh.chui.model.dto.TourDto;
import com.oleh.chui.model.entity.HotelType;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.entity.TourType;
import com.oleh.chui.model.exception.tour.TourNameIsReservedException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TourServiceTest {

    CountryService countryService = mock(CountryService.class);
    TourDao tourDao = mock(TourDao.class);
    TourService tourService = new TourService(countryService, tourDao);

    private static final String TOUR_NAME = "NAME";

    private static final TourDto TOUR_DTO = new TourDto(
            TOUR_NAME,
            "1300",
            "COUNTRY",
            "CITY",
            "DESCRIPTION",
            "10",
            "2",
            TourType.TourTypeEnum.VACATION.name(),
            HotelType.HotelTypeEnum.FOUR_STARS.name(),
            "2",
            "2022-08-02",
            "2022-08-08",
            "BURNING"
    );

    private static final Tour TOUR = new Tour(TOUR_DTO);

    @Test
    void checkCreateShouldWorkWithoutException() {
        when(tourDao.findByName(TOUR_NAME)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> tourService.create(TOUR_DTO));

        verify(tourDao, times(1)).create(TOUR);
    }

    @Test
    void checkCreateShouldThrowTourNameIsReservedException() {
        when(tourDao.findByName(TOUR_NAME)).thenReturn(Optional.of(TOUR));

        assertThrows(
                TourNameIsReservedException.class,
                () -> tourService.create(TOUR_DTO)
        );
    }

    @Test
    void checkUpdateShouldWorkWithoutException() {
        assertDoesNotThrow(() -> tourService.update(TOUR_DTO, 1L));

        verify(tourDao, times(1)).update(TOUR);
    }

    @Test
    void checkChangeDiscount() {
        tourService.changeDiscount(TOUR_DTO, 1L);
        verify(tourDao, times(1)).updateDiscountInfo(TOUR.getMaxDiscount(), TOUR.getDiscountStep(), 1L);
    }

    @Test
    void changeBurningState() {
        tourService.changeBurningState(1L);
        verify(tourDao, times(1)).changeBurningStateById(1L);
    }

    @Test
    void checkDeleteTour() {
        tourService.deleteTour(1L);
        verify(tourDao, times(1)).delete(1L);
    }

}