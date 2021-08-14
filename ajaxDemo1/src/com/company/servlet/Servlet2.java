package com.company.servlet;

import com.company.pojo.Student;
import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/13 12:37
 */
@WebServlet(urlPatterns = "/servlet2.do")
public class Servlet2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        String password=req.getParameter("password");
        System.out.println(uname);
        System.out.println(password);
        Student student = new Student("lili", "123456", "女", new Date());
        System.out.println("-----"+student);
        Gson gson = new Gson();
        String s = gson.toJson(student);
        System.out.println("----"+s);
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(s);

    }
}
