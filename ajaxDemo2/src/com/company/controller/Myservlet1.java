package com.company.controller;
import com.company.pojo.Area;
import com.company.service.Myservice1;
import com.company.service.imp.MyserviceImp;
import com.google.gson.Gson;

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
 * @date 2021/8/13 18:58
 */
@WebServlet(urlPatterns = "/servlet1.do")
public class Myservlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int parentID=0;
        try {
            parentID =Integer.parseInt(req.getParameter("parentID"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Myservice1 myserviceImp = new MyserviceImp();
        //调用service层方法，根据传来的parentID 将查询的结果以List集合返回过来
        List<Area> areaList = myserviceImp.findByParentID(parentID);
        Gson gson = new Gson();
        String s = gson.toJson(areaList);
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(s);


    }
}
