package com.oleh.chui.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPoolHolder {

    private static final String DB_PROPERTIES_PATH = "C:/Users/User/IdeaProjects/epamPractice/Travel-agency/src/main/resources/db.properties";
    private static final String DB_URL_KEY = "db.url";
    private static final String DB_USERNAME_KEY = "db.username";
    private static final String DB_PASSWORD_KEY = "db.password";
    private static final String DB_MIN_IDLE_KEY = "db.min.idle";
    private static final String DB_MAX_IDLE_KEY = "db.max.idle";
    private static final String DB_MAX_OPEN_PREPARED_STATEMENT_KEY = "db.max.open.prepared.statement";
    private static final String DB_DRIVER_CLASS_NAME_KEY = "db.driver.class.name";

    private static volatile DataSource dataSource;
    private static final Logger logger = LogManager.getLogger(ConnectionPoolHolder.class);

    private ConnectionPoolHolder() {}

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    try (FileReader reader = new FileReader(DB_PROPERTIES_PATH)) {
                        Properties p = new Properties();
                        p.load(reader);
                        BasicDataSource ds = new BasicDataSource();
                        ds.setUrl(p.getProperty(DB_URL_KEY));
                        ds.setUsername(p.getProperty(DB_USERNAME_KEY));
                        ds.setPassword(p.getProperty(DB_PASSWORD_KEY));
                        ds.setMinIdle(Integer.parseInt(p.getProperty(DB_MIN_IDLE_KEY)));
                        ds.setMaxIdle(Integer.parseInt(p.getProperty(DB_MAX_IDLE_KEY)));
                        ds.setMaxOpenPreparedStatements(Integer.parseInt(p.getProperty(DB_MAX_OPEN_PREPARED_STATEMENT_KEY)));
                        ds.setDriverClassName(p.getProperty(DB_DRIVER_CLASS_NAME_KEY));
                        dataSource = ds;
                    } catch (IOException e) {
                        logger.fatal("Error during connecting to DB " + e.getMessage());
                        System.exit(-1);
                    }
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            logger.error("{}, when trying to get connection", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("{}, error when trying to close connection", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
