package com.company.dao.imp;
import com.company.util.PropertiesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/11 14:00
 */
public class MyConnectionPool {
    private static String url;
    private static String sqlUser;
    private static String sqlPassword;
    private static String driver;
    private static LinkedList<Connection> pool;
    public static void Util(){
        Properties properties = new Properties();
        //jdk 1.8 可用，通过字节码获取io流执行properties
        //执行字节码跟路径的一个文件的IO流
        InputStream inputStream = MyConnectionPool.class.getResourceAsStream("/jdbc.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = properties.getProperty("driver");
        System.out.println("Util方法中获取的--"+driver);
    }

    static {
        PropertiesUtil propertiesUtil = new PropertiesUtil("/jdbc.properties");
        driver = propertiesUtil.getProperties("driver");
        url = propertiesUtil.getProperties("sqlUrl");
        sqlUser = propertiesUtil.getProperties("sqlUser");
        sqlPassword = propertiesUtil.getProperties("sqlPwd");
        //加载驱动
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //初始化pool
        pool = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Connection connection = initConnection();
            if (connection != null) {
                pool.add(connection);
            }else{
                System.out.println("c初始化的connection为空");
            }
        }

    }

    private static Connection initConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, sqlUser, sqlPassword);
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

    /*public static void returnConnection(Connection connection) {
        if (null != connection) {
            try {
                if (!connection.isClosed()) {
                    if (pool.size() < 5) {
                        connection.setAutoCommit(true);//调整事务状态
                        pool.addLast(connection);
                        //logger.info("链接池没满，已归还！");
                    } else {
                        connection.close();
                        //logger.warn("连接池满了，已经此connection关闭！");
                    }
                } else {
                    System.out.println("连接池已被关闭，不用再关了");
                    //logger.warn("连接池已被关闭，不用再关了");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("链接为空，不可归还关闭！");
            //logger.warn("链接为空，不可归还关闭！");
        }
    }*/

    public static void backConnection(Connection connection){
        if(null != connection){
            try {
                if(!connection.isClosed()){
                    if(pool.size()<5){
                        try {
                            connection.setAutoCommit(true);// 调整事务状态
                            System.out.println("设置连接:"+connection.hashCode()+"自动提交为true");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        pool.addLast(connection);
                        System.out.println("连接池未满,归还连接:"+connection.hashCode());
                    }else{
                        try {
                            connection.close();
                            System.out.println("连接池满了,关闭连接:"+connection.hashCode());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    System.out.println("连接:"+connection.hashCode()+"已经关闭,无需归还");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("传入的连接为null,不可归还");
        }
    }
}
