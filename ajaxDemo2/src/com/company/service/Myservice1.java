package com.company.service;

import com.company.pojo.Area;

import java.util.List;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/13 19:01
 */
public interface Myservice1 {
    List<Area> findByParentID(int parentID);
}
