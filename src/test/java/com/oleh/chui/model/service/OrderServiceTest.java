package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.OrderDao;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.entity.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    UserService userService = mock(UserService.class);
    TourService tourService = mock(TourService.class);
    OrderDao orderDao = mock(OrderDao.class);
    OrderService orderService = new OrderService(userService, tourService, orderDao);

    private static final Long ID = 1L;

    private static final Tour TOUR = Tour.builder()
            .id(ID)
            .name("NAME")
            .price(BigDecimal.valueOf(100))
            .maxDiscount(20)
            .discountStep(2)
            .build();

    private static final User USER = User.builder()
            .id(ID)
            .username("USERNAME")
            .build();

    @Test
    void checkCreateOrderShouldWorkWithoutException() {
        when(userService.findById(ID)).thenReturn(Optional.of(USER));
        when(tourService.findById(ID)).thenReturn(Optional.of(TOUR));

        assertDoesNotThrow(() -> orderService.createOrder(ID, ID));
    }

    @Test
    void checkCreateOrderShouldThrowRuntimeException() {
        when(userService.findById(ID)).thenReturn(Optional.empty());
        when(tourService.findById(ID)).thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> orderService.createOrder(ID, ID)
        );
    }

    @Test
    void checkCalculateFinalPrice() {
        when(orderDao.findByUserIdCount(ID)).thenReturn(2);

        assertEquals(0, orderService.calculateFinalPrice(ID, TOUR).compareTo(BigDecimal.valueOf(96)));
    }
}