package com.oleh.chui.model.service;

import com.oleh.chui.model.dao.DaoFactory;

public class ServiceFactoryImpl extends ServiceFactory {

    @Override
    public UserService createUserService() {
        return new UserService(DaoFactory.getInstance().createUserDao());
    }

    @Override
    public TourService createTourService() {
        return new TourService(DaoFactory.getInstance().createTourDao());
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
