package com.company.controller;

import com.company.pojo.Student;
import com.company.pojo.StudentPageBean;
import com.company.service.StudentService;
import com.company.service.imp.StudentServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/11 13:57
 */
@WebServlet(urlPatterns = "/showStudent.do")
public class ShowStudentController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前页和页大小，搜索的姓名和年龄
        Integer currentPage = Integer.parseInt(req.getParameter("currentPage"));
        Integer pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String stname = req.getParameter("stname");
        String lage = req.getParameter("lage");
        //将搜索用的名字和年龄放进request域中，在前端切换页面的时候方便从域中取值做回显
        req.setAttribute("stname",stname);
        req.setAttribute("lage",lage);
        //创建实现类对象（service服务层的）
        StudentService studentService=new StudentServiceImp();
        StudentPageBean<Student> studentPageBean = studentService.selectStudent(stname,lage,currentPage, pageSize);
        req.setAttribute("StudentPageBean",studentPageBean);
        //然后转发
        req.getRequestDispatcher("student.jsp").forward(req,resp);


    }
}
