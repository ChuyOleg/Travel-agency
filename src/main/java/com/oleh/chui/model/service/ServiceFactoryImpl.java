package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.DaoFactory;
import com.oleh.chui.model.service.util.PBKDF2PasswordEncoder;

/**
 * Manages creating all available services.
 *
 * @author Oleh Chui
 */
public class ServiceFactoryImpl extends ServiceFactory {

    @Override
    public UserService createUserService() {
        return new UserService(
                new PBKDF2PasswordEncoder(),
                DaoFactory.getInstance().createUserDao()
        );
    }

    @Override
    public TourService createTourService() {
        return new TourService(
                createCountryService(),
                DaoFactory.getInstance().createTourDao()
        );
    }

    @Override
    public OrderService createOrderService() {
        return new OrderService(
                createUserService(),
                createTourService(),
                DaoFactory.getInstance().createOrderDao()
        );
    }

    @Override
    public CountryService createCountryService() {
        return new CountryService(DaoFactory.getInstance().createCountryDao());
    }
}
