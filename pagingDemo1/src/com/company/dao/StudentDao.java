package com.company.dao;

import com.company.pojo.Student;

import java.util.List;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/11 14:26
 */
public interface StudentDao {

    /**
     * 查询数据总量的方法
     * @param stname 条件查询时候的学生姓名
     * @param lage 条件查询时候的学生最低年龄限制
     * @return 返回根据条件查出的数据总量 int类型
     */
    int findAllDataCount(String stname,String lage);

    /**
     * 主要根据当前页currentPage查询出对应的学生数据并返回学生List集合
     * @param stname 学生姓名
     * @param lage 学生最低年龄限制
     * @param currentPage 当前页数
     * @param pageSize 页大小
     * @return 返回查询出来的学生List集合
     */
    List<Student> findByPage(String stname,String lage,Integer currentPage, Integer pageSize);
}
