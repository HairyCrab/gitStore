package com.company.test;

import com.company.dao.AreaDao;
import com.company.dao.imp.AreaDaoImp;
import com.company.pojo.Area;
import org.junit.Test;

import java.util.List;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/13 21:49
 */
public class tt {
    @Test
    public void query(){
        AreaDao areaDaoImp = new AreaDaoImp();
        List<Area> byParrent = areaDaoImp.findByParrent(0);
        for (Area area : byParrent) {
            System.out.println(area);
        }
    }
}
