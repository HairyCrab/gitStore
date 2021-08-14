package com.company.dao.imp;
import com.company.dao.StudentBaseDao;
import com.company.dao.StudentDao;
import com.company.pojo.Student;
import java.util.List;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/11 14:25
 */
public class StudentDaoImp extends StudentBaseDao implements StudentDao {

    @Override
    public List<Student> findByPage(String stname, String lage, Integer currentPage, Integer pageSize) {
        List<Student> studentList = null;
        //查询sql语句，String创建对象后，这个对象中的字符是不可改变的，所以转换成StringBuilder,效率高，线程不安全。
        StringBuilder sbsql = new StringBuilder("select * from student where 1=1 ");
        //根据查询条件判断是否需要拼接sql语句
        if (null != stname && !"".equals(stname)) {
            sbsql.append("and stuname like ?");
        }
        if (null != lage && !"".equals(lage)) {
            sbsql.append("and stuage >= ? ");
        }
        sbsql.append("limit ?,?");
        String sql = sbsql.toString();
        //根据查询条件判断传递的参数
        if ((null != stname && !"".equals(stname)) && (null != lage && !"".equals(lage))) {
            studentList = queryByPage(Student.class, sql, "%" + stname + "%", lage, (currentPage - 1) * pageSize, pageSize);
        } else if ((null != stname && !"".equals(stname)) && (null == lage || "".equals(lage))) {
            studentList = queryByPage(Student.class, sql, "%" + stname + "%", (currentPage - 1) * pageSize, pageSize);
        } else if ((null == stname || "".equals(stname)) && (null != lage && !"".equals(lage))) {
            studentList = queryByPage(Student.class, sql, lage, (currentPage - 1) * pageSize, pageSize);
        } else {
            studentList = queryByPage(Student.class, sql, (currentPage - 1) * pageSize, pageSize);
        }
        return studentList;
    }

    @Override
    public int findAllDataCount(String stname, String lage) {
        int count = 0;
        StringBuilder sbsql = new StringBuilder("select count(1) from student where 1=1 ");
        if (null != stname && !"".equals(stname)) {
            sbsql.append("and stuname like ?");
        }
        if (null != lage && !"".equals(lage)) {
            sbsql.append("and stuage >= ? ");
        }
        String sql = sbsql.toString();
        if ((null != stname && !"".equals(stname)) && (null != lage && !"".equals(lage))) {
            count = queryCount(sql, "%" + stname + "%", lage);
        } else if ((null != stname && !"".equals(stname)) && (null == lage || "".equals(lage))) {
            count = queryCount(sql, "%" + stname + "%");
        } else if ((null == stname || "".equals(stname)) && (null != lage && !"".equals(lage))) {
            count = queryCount(sql, lage);
        } else {
            count = queryCount(sql);
        }
        return count;
    }
}
