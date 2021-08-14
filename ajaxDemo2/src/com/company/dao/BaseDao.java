package com.company.dao;

import com.company.dao.imp.MyConnectionPool;

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
 * @date 2021/8/13 19:00
 */
public class  BaseDao {
    private static List list;
    public static List queryData(Class clazz,String sql,Object... args){
        Connection connection = MyConnectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            Field[] declaredField = clazz.getDeclaredFields();
            for (Field field : declaredField) {
                field.setAccessible(true);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            list=new ArrayList<>();
            while (resultSet.next()){
                Object o = clazz.newInstance();
                for (Field field : declaredField) {
                    Object object = resultSet.getObject(field.getName());
                    field.set(o,object);
                }
                list.add(o);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return list;
    }
}
