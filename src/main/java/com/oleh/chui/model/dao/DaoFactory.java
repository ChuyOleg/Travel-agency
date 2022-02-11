package com.oleh.chui.model.dao;

import com.oleh.chui.model.dao.impl.DaoFactoryImpl;

public abstract class DaoFactory {

    private static volatile DaoFactory daoFactory;

    protected DaoFactory() {}

    public abstract UserDao createUserDao();
    public abstract TourDao createTourDao();
    public abstract OrderDao createOrderDao();
    public abstract CountryDao createCountryDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new DaoFactoryImpl();
                }
            }
        }
        return daoFactory;
    }

}
