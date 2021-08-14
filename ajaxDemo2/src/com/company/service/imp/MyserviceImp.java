package com.company.service.imp;

import com.company.dao.AreaDao;
import com.company.dao.imp.AreaDaoImp;
import com.company.pojo.Area;
import com.company.service.Myservice1;

import java.util.List;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/13 19:02
 */
public class MyserviceImp implements Myservice1 {
    @Override
    public List<Area> findByParentID(int parentID) {
        AreaDao areaDaoImp = new AreaDaoImp();
        List<Area> byParrent = areaDaoImp.findByParrent(parentID);
        return byParrent;
    }
}
