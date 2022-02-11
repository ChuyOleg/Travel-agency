package com.oleh.chui.model.dao.impl;

import com.oleh.chui.model.dao.*;

public class DaoFactoryImpl extends DaoFactory {

    public DaoFactoryImpl() {}

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl();
    }

    @Override
    public TourDao createTourDao() {
        return new TourDaoImpl();
    }

    @Override
    public OrderDao createOrderDao() {
        return new OrderDaoImpl();
    }

    @Override
    public CountryDao createCountryDao() {
        return new CountryDaoImpl();
    }
}
