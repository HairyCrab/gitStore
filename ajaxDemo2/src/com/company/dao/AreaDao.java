package com.company.dao;

import com.company.pojo.Area;

import java.util.List;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/13 18:59
 */
public interface AreaDao {
    List<Area> findByParrent(int parentID);
}
