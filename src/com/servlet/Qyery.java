package com.servlet;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Query", value = "/Query")
public class Qyery extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean is_ok = false;
        String identify = request.getParameter("identify");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String susp = request.getParameter("susp");
        String diag = request.getParameter("diag");
        String date = request.getParameter("date");
        System.out.println(identify+name+sex+province+city+susp+diag+date);
        PrintWriter oo = response.getWriter();
        response.setCharacterEncoding("utf-8");
        oo.print(new Gson().toJson("查询成功！"));
        oo.flush();
        oo.close();
    }
}
