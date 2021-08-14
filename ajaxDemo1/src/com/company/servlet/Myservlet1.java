package com.company.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/12 16:40
 */
@WebServlet(urlPatterns = "/usernameController.do")
public class Myservlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        Boolean info;
        if(uname.equals("lxx")){
            info=false;
        }else{
            info=true;
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(String.valueOf(info));

    }
}
