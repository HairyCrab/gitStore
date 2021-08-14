package com.company.dao;

import com.company.dao.imp.MyConnectionPool;
import com.company.pojo.Student;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/11 17:43
 */
public class StudentBaseDao {
    private static Connection connection;

    public static List<Student> queryByPage(Class clazz, String sql, Object... args) {
        List list = null;
        try {
            connection = MyConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //遍历传递过来的参数，并给sql中的问号? 设置参数
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            //执行executeQuery 返回result结果集，需要遍历取值
            ResultSet resultSet = preparedStatement.executeQuery();
            Field[] declaredFields = clazz.getDeclaredFields();
            //设置通过反射字节码获取的student字段变量可以访问，不然student的private 声明的属性不可访问到，
            //field--> stuname stuage ...
            for (Field field : declaredFields) {
                field.setAccessible(true);
            }
            list = new ArrayList<>();
            while (resultSet.next()) {
                try {
                    //o 包含所有属性且为空的一个对象
                    Object o = clazz.newInstance();
                    for (Field field : declaredFields) {
                        //通过field.getName()即stuname stuage... 从结果集中获取到查询的数据
                        Object object = resultSet.getObject(field.getName());
                        field.set(o, object);
                    }
                    list.add(o);

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            MyConnectionPool.backConnection(connection);
        }
        return list;
    }

    public static int queryCount(String sql, Object... args) {
        int anInt = 0;
        try {
            Connection connection = MyConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //列数
                anInt = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return anInt;
    }
}
