package com.oleh.chui.model.dao.impl;

import com.oleh.chui.model.dao.DaoFactory;
import com.oleh.chui.model.dao.OrderDao;
import com.oleh.chui.model.dao.TourDao;
import com.oleh.chui.model.dao.UserDao;

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
}
