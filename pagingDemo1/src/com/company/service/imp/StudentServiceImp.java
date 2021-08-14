package com.company.service.imp;

import com.company.dao.StudentDao;
import com.company.dao.imp.StudentDaoImp;
import com.company.pojo.Student;
import com.company.pojo.StudentPageBean;
import com.company.service.StudentService;

import java.util.List;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/11 16:40
 */
public class StudentServiceImp implements StudentService {
    @Override
    public StudentPageBean<Student> selectStudent(String stname,String lage,Integer currentPage, Integer pageSize) {
        //服务层处理逻辑，具体操作交给dao层，创建dao层实现类对象
        StudentDao studentDao=new StudentDaoImp();
        //dao层实现方法，当前页数据data
        List<Student> studentList = studentDao.findByPage(stname,lage,currentPage,pageSize);
        //dao层实现方法，数据总量个数
        int studentDaoAllDataCount = studentDao.findAllDataCount(stname,lage);
        //根据返回总数量计算出页数
        int allPage=studentDaoAllDataCount%pageSize==0?studentDaoAllDataCount/pageSize:studentDaoAllDataCount/pageSize+1;
        //封装成对象
        StudentPageBean studentPageBean = new StudentPageBean(studentList, pageSize, studentDaoAllDataCount, allPage, currentPage);
        return studentPageBean;

    }
}
