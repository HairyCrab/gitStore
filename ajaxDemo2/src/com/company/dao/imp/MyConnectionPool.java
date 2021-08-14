package com.company.dao.imp;

import com.company.util.PropertiesUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/13 19:02
 */
public class MyConnectionPool {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static Logger logger;
    //private static Connection connection;
    private static LinkedList<Connection> pool;

    static {
        PropertiesUtil propertiesUtil = new PropertiesUtil("/jdbc.properties");
        driver = propertiesUtil.getProperties("driver");
        url = propertiesUtil.getProperties("sqlUrl");
        user = propertiesUtil.getProperties("sqlUser");
        password = propertiesUtil.getProperties("sqlPwd");
        logger = Logger.getLogger(MyConnectionPool.class);
        //Connection connection;
        try {
            Class.forName(driver);
            pool = new LinkedList<>();
            for (int i = 0; i < 5; i++) {
                Connection connection = initConnection();
                pool.add(connection);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection initConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection() {
        Connection connection = null;
        if (pool.size() > 0) {
            connection = pool.removeFirst();
        } else {
            connection = initConnection();
        }
        return connection;
    }

    public static void returnConnection(Connection connection) {
        if (null != connection) {
            try {
                if (!connection.isClosed()) {
                    if (pool.size() < 5) {
                        connection.setAutoCommit(true);//调整事务状态
                        pool.addLast(connection);
                        logger.info("链接池没满，已归还！");
                    } else {
                        connection.close();
                        logger.warn("连接池满了，已经此connection关闭！");
                    }
                } else {
                    logger.warn("连接池已被关闭，不用再关了");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            logger.warn("链接为空，不可归还关闭！");
        }
    }
}
