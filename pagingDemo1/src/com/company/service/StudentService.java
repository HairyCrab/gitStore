package com.company.service;

import com.company.pojo.Student;
import com.company.pojo.StudentPageBean;

import java.util.List;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/11 16:39
 */
public interface StudentService {
    /**
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    StudentPageBean<Student> selectStudent(String stname,String lage,Integer currentPage, Integer pageSize);
}
