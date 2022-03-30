package com.servlet;

import DAO.DBUtil;
import DAO.UserDAOImp;
import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.Select;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "epidemic", value = "/epidemic")
public class epidemic extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userfrom = request.getParameter("userfrom");
        System.out.println("前端数据："+username+","+password+","+userfrom);

        try{
            Connection cnn = DBUtil.getConnection();
            UserDAOImp userDAOImp = new UserDAOImp(cnn);
            if (userDAOImp.LoginUser(username,password)){
                PrintWriter oo = response.getWriter();
                response.setCharacterEncoding("utf-8");
                oo.print(new Gson().toJson("登陆成功！"));
                oo.flush();
                oo.close();
            }else{
                response.sendError(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
