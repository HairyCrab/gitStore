package com.company.dao.imp;

import com.company.dao.AreaDao;
import com.company.dao.BaseDao;
import com.company.pojo.Area;

import java.util.List;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/13 19:00
 */
public class AreaDaoImp extends BaseDao implements AreaDao {
    @Override
    public List<Area> findByParrent(int parentID) {
        String sql="select * from area where parentid=?";

        return queryData(Area.class,sql,parentID);
    }
}
